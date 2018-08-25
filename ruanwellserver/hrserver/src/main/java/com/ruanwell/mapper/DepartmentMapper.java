package com.ruanwell.mapper;

import com.ruanwell.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/12.
 */
@Mapper
public interface DepartmentMapper {
    List<Department> getDepartmentsByParentId(Long pid);

    void addDep(@Param("dep") Department department);

    List<Department> allDeps();

    void deleteDepById(@Param("dep") Department department);
}
