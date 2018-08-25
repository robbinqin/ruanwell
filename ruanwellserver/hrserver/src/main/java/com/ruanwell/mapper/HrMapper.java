package com.ruanwell.mapper;

import com.ruanwell.bean.Hr;
import com.ruanwell.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/8.
 */
@Mapper
public interface HrMapper {
    Hr loadUserByUsername(String username);

    List<Role> getHrRolesByHrId(Long hrid);

    List<Hr> getHrByKeywords(String keywords);

    int updateHrStateById(@Param("enabled") boolean enabled, @Param("hrid") Long hrid);

    void deleteRolesByHrId(Long hrid);

    int updateHrRoles(@Param("hrid") Long hrid, @Param("roles") Long[] roles);
}
