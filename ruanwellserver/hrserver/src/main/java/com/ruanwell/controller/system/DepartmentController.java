package com.ruanwell.controller.system;

import com.ruanwell.bean.Department;
import com.ruanwell.bean.RespBean;
import com.ruanwell.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robbinqin on 2018/4/12.
 */
@RestController
@RequestMapping("/system/basic")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/dep")
    public List<Department> getDepartmentsByParentId(@RequestParam(defaultValue = "-1") Long pid) {
        return departmentService.getDepartmentsByParentId(pid);
    }

    @PostMapping("/dep")
    public Map<String, Object> addDep(Department department) {
        Map<String, Object> map = new HashMap<>();
        if (departmentService.addDep(department) == 1) {
            map.put("status", "success");
            map.put("msg", department);
            return map;
        }
        map.put("status", "error");
        map.put("msg", "添加失败!");
        return map;
    }

    @RequestMapping(value = "/deps", method = RequestMethod.GET)
    public List<Department> allDeps() {
        return departmentService.allDeps();
    }

    @DeleteMapping("/dep")
    public RespBean deleteDepById(Department department) {
        int result = departmentService.deleteDepById(department);
        if (result == -1) {
            return new RespBean("error", "该部门下尚有关联的员工，部门删除失败!");
        } else if (result == 1) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
}
