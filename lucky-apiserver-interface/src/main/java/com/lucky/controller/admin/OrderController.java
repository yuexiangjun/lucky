package com.lucky.controller.admin;

import com.lucky.application.OrderServer;
import com.lucky.controller.admin.dto.OrderDTO;
import com.lucky.controller.admin.dto.StatusDTO;
import com.lucky.controller.admin.vo.OrderVO;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户
 *
 * @folder API/后台/订单记录
 */
@RestController
@RequestMapping("/admin/order")
public class OrderController {
	private final OrderServer orderServer;

	public OrderController(OrderServer orderServer) {
		this.orderServer = orderServer;
	}


	/**
	 * 场次的抽奖记录
	 * 列表
	 */
	@PostMapping("/list")
	@ResponseFormat
	public List<OrderVO> list(@RequestBody OrderDTO dto) {

		var entity = OrderDTO.toEntity(dto);

		return orderServer.list(entity)
				.stream()
				.map(OrderVO::getInstance)
				.collect(Collectors.toList());
	}


	/**
	 * 修改订单状态
	 */
	@PutMapping("/status")
	@ResponseFormat
	public void updateStatus(@RequestBody StatusDTO dto) {

		orderServer.updateStatus(dto.getId(), dto.getStatus());
	}
}
