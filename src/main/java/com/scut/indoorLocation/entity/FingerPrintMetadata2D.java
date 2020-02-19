package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Mingor on 2020/2/18 20:00
 */
@Data
@Builder
@TableName("fingerprint_metadata_2d")
public class FingerPrintMetadata2D {
    @TableId(value = "meta_id", type = IdType.ASSIGN_UUID)
    private String metaId;

    private String userId;

    private String bssid1;

    private String bssid2;

    private String bssid3;

    private LocalDateTime createTime;

}
