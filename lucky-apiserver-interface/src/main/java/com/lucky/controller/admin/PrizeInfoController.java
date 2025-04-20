package com.lucky.controller.admin;

import com.lucky.application.PrizeInfoServer;
import com.lucky.controller.admin.dto.GradeDTO;
import com.lucky.controller.admin.dto.PrizeInfoDTO;
import com.lucky.domain.entity.PrizeInfoEntity;
import com.lucky.domain.exception.BusinessException;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 奖品
 * @folder API/后台/奖品设置
 */
@RestController
@RequestMapping("/prize-info")
public class PrizeInfoController {
    private final PrizeInfoServer prizeInfoServer;

    public PrizeInfoController(PrizeInfoServer prizeInfoServer) {
        this.prizeInfoServer = prizeInfoServer;
    }


    /**
     * 添加
     */
    @PostMapping
    @ResponseFormat
    public  void  save(@RequestBody PrizeInfoDTO dto) {
        var entity = PrizeInfoDTO.toEntity(dto);

        var id = prizeInfoServer.saveOrUpdate(entity);
        if (Objects.isNull(id))
            throw BusinessException.newInstance("添加失败");

    }

    /**
     * 修改
     */
    @PutMapping
    @ResponseFormat
    public  void  update(@RequestBody PrizeInfoDTO dto) {
        var entity = PrizeInfoDTO.toEntity(dto);

        if (Objects.isNull(entity.getId()))
            throw BusinessException.newInstance("缺少id参数");

        var id = prizeInfoServer.saveOrUpdate(entity);
        if (Objects.isNull(id))
            throw BusinessException.newInstance("修改失败");

    }

    /**
     * 删除
     */
    @ResponseFormat
    @DeleteMapping
    public void deleteById(@RequestParam  Long id) {
         var aBoolean = prizeInfoServer.deleteById(id);
        if (aBoolean)
            throw BusinessException.newInstance("删除失败");
    }
}
