package com.ruanwell.mapper;

import com.ruanwell.bean.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/11.
 */
@Mapper
public interface RoleMapper {

    List<Role> getAllRoles();
}
