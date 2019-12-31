package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * 菜单项
 * Created by Mingor on 2019/12/31 10:04
 */
@Data
@Builder
@TableName("menu_item")
public class MenuItem {

    @TableId(value = "menu_id", type = IdType.ASSIGN_UUID)
    private String menuId;

    @TableField(value = "store_id")
    private String storeId;

    @TableField(value = "item_name")
    private String itemName;

    private String introduction;

    private Integer price;

    @TableField(value = "image_url")
    private String imageUrl;
}
