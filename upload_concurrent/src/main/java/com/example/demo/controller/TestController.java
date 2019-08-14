package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.utils.ConCurrent;

@Controller
public class TestController {
	
	@RequestMapping("upload")
	@ResponseBody
	public String uploadTest(MultipartFile[] mFile) {
		ConCurrent.uploadconcurrent(mFile);
		return "ok";
	}
}
