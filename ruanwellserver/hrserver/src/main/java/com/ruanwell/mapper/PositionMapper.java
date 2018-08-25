package com.ruanwell.mapper;

import com.ruanwell.bean.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/11.
 */
@Mapper
public interface PositionMapper {
    List<Position> getAllPositions();

    int addPosition(String name);

    int deletePositionByIds(@Param("ids") String[] split);

    int updatePositionById(@Param("name") String name, @Param("id") Long id);
}
