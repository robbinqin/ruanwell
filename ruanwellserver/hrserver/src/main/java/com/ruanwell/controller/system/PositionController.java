package com.ruanwell.controller.system;

import com.ruanwell.bean.Position;
import com.ruanwell.bean.RespBean;
import com.ruanwell.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by robbinqin on 2018/4/11.
 */
@RestController
@RequestMapping("/system/basic")
public class PositionController {
    @Autowired
    PositionService positionService;

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @PostMapping("/position")
    public RespBean addPosition(String name) {
        int result = positionService.addPosition(name);
        if (result == 1) {
            return new RespBean("success", "添加成功!");
        }
        return new RespBean("error", "添加失败!");
    }

    @DeleteMapping("/position/{ids}")//1,2,3,4
    public RespBean deletePostionByIds(@PathVariable String ids) {
        boolean result = positionService.deletePostionByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @PutMapping("/position")
    public RespBean updatePositionById(String name, Long id) {
        int result = positionService.updatePositionById(name, id);
        if (result == 1) {
            return new RespBean("success", "修改成功!");
        }
        return new RespBean("error", "修改失败!");
    }
}
