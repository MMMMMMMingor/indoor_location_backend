package com.scut.indoorLocation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.PageRequest;
import com.scut.indoorLocation.dto.StoreInfoRequest;
import com.scut.indoorLocation.entity.Store;
import com.scut.indoorLocation.exception.StoreCreateException;


/**
 * 店铺service接口
 * Created by Mingor on 2019/12/30 21:57
 */
public interface StoreService {

    /**
     * 创建店铺信息
     * @param param 店铺信息
     */
    void createStore(StoreInfoRequest param) throws StoreCreateException;


    /**
     * 查询商铺(分页方式)
     * @return 商铺列表
     */
    IPage<Store> queryStoresByPage(PageRequest pageRequest);

}
