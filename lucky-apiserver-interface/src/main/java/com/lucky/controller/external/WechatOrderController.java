package com.lucky.controller.external;

import com.lucky.application.OrderServer;
import com.lucky.controller.admin.dto.OrderDTO;
import com.lucky.controller.common.BaseController;
import com.lucky.controller.external.dto.PrizePublicityDTO;
import com.lucky.controller.external.vo.PrizePublicityVO;
import com.lucky.domain.valueobject.BaseDataPage;
import com.lucky.domain.valueobject.Order;
import com.lucky.domain.valueobject.PrizePublicity;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 订单记录
 *
 * @folder API/小程序/订单记录
 */
@RestController
@RequestMapping("/wechat/order")
public class WechatOrderController extends BaseController {
    private final OrderServer orderServer;

    public WechatOrderController(OrderServer orderServer) {
        this.orderServer = orderServer;
    }


    /**
     * 抽奖记录
     */
    @PostMapping("/list")
    @ResponseFormat
    public BaseDataPage<Order> list(@RequestBody OrderDTO dto) {

        var entity = OrderDTO.toEntity(dto);

        if (Objects.isNull(entity.getWechatUserId()))
            entity.setWechatUserId(this.getUserId());

        return orderServer.page(entity, dto.getPage(), dto.getSize());

    }

    /**
     * 中奖公示
     */
    @PostMapping("/prize-publicity")
    @ResponseFormat
    public List<PrizePublicityVO> prizePublicity(@RequestBody PrizePublicityDTO dto) {

         var publicityList = orderServer.prizePublicity(dto.getGradeType(),dto.getTopicId());
        return publicityList.stream()
                .map(PrizePublicityVO::toVO)
                .collect(Collectors.toList());
    }


}
