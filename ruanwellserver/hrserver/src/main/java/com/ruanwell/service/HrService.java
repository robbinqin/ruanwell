package com.ruanwell.service;

import com.ruanwell.bean.Hr;
import com.ruanwell.bean.Role;
import com.ruanwell.mapper.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/8.
 */
@Service
@Transactional
public class HrService implements UserDetailsService {
    @Autowired
    HrMapper hrMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(s);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<Role> roles = hrMapper.getHrRolesByHrId(hr.getId());
        hr.setRoles(roles);
        return hr;
    }

    public List<Hr> getHrByKeywords(String keywords) {
        return hrMapper.getHrByKeywords(keywords);
    }

    public int updateHrStateById(boolean enabled, Long hrid) {
        return hrMapper.updateHrStateById(enabled,hrid);
    }

    public boolean updateHrRoles(Long hrid, Long[] roles) {
        hrMapper.deleteRolesByHrId(hrid);
        return hrMapper.updateHrRoles(hrid, roles)==roles.length;
    }
}
