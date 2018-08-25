package com.ruanwell.controller;

import com.ruanwell.bean.Menu;
import com.ruanwell.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 返回系统的一些配置信息，这些配置信息不涉及到角色，即登录后就能访问
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/menus")
    public List<Menu> getMenusByHrId() {
        return menuService.getMenusByHrId();
    }
}
