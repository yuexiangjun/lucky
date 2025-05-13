package com.lucky.controller.admin;

import com.lucky.application.LogisticsOrderServer;
import com.lucky.controller.admin.dto.LogisticsOrderInfoDTO;
import com.lucky.controller.admin.dto.UpdateLogisticsOrderDTO;
import com.lucky.controller.external.vo.LogisticsOrderInfoVO;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 物流订单
 *
 * @folder API/后台/物流订单列表
 */
@RequestMapping("/admin/logistics-order")
@RestController
public class LogisticsOrderController {
    private final LogisticsOrderServer logisticsOrderServer;

    public LogisticsOrderController(LogisticsOrderServer logisticsOrderServer) {
        this.logisticsOrderServer = logisticsOrderServer;
    }


    /**
     * 获取物流订单列表
     */
    @PostMapping("/list")
    @ResponseFormat
    public List<LogisticsOrderInfoVO> list(@RequestBody LogisticsOrderInfoDTO logisticsOrderInfo) {
        var logisticsOrderEntity = LogisticsOrderInfoDTO.toEntity(logisticsOrderInfo);
        return logisticsOrderServer.getByAdminList(logisticsOrderEntity)
                .stream()
                .map(LogisticsOrderInfoVO::getInstance)
                .collect(Collectors.toList());
    }

    /**
     * 修改物流订单 包含修改状态
     */
    @PostMapping("/update")
    @ResponseFormat
    public void updateLogisticsOrder(@RequestBody UpdateLogisticsOrderDTO updateLogisticsOrderDTO) {
        var logisticsOrderEntity = UpdateLogisticsOrderDTO.toEntity(updateLogisticsOrderDTO);
        logisticsOrderServer.updateLogisticsOrder(logisticsOrderEntity);
    }


}
