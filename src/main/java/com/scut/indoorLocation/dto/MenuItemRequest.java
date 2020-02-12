package com.scut.indoorLocation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2019/12/31 10:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemRequest {

    @ApiModelProperty(value = "店铺ID", name = "storeId", example = "36ed8eba21765f8595fe8d45b7892ba6 ")
    private String storeId;

    @ApiModelProperty(value = "菜单名", name = "itemName", example = "烤全羊")
    private String itemName;

    @ApiModelProperty(value = "介绍", name = "introduction", example = "美味多汁爽口")
    private String introduction;

    @ApiModelProperty(value = "价格", name = "price", example = "999")
    private Integer price;

    @ApiModelProperty(value = "价格", name = "imageUrl", example = "http://39.99.131.85/images/b326cdad97ca4dd79012c73886fd247c.jpeg")
    private String imageUrl;

    /**
     * Add by hxy 2020/02/11
     */
    @ApiModelProperty(value = "菜单ID", name = "MenuId", example = "53c25fe9d88b27e0c5d10dc418068e09")
    private String MenuId;
}
