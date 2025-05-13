package com.lucky.controller.external;

import com.lucky.application.DeliveryAddressServer;
import com.lucky.controller.external.dto.DeliveryAddressDTO;
import com.lucky.controller.external.vo.DeliveryAddressVO;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 收货地址信息
 *
 * @folder API/小程序/收货地址信息
 */
@RequestMapping("/wechat/delivery-address")
@RestController
public class DeliveryAddressController {
    private final DeliveryAddressServer deliveryAddressServer;

    public DeliveryAddressController(DeliveryAddressServer deliveryAddressServer) {
        this.deliveryAddressServer = deliveryAddressServer;
    }


    /**
     * 添加修改
     *
     * @return
     */
    @ResponseFormat
    @PostMapping()
    public void saveOrUpdate(@RequestBody DeliveryAddressDTO dto) {
        var entity = DeliveryAddressDTO.toEntity(dto);
        deliveryAddressServer.saveOrUpdate(entity);

    }


    /**
     * 更据id删除
     */
    @ResponseFormat
    @DeleteMapping()
    public void deleteById(@RequestParam Long id) {
        deliveryAddressServer.deleteById(id);
    }

    /**
     * 更据用户查询
     */
    @ResponseFormat
    @GetMapping("/list")
    public List<DeliveryAddressVO> getByWechatUserId(@RequestParam Long wechatUserId) {
        return deliveryAddressServer.getByWechatUserId(wechatUserId)
                .stream()
                .map(DeliveryAddressVO::getInstance)
                .collect(Collectors.toList());
    }

    /**
     * 修改默认地址
     */
    @PutMapping("/default")
    @ResponseFormat
    public  void  updateDefault(@RequestParam Long id, @RequestParam Long wechatUserId) {
         deliveryAddressServer.updateDefault(id, wechatUserId);

    }


}
