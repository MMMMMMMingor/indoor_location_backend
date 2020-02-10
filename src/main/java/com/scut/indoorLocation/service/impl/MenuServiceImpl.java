package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.indoorLocation.dto.MenuItemRequest;
import com.scut.indoorLocation.entity.MenuItem;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.mapper.MenuItemMapper;
import com.scut.indoorLocation.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Mingor on 2019/12/31 10:24
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuItemMapper menuItemMapper;


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

}
