package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Mingor on 2019/12/30 21:59
 */
@Data
@Builder
@TableName("store")
public class Store {

    @TableId(value = "store_id", type = IdType.ASSIGN_UUID)
    private String storeId;

    @TableField(value = "owner_id")
    private String ownerId;

    @TableField(value = "store_name")
    private String storeName;

    private String address;

    @TableField(value = "business_time")
    private String businessTime;

}
