package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.indoorLocation.dto.StoreInfoRequest;
import com.scut.indoorLocation.entity.Store;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.NotStoreOwnerException;
import com.scut.indoorLocation.mapper.StoreMapper;
import com.scut.indoorLocation.service.StoreService;
import com.scut.indoorLocation.utility.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Created by Mingor on 2019/12/30 22:09
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private StoreMapper storeMapper;

    @Override
    public void createStore(StoreInfoRequest param) throws CreateException {
        String uid = jwtUtil.extractUidSubject(request);

        Store store = Store.builder()
                .ownerId(uid)
                .storeName(param.getStoreName())
                .address(param.getAddress())
                .businessTime(param.getBusinessTime())
                .createTime(LocalDateTime.now())
                .build();

        if (storeMapper.insert(store) != 1)
            throw new CreateException("商铺创建异常");
    }

    @Override
    public IPage<Store> queryStoresByPage(Long pageNo, Long pageSize) {
        Page<Store> page = new Page<>(pageNo, pageSize);
        return storeMapper.selectPage(page, null);
    }

    @Override
    public void modifyStoreInfo(StoreInfoRequest param) throws NotStoreOwnerException {
        //提取uid
        String uid = jwtUtil.extractUidSubject(this.request);

        Store store = storeMapper.selectById(param.getStoreId());
        if(!store.getOwnerId().equals(uid))
            throw new NotStoreOwnerException("该用户不是店铺的所有人");

        Store newStore = Store.builder()
                .storeName(param.getStoreName() == null ? store.getStoreName() : param.getStoreName())
                .address(param.getAddress() == null ? store.getAddress() : param.getAddress())
                .businessTime(param.getBusinessTime() == null ? store.getBusinessTime() : param.getBusinessTime())
                .build();

        storeMapper.updateById(newStore);
    }


}
