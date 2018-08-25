package com.ruanwell.controller.system;

import com.ruanwell.bean.Hr;
import com.ruanwell.bean.RespBean;
import com.ruanwell.bean.Role;
import com.ruanwell.service.HrService;
import com.ruanwell.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/11.
 */
@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;
    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public List<Hr> getHrByKeywords(@RequestParam(defaultValue = "") String keywords) {
        return hrService.getHrByKeywords(keywords);
    }

    @PutMapping("/")
    public RespBean updateHrStateById(boolean enabled, Long hrid) {
        int result = hrService.updateHrStateById(enabled, hrid);
        if (result == 1) {
            return new RespBean("success", "更新成功!");
        }
        return new RespBean("error", "更新失败!");
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @RequestMapping(value = "/roles", method = RequestMethod.PUT)
    public RespBean updateHrRoles(Long hrid, Long[] roles) {
        boolean flag = hrService.updateHrRoles(hrid, roles);
        if (flag) {
            return new RespBean("success", "修改成功!");
        }
        return new RespBean("error", "修改失败!");
    }
}