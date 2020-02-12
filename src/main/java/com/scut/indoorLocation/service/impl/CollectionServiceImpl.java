package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scut.indoorLocation.entity.Collection;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.DeleteException;
import com.scut.indoorLocation.mapper.CollectionMapper;
import com.scut.indoorLocation.service.CollectionService;
import com.scut.indoorLocation.utility.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Created by Mingor on 2020/1/1 10:24
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

    @Resource
    private CollectionMapper collectionMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void createCollection(String storeId) throws CreateException {
        //获取用户ID
        String uid = jwtUtil.extractUidSubject(request);

        Collection collection = Collection.builder()
                .userId(uid)
                .storeId(storeId)
                .createTime(LocalDateTime.now())
                .build();

        //保存
        if (collectionMapper.insert(collection) != 1)
            throw new CreateException("收藏信息创建异常");
    }

    @Override
    public IPage<Collection> queryByPage(Long pageNO, Long pageSize) {

        //获取用户ID
        String uid = jwtUtil.extractUidSubject(request);

        // 构造分页查询条件
        IPage<Collection> page = new Page<>(pageNO, pageSize);
        QueryWrapper<Collection> wrapper = new QueryWrapper<Collection>().eq("user_id", uid);

        // 分页查询
        return collectionMapper.selectPage(page, wrapper);

    }
    @Override
    public void deleteCollection(String collectionId) throws DeleteException {
        //获取用户ID
        String uid = jwtUtil.extractUidSubject(request);

        Collection collection = Collection.builder()
                .userId(uid)
                .collectionId(collectionId)
                .createTime(LocalDateTime.now())
                .build();

        //保存
        if (collectionMapper.deleteById(collection.getCollectionId()) != 1)
            throw new DeleteException("收藏信息创建异常");
    }

}
