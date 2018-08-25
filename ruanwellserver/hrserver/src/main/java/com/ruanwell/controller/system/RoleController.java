package com.ruanwell.controller.system;

import com.ruanwell.bean.Menu;
import com.ruanwell.bean.RespBean;
import com.ruanwell.bean.Role;
import com.ruanwell.service.MenuService;
import com.ruanwell.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robbinqin on 2018/4/13.
 */
@RestController
@RequestMapping("/system/basic")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/menus/{rid}")
    public Map<String, Object> getAllMenus(@PathVariable Long rid) {
        Map<String, Object> map = new HashMap<>();
        List<Menu> menus = menuService.getAllMenus2();
        map.put("menus", menus);
        List<Long> ids = menuService.getAllMenusIdByRid(rid);
        map.put("ids", ids);
        return map;
    }

    @PutMapping("/role")
    public RespBean updateRole(Long[] mids, Long rid) {
        if (menuService.updateMenuRole(mids, rid)) {
            return new RespBean("success", "更新成功!");
        }
        return new RespBean("error", "更新失败!");
    }

//    @RequestMapping(value = "/menus/{rid}",method = RequestMethod.GET)
//    public List<Long> getAllMenusIdByRid(@PathVariable Long rid) {
//        return ids;
//    }
}
