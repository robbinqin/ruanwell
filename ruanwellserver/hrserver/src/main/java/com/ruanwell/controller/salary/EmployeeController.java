package com.ruanwell.controller.salary;

import com.ruanwell.bean.Employee;
import com.ruanwell.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robbinqin on 2018/4/13.
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    public Map<String,Object> getAllEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>();
        List<Employee> emps = employeeService.getAllEmployeeByPage(page, size);
        Integer totalCount = employeeService.getEmpsCount();
        map.put("emps", emps);
        map.put("totalCount", totalCount);
        return map;
    }
}
