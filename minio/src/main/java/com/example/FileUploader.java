package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import io.minio.PutObjectOptions;
import io.minio.errors.*;
import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;

public class FileUploader {
    public static void main(String[] args) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException, InvalidResponseException, InsufficientDataException, InvalidPortException, InvalidExpiresRangeException, ErrorResponseException, XmlParserException, InvalidBucketNameException, InvalidEndpointException, InternalException {
        FileUploader.fileUp();
        FileUploader.fileDown();
    }
    /**
     * 上传文件
     *
     * @return 文件链接地址
     */
    public static void fileUp() throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient("https://play.min.io", "Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("asiatrip");
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("asiatrip");
            }
            InputStream inputStream = new FileInputStream("E:\\Nodes\\gulimall\\谷粒商城\\课件+笔记+源码\\课件和文档(老版)\\基础篇\\资料\\pics\\0d40c24b264aa511.jpg");
            // 使用putObject上传一个文件到存储桶中。
//            minioClient.putObject("asiatrip","0d40c24b264aa511.jpg", "E:\\Nodes\\gulimall\\谷粒商城\\课件+笔记+源码\\课件和文档(老版)\\基础篇\\资料\\pics\\0d40c24b264aa511.jpg");
            minioClient.putObject("airstrip","0d40c24b264aa511.jpg",inputStream,new PutObjectOptions(inputStream.available(), -1));

            String asiatrip = minioClient.getObjectUrl("asiatrip", "0d40c24b264aa511.jpg");
            System.out.println(asiatrip);
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }


    /**
     * 获取文件外链
     */
    public static void fileDown() throws InvalidPortException, InvalidEndpointException, IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, InvalidExpiresRangeException, InternalException, NoSuchAlgorithmException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        MinioClient minioClient = new MinioClient("https://play.min.io", "Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");
        String url = minioClient.presignedGetObject("asiatrip", "0d40c24b264aa511.jpg", 30);
        System.out.println(url);
    }
}
