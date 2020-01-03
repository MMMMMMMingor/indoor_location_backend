package com.scut.indoorLocation.utility;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mingor on 2019/11/19 15:35
 */

@Component
@Slf4j
public class LevelDBUtil {

    private DB db;

    @Value("${levelDB.dir.path}")
    private String DB_DIR_PATH;

    private static final String CHARSET = "utf-8";

    //spring启动时初始化
    @PostConstruct
    public void initLevelDB() {
        DBFactory factory = new Iq80DBFactory();
        Options options = new Options();
        options.cacheSize(100 * 1024 * 1024); // 100MB cache
        options.createIfMissing(true);
        options.logger(System.out::println);  // 打印日志
        try {
            this.db = factory.open(new File(DB_DIR_PATH), options);
        } catch (IOException e) {
            log.error("levelDB启动异常", e);
        }
    }

    //基于fastjson的对象序列化
    private byte[] serializer(Object obj) {
        return JSON.toJSONBytes(obj, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 存放数据
     * @param key 键
     * @param val 值
     */
    public void put(@NotNull String key, Object val) {
        try {
            this.db.put(key.getBytes(CHARSET), this.serializer(val));
        } catch (UnsupportedEncodingException e) {
            log.error("编码转化异常", e);
        }
    }

    /**
     * 根据key获取数据
     * @param key 键
     * @return value 值
     */
    public Object get(String key) {
        byte[] val = null;
        try {
            val = db.get(key.getBytes(CHARSET));
        } catch (Exception e) {
            log.error("levelDB get error", e);
        }
        if(val != null)
            return JSON.parse(val);
        else
            return null;
    }

    /**
     * 根据key获取数据
     * @param key 键
     * @param clazz 类型
     * @param <T> 泛型
     * @return value 值
     */
    public <T> T get(String key, Class clazz) {
        byte[] val = null;
        try {
            val = db.get(key.getBytes(CHARSET));
        } catch (Exception e) {
            log.error("levelDB get error", e);
        }
        if(val != null)
            return JSON.parseObject(val, clazz);
        else
            return null;
    }

    /**
     * 根据key删除数据
     * @param key 键
     */
    public void delete(String key) {
        try {
            db.delete(key.getBytes(CHARSET));
        } catch (Exception e) {
            log.error("levelDB delete error", e);
        }

    }

    /**
     * 获取所有key
     * @return 所有的keys
     */
    public List<String> getKeys() {

        List<String> list = new ArrayList<>();
        DBIterator iterator = null;
        try {
            iterator = db.iterator();
            while (iterator.hasNext()) {
                Map.Entry<byte[], byte[]> item = iterator.next();
                String key = new String(item.getKey(), CHARSET);
                list.add(key);
            }
        } catch (Exception e) {
            log.error("遍历发生异常", e);
        } finally {
            if (iterator != null) {
                try {
                    iterator.close();
                } catch (IOException e) {
                    log.error("遍历发生异常", e);
                }

            }
        }
        return list;
    }

    //spring销毁对象前关闭
    @PreDestroy
    public void closeDB() {
        if (db != null) {
            try {
                db.close();
            } catch (IOException e) {
                log.error("levelDB 关闭异常", e);
            }
        }
    }

}
