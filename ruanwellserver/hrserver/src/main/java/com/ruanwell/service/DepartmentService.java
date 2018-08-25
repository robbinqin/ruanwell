package com.ruanwell.service;

import com.ruanwell.bean.Department;
import com.ruanwell.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/12.
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    public List<Department> getDepartmentsByParentId(Long pid) {
        return departmentMapper.getDepartmentsByParentId(pid);
    }

    public int addDep(Department department) {
        department.setEnabled(true);
        departmentMapper.addDep(department);
        return department.getResult();
    }

    public List<Department> allDeps() {
        return departmentMapper.allDeps();
    }

    public int deleteDepById(Department department) {
        departmentMapper.deleteDepById(department);
        return department.getResult();
    }
}
