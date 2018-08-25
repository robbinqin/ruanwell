package com.ruanwell.service;

import com.ruanwell.bean.Hr;
import com.ruanwell.bean.Menu;
import com.ruanwell.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/9.
 */
@Service
@Transactional
public class MenuService {

    @Autowired
    MenuMapper menuMapper;
    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }

    public List<Menu> getAllMenus2() {
        return menuMapper.getAllMenus2();
    }

    public List<Long> getAllMenusIdByRid(Long rid) {
        return menuMapper.getAllMenusIdByRid(rid);
    }

    public boolean updateMenuRole(Long[] mids, Long rid) {
        menuMapper.deleteMenusByRid(rid);
        return menuMapper.updateMenuRole(mids,rid)==mids.length;
    }
}
