package com.example.demo.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.example.demo.constant.UploadImgConstant;

import org.springframework.web.multipart.MultipartFile;


public class UploadImg {
	public static boolean upload(MultipartFile mFilter) {
		if (mFilter!=null) {
				File file = null;
				String fileName = mFilter.getOriginalFilename();
				
	            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
	            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
	            String timeDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	            String currentPath = UploadImgConstant.imgpath+"/"+timeDir;
				File f = new File(currentPath);
				if (!f.exists() && !f.isDirectory()) {
					f.mkdirs();
				}
				file = new File(currentPath,fileName);
				try {
					mFilter.transferTo(file);
					System.out.println("【"+file+"】>>>>上传完成<<<<");
					return true;
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return false;
	}
}
