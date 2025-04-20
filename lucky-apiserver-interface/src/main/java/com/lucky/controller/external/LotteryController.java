package com.lucky.controller.external;

import com.alibaba.fastjson.JSONObject;
import com.lucky.application.LotteryServer;
import com.lucky.controller.external.dto.PayDTO;
import com.lucky.controller.external.vo.SuccessProductsVO;
import com.lucky.domain.valueobject.PayInfo;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 支付
 *
 * @folder API/小程序/支付相关
 */
@RequestMapping("/wechat/pay")
@RestController
public class LotteryController {
    private final LotteryServer lotteryServer;

    public LotteryController(LotteryServer lotteryServer) {
        this.lotteryServer = lotteryServer;
    }

    /**
     * 获取准备上面的排队人数
     */
    @GetMapping("/buy/queue/num")
    @ResponseFormat
    public Integer getBuyQueueNum(@RequestParam("topicId") Long topicId,
                                  @RequestParam("sessionId") Long sessionId) {

        return lotteryServer.getQueueNum(topicId, sessionId);
    }

    /**
     * 购买同意场次 排队
     */
    @GetMapping("/buy")
    @ResponseFormat
    public Boolean buy(@RequestParam("topicId") Long topicId,
                       @RequestParam("sessionId") Long sessionId,
                       @RequestParam("wechatUserId") Long wechatUserId) {

        return lotteryServer.buy(topicId, sessionId, wechatUserId);
    }


    /**
     * 抽奖结束
     */
    @GetMapping("/buy/end")
    @ResponseFormat
    public void end(@RequestParam("topicId") Long topicId, @RequestParam("sessionId") Long sessionId) {
        lotteryServer.end(topicId, sessionId);
    }

    /**
     * 支付
     */
    @PostMapping("/pay")
    @ResponseFormat
    public PayInfo pay(@RequestBody PayDTO dto) {

        return lotteryServer.pay(PayDTO.toEntity(dto));
    }

    /**
     * 支付成功后 获取抽取的奖品
     */
    @GetMapping("/pay/success")
    @ResponseFormat
    public List<SuccessProductsVO> successByPrizeInfo(@RequestParam("payOrderId") Long payOrderId) {
        return lotteryServer.successByPrizeInfo(payOrderId)
                .stream()
                .map(SuccessProductsVO::getInstance)
                .collect(Collectors.toList());

    }

    /**
     * 支付回调
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping("/callback")
    public String payCallBack(@RequestBody JSONObject jsonObject) {
        return lotteryServer.payCallBack(jsonObject);
    }

}
