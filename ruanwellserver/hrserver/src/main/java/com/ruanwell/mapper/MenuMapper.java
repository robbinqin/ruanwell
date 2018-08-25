package com.ruanwell.mapper;

import com.ruanwell.bean.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/9.
 */
@Mapper
public interface MenuMapper {
    List<Menu> getMenusByHrId(Long hrid);

    List<Menu> getAllMenus();

    List<Menu> getAllMenus2();

    List<Long> getAllMenusIdByRid(Long rid);

    void deleteMenusByRid(Long rid);

    int updateMenuRole(@Param("mids") Long[] mids, @Param("rid") Long rid);
}
