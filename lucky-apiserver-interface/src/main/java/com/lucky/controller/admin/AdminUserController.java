package com.lucky.controller.admin;


import com.lucky.application.AdminUserServer;
import com.lucky.application.interceptor.LoginUserEntity;
import com.lucky.controller.admin.dto.AdminUserDTO;
import com.lucky.controller.admin.dto.AdminUserFindListDTO;
import com.lucky.controller.admin.dto.EnabledDTO;
import com.lucky.controller.admin.dto.LoginDTO;
import com.lucky.controller.admin.vo.AdminUserVO;
import com.lucky.domain.entity.AdminUserEntity;
import com.lucky.domain.exception.BusinessException;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 后台用户
 *
 * @folder API/后台/用户
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
	private final AdminUserServer adminUserServer;

	public AdminUserController(AdminUserServer adminUserServer) {
		this.adminUserServer = adminUserServer;
	}

	/**
	 * 添加
	 *
	 * @param dto
	 * @return
	 */
	@PostMapping("/save")
	@ResponseFormat
	public void save(@RequestBody AdminUserDTO dto) {
		var entity = AdminUserDTO.toEntity(dto);
		var id = adminUserServer.saveOrUpdate(entity);
		if (Objects.isNull(id))
			throw BusinessException.newInstance("添加失败");
	}

	/**
	 * 修改
	 *
	 * @param dto
	 * @return
	 */
	@PutMapping("/update")
	@ResponseFormat
	public void update(@RequestBody AdminUserDTO dto) {
		var entity = AdminUserDTO.toEntity(dto);
		var id = adminUserServer.saveOrUpdate(entity);
		if (Objects.isNull(id))
			throw BusinessException.newInstance("修改失败");
	}

	/**
	 * 列表
	 *
	 * @param adminUserFindListDTO
	 * @return
	 */
	@PostMapping("/list")
	@ResponseFormat
	public List<AdminUserVO> list(@RequestBody AdminUserFindListDTO adminUserFindListDTO) {
		var adminUserEntity = AdminUserEntity.builder()
				.name(adminUserFindListDTO.getName())
				.phone(adminUserFindListDTO.getPhone())
				.build();
		return adminUserServer.list(adminUserEntity).stream()
				.map(AdminUserVO::getInstance)
				.collect(Collectors.toList());
	}

	/**
	 * 启用禁用
	 */
	@PutMapping("/enabled")
	@ResponseFormat
	public void enabled(@RequestBody EnabledDTO enabledDTO) {

		adminUserServer.enabled(enabledDTO.getId(), enabledDTO.getEnabled());

	}

	/**
	 * 登录
	 */
	@PostMapping("/login")
	@ResponseFormat
	public LoginUserEntity login(@RequestBody LoginDTO loginDTO) {
		return adminUserServer.login(loginDTO.getPhone(), loginDTO.getPassword());
	}
}
