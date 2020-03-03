package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scut.indoorLocation.dto.FingerPrint2D;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Mingor on 2020/2/18 20:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("fingerprint_metadata_2d")
public class FingerPrintMetadata2D {

    @TableId(value = "meta_id")
    private String metaId;

    private String userId;

    private String remark;

    @JsonIgnore
    @TableField(exist = false)
    private List<AccessPoint> accessPoints;

    @JsonIgnore
    @TableField(exist = false)
    private List<FingerPrint2D> fingerPrint2DList;

    private LocalDateTime createTime;

}
