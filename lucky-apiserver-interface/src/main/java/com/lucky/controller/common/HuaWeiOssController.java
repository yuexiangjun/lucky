package com.lucky.controller.common;


import com.lucky.application.tripartite.HuaWeiOssServer;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传
 *
 * @folder API/公共/图片上传
 */
@RestController
@RequestMapping("/oss")
public class HuaWeiOssController {
	private final HuaWeiOssServer HuaWeiOssServer;

	public HuaWeiOssController(com.lucky.application.tripartite.HuaWeiOssServer huaWeiOssServer) {
		HuaWeiOssServer = huaWeiOssServer;
	}


	/**
	 * 上传图片
	 *
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	@ResponseFormat
	public String upload(@RequestPart("file")MultipartFile file) {
		return HuaWeiOssServer.upload(file);
	}

}
