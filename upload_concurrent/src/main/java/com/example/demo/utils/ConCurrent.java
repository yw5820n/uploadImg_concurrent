package com.example.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConCurrent {
	public static boolean uploadconcurrent(MultipartFile [] mFile) {
		ExecutorService service = Executors.newCachedThreadPool();//创建线程池
		final Integer count = mFile.length;//获取文件数
		final CountDownLatch dataLatch = new CountDownLatch(count);//创建文件计数器
		final CountDownLatch isExecute = new CountDownLatch(1);//觉得是否执行
		for(MultipartFile m : mFile) {
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try {
						isExecute.await();
						long startTime = System.currentTimeMillis();
						 boolean state = UploadImg.upload(m);
						 if(state) {
							 long endTime = System.currentTimeMillis();
							 System.out.println(Thread.currentThread().getName()+"》》》接收任务");
							 System.out.println("执行时间："+(endTime - startTime));
						 }
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						dataLatch.countDown();//执行完就删除一个
					}
				}
			};
			service.execute(runnable);
		}
		try {
			Thread.sleep(3000);
			isExecute.countDown();//决定开始执行
			dataLatch.await();//等待线程执行完毕
			System.out.println("【"+Thread.currentThread().getName()+"】>>>>执行完毕");
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			service.shutdown();
		}
		return false;
	}
}
