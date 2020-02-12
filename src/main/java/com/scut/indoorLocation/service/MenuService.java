package com.scut.indoorLocation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.MenuItemRequest;
import com.scut.indoorLocation.entity.MenuItem;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.NotExistException;
import com.scut.indoorLocation.exception.NotStoreOwnerException;

/**
 * Created by Mingor on 2019/12/31 10:24
 */
public interface MenuService  {

    /**
     * 创建菜单项
     * @param menuItemRequest 菜单项描述信息
     */
    void createMenuItem(MenuItemRequest menuItemRequest) throws CreateException;


    /**
     * 以分页的方式查询商铺菜单
     * @param storeId 商铺ID
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return 分页形式的菜单列表
     */
    IPage<MenuItem> queryMenuItemByPage(String storeId, Long pageNo, Long pageSize);

    /**
     * 修改菜单信息
     * Add by hxy 2020/02/11
     * @param menuItemRequest 新的菜单信息
     */

    void modifyMenuInfo(MenuItemRequest menuItemRequest) throws NotExistException,NotStoreOwnerException;

    /**
     * Add by hxy 2020/ 01 /10
     * 删除某项菜单
     * @param MenuId 菜单项的id
     * */
    void deleteMenuItem(String MenuId) throws NotStoreOwnerException, NotExistException;



}
