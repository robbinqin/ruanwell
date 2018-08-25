package com.ruanwell.mapper;

import com.ruanwell.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/13.
 */
@Mapper
public interface EmployeeMapper {
    List<Employee> getAllEmployeeByPage(@Param("start") int start, @Param("size") Integer size);

    Integer getEmpsCount();
}
