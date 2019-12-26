package com.scut.indoorLocation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2019/12/26 23:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAndPassRequest {

    private String username;

    private String password;

}
