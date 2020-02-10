package com.scut.indoorLocation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.entity.Friend;
import com.scut.indoorLocation.exception.CreateException;

/**
 * Created by Mingor on 2020/1/1 11:24
 */
public interface FriendService {

    /**
     * 添加商铺收藏信息
     * @param friendId 好友ID
     */
    void createCollection(String friendId) throws CreateException;

    /**
     * 分页查询收藏信息
     * @param pageNO 页号
     * @param pageSize 页大小
     * @return 分页后的收藏信息
     */
    IPage<Friend> queryByPage(Long pageNO, Long pageSize);
}
