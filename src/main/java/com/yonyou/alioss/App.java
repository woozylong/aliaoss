package com.yonyou.alioss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/**
 * Hello world!
 *
 */
public class App 
{
	
    private static OSSClient client;
    private static String key = "mUyRskh2lbq5jCff";
    private static String secret = "pDNa6muwgh9bdt5FBN4DjQkfHLxV1Z";
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";// 以北京为例
	
    static{
    	
    	
    	// 创建ClientConfiguration实例
    	ClientConfiguration conf = new ClientConfiguration();
    	// 配置代理为本地8080端口
//    	conf.setProxyHost("127.0.0.1");
//    	conf.setProxyPort(8090);
    	// 设置HTTP最大连接数为10
    	conf.setMaxConnections(10);
    	// 设置TCP连接超时为5000毫秒
    	conf.setConnectionTimeout(5000);
    	// 设置最大的重试次数为3
    	conf.setMaxErrorRetry(3);
    	// 设置Socket传输数据超时的时间为2000毫秒
    	conf.setSocketTimeout(2000);
    	
    	client = new OSSClient(endpoint, key, secret,conf);
    }
	public static void main( String[] args ) throws FileNotFoundException
    {
    	putObject("woozylong1", key, "D:\\BugReport.txt");
    }
    
    public static void putObject(String bucketName, String key, String filePath) throws FileNotFoundException {
        // 获取指定文件的输入流
        File file = new File(filePath);
        InputStream content = new FileInputStream(file);
        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(file.length());

        // 上传Object.
        PutObjectResult result = client.putObject(bucketName, key, content, meta);

        // 打印ETag
        System.out.println(result.getETag());
    }
}
