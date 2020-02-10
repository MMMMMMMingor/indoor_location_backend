package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 店铺收藏
 * Created by Mingor on 2019/12/31 20:43
 */
@Data
@Builder
@TableName("collection")
public class Collection {

    @TableId(value = "collection_id", type = IdType.ASSIGN_UUID)
    private String collectionId;

    private String userId;

    private String storeId;

    private LocalDateTime createTime;

}
