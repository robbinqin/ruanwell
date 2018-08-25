package com.ruanwell.service;

import com.ruanwell.bean.Position;
import com.ruanwell.mapper.PositionMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/11.
 */
@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public int addPosition(String name) {
        return positionMapper.addPosition(name);
    }

    public boolean deletePostionByIds(String ids) {
        String[] split = ids.split(",");
        int result = positionMapper.deletePositionByIds(split);
        return result == split.length;
    }

    public int updatePositionById(String name, Long id) {
        return positionMapper.updatePositionById(name, id);
    }
}
