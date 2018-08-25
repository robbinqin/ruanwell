package com.ruanwell.service;

import com.ruanwell.bean.Employee;
import com.ruanwell.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/13.
 */
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    public List<Employee> getAllEmployeeByPage(Integer page, Integer size) {
        int start = (page - 1) * size;
        return employeeMapper.getAllEmployeeByPage(start,size);
    }

    public Integer getEmpsCount() {
        return employeeMapper.getEmpsCount();
    }
}
