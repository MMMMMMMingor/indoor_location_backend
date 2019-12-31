package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Mingor on 2019/12/30 21:59
 */
@Data
@Builder
@TableName("store")
public class Store {

    @TableId(value = "store_id", type = IdType.ASSIGN_UUID)
    private String storeId;

    private String ownerId;

    private String storeName;

    private String address;

    private String businessTime;

    @JsonIgnore
    private LocalDateTime createTime;
}
