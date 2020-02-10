package com.scut.indoorLocation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2019/12/30 21:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreInfoRequest {

    @ApiModelProperty(value = "店铺ID", name = "storeId", example = "54f300588c849624b9cc4910482afb99")
    private String storeId;

    @ApiModelProperty(value = "店铺名", name = "storeName", example = "原始烧烤")
    private String storeName;

    @ApiModelProperty(value = "地址", name = "address", example = "小谷围贝岗村中二横路1151号")
    private String address;

    @ApiModelProperty(value = "营业时间", name = "businessTime", example = "周一-周日11:00-21:00")
    private String businessTime;

}
