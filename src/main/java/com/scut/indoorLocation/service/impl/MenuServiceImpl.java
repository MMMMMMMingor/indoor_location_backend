package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.indoorLocation.dto.MenuItemRequest;
import com.scut.indoorLocation.entity.MenuItem;
import com.scut.indoorLocation.entity.Store;
import com.scut.indoorLocation.exception.*;
import com.scut.indoorLocation.mapper.MenuItemMapper;
import com.scut.indoorLocation.mapper.StoreMapper;
import com.scut.indoorLocation.service.MenuService;
import com.scut.indoorLocation.utility.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Mingor on 2019/12/31 10:24
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuItemMapper menuItemMapper;

    /**
     *Add by hxy 2020/2/11
     */
    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private HttpServletRequest request;

    @Resource
    private StoreMapper storeMapper;

    @Override
    public void createMenuItem(MenuItemRequest menuItemRequest) throws CreateException {
        MenuItem menuItem = MenuItem.builder()
                .storeId(menuItemRequest.getStoreId())
                .itemName(menuItemRequest.getItemName())
                .introduction(menuItemRequest.getIntroduction())
                .price(menuItemRequest.getPrice())
                .imageUrl(menuItemRequest.getImageUrl())
                .build();

        if (menuItemMapper.insert(menuItem) != 1)
            throw new CreateException("菜单项创建异常");

    }

    @Override
    public IPage<MenuItem> queryMenuItemByPage(String storeId, Long pageNo, Long pageSize) {
        Page<MenuItem> page = new Page<>(pageNo, pageSize);
        QueryWrapper<MenuItem> wrapper = new QueryWrapper<>();
        wrapper.eq("store_id", storeId);
        return menuItemMapper.selectPage(page, wrapper);
    }

    /**
     * Add by hxy 2020/02/11
     *修改菜单
     */
    @Override
    public void modifyMenuInfo(MenuItemRequest menuItemRequest) throws NotStoreOwnerException, NotExistException {

        if(menuItemMapper.selectById(menuItemRequest.getMenuId())==null)
            throw new NotExistException("不存在该菜单项");
        //提取uid
        String uid =jwtUtil.extractUidSubject(this.request);
        MenuItem menuItem=menuItemMapper.selectById(menuItemRequest.getMenuId()); //以传入的menuid为唯一标准
        Store store = storeMapper.selectById(menuItem.getStoreId());
        if(!store.getOwnerId().equals(uid))
            throw new NotStoreOwnerException("该用户不是店铺的所有人");


        MenuItem newMenuItem = MenuItem.builder()
                .menuId(menuItemRequest.getMenuId())
                .storeId(menuItem.getStoreId())
                .itemName(menuItemRequest.getItemName()==null?menuItem.getItemName():menuItemRequest.getItemName())
                .introduction(menuItemRequest.getIntroduction()==null?menuItem.getIntroduction():menuItemRequest.getIntroduction())
                .price(menuItemRequest.getPrice()==null?menuItem.getPrice():menuItemRequest.getPrice())
                .imageUrl(menuItemRequest.getImageUrl()==null?menuItem.getImageUrl():menuItemRequest.getImageUrl())
                .build();

      menuItemMapper.updateById(newMenuItem);
    }

    /**
     * Add by hxy 2020/01/10
     * 删除一个菜单项
     * */

    @Override
    public void deleteMenuItem(String MenuId) throws NotExistException, NotStoreOwnerException {

        if(menuItemMapper.selectById(MenuId)==null)
            throw new NotExistException("不存在该菜单项");

        MenuItem menuitem= menuItemMapper.selectById(MenuId);   //通过当前的菜单id获得菜单项
        String uid = jwtUtil.extractUidSubject(this.request);  //获得当前的用户id

        Store store = storeMapper.selectById(menuitem.getStoreId());  //根据菜单项获得店铺id，即赋值一个店铺

        if(!store.getOwnerId().equals(uid))
            throw new NotStoreOwnerException("该用户不是菜单项所属店铺的用户，无法删除");

        menuItemMapper.deleteById(MenuId);

    }





}
