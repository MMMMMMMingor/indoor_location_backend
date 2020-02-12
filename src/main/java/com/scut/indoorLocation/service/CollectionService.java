package com.scut.indoorLocation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scut.indoorLocation.entity.Collection;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.DeleteException;

/**
 * Created by Mingor on 2020/1/1 10:21
 */
public interface CollectionService extends IService<Collection> {

    /**
     * 添加商铺收藏信息
     * @param storeId 商铺ID
     */
    void createCollection(String storeId) throws CreateException;


    /**
     * 分页查询收藏信息
     * @param pageNO 页号
     * @param pageSize 页大小
     * @return 分页后的收藏信息
     */
    IPage<Collection> queryByPage(Long pageNO, Long pageSize);
    /**
     * 删除商铺收藏信息
     * @param storeId 商铺ID
     */
    void deleteCollection(String storeId) throws DeleteException;
}
