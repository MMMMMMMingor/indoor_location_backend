package com.scut.indoorLocation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scut.indoorLocation.entity.UserBasic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Mingor on 2019/11/18 23:36
 */
public interface UserBasicMapper extends BaseMapper<UserBasic> {

    @Select("SELECT user_id FROM user_basic WHERE username = #{username}")
    String getUserIdByName(@Param("username") String username);

}
