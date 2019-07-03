package com.comm.image.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;


@RestController
@RequestMapping("/aliyun")
public class AliyunController {


    @Autowired
    OSSClient ossClient;

    @GetMapping("listFile")
    @ResponseBody
    public void testListFile(@RequestParam("bucketName") String bucketName) throws Exception {

// ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
        ObjectListing objectListing = ossClient.listObjects(bucketName);
// objectListing.getObjectSummaries获取所有文件的描述信息。l
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " +
                    "(size = " + objectSummary.getSize() + ")");
        }

// 关闭OSSClient。
        ossClient.shutdown();
    }


    @GetMapping("upload")
    @ResponseBody
    public String testUpload() {

        String objectName = "test";
        String bucketName = "hcq0510";


// 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        String content = "Hello OSS";
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

// 关闭OSSClient。
        ossClient.shutdown();
        return "上传成功";
    }

    @PostMapping("downloadFile")
    @ResponseBody
    public void testDownload(@RequestParam("bucketName") String bucketName, @RequestParam("fileName") String fileName) throws Exception {


// 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, fileName);
// 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                System.out.println("\n" + line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            content.close();
        }

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @DeleteMapping("deleteFile")
    @ResponseBody
    public void testDelete(@RequestParam("bucketName") String bucketName, @RequestParam("fileName") String fileName) throws Exception {
// 删除文件。
        ossClient.deleteObject(bucketName, fileName);

// 关闭OSSClient。
        ossClient.shutdown();
    }


    @GetMapping("generatePresignedUrl")
    @ResponseBody
    public URL generatePresignedUrl(@RequestParam("bucketName") String bucketName, @RequestParam("fileName") String fileName) throws Exception {


// 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
// 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
        return url;
    }


}
