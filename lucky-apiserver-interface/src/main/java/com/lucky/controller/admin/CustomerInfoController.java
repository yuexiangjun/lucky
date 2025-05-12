package com.lucky.controller.admin;

import com.lucky.application.CustomerInfoServer;
import com.lucky.controller.admin.dto.CustomerInfoDTO;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客服信息
 *
 * @folder API/后台/客服信息
 */
@RequestMapping("/admin/customer")
@RestController
public class CustomerInfoController {
    private final CustomerInfoServer customerInfoServer;

    public CustomerInfoController(CustomerInfoServer customerInfoServer) {
        this.customerInfoServer = customerInfoServer;
    }


    /**
     * 添加修改
     */
    @PostMapping()
    @ResponseFormat
    public void saveOrUpdate(@RequestBody CustomerInfoDTO dto) {
        var entity = CustomerInfoDTO.toEntity(dto);
        customerInfoServer.saveOrUpdate(entity);
    }


}
