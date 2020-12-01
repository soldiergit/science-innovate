package com.soldier.common.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

/**
 * @Author soldier
 * @Date 20-12-1 上午9:41
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:数据文件上传到OSS，摒弃FileUtils
 */
public class OSSUtils {

    private static String endpoint = "oss-cn-shenzhen.aliyuncs.com";

    private static String accessKeyId = "LTAI0X8ssJ8ezs4C";

    private static String accessKeySecret = "BMbWYHeCOaF8BMCXcgCY2c2TwyPHEs";

    private static String bucketName = "science-innovate";

    private static Logger logger = LoggerFactory.getLogger(OSSUtils.class);

    /**
     * 上传文件（指定路径）：不管文件存不存在，直接新增或替换
     * @param multipartFile     文件
     * @param fileAbsolutePath  文件保存的绝对路径（/home/soldier/Downloads/teacher-business/34/TC/教师logo.jpg）
     */
    public static String upload2OSS(MultipartFile multipartFile, String fileAbsolutePath) {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            if (ossClient.doesBucketExist(bucketName)) {
                logger.info("您已经创建Bucket：" + bucketName);
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + bucketName);
                ossClient.createBucket(bucketName);
            }
            // 上传文件
            ossClient.putObject(bucketName, fileAbsolutePath, multipartFile.getInputStream());
            logger.info("Object：" + fileAbsolutePath + "存入OSS成功。");
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return fileAbsolutePath;
    }

    /**
     * 上传文件（指定路径）：文件重复时，在后来的文件末尾添加数字1 2...以此类推
     * @param multipartFile     文件
     * @param filePrefix        文件名称(例：教师logo.jpg-> filePrefix=教师logo)
     * @param fileSuffix        文件名后缀(例：教师logo.jpg-> fileSuffix=.jpg)
     * @param UPLOAD_FILES_PATH 上传路径
     */
    public static String upload2OSS(MultipartFile multipartFile, String filePrefix, String fileSuffix, String UPLOAD_FILES_PATH) {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 文件保存的绝对路径（/home/soldier/Downloads/teacher-business/34/TC/教师logo.jpg）
        String fileAbsolutePath = UPLOAD_FILES_PATH + filePrefix + fileSuffix;

        try {
            if (ossClient.doesBucketExist(bucketName)) {
                logger.info("您已经创建Bucket：" + bucketName);
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + bucketName);
                ossClient.createBucket(bucketName);
            }
            // 判断文件是否存在，如果存在在文件名则在文件名后面加数字：1、2、3...
            for (int i = 1; ossClient.doesObjectExist(bucketName, fileAbsolutePath) && i < Integer.MAX_VALUE; i++) {
                logger.info("Bucket中文件已存在：" + fileAbsolutePath);
                // 例:/home/soldier/Downloads/teacher-business/34/TC/教师logo(1).jpg
                fileAbsolutePath = UPLOAD_FILES_PATH + filePrefix + '(' + i + ')' + fileSuffix;
            }
            // 上传文件
            ossClient.putObject(bucketName, fileAbsolutePath, multipartFile.getInputStream());
            logger.info("Object：" + fileAbsolutePath + "存入OSS成功。");
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return fileAbsolutePath;
    }

    /**
     * 上传文件（不指定路径）
     * @param multipartFile 文件
     */
    public static String upload2OSS(MultipartFile multipartFile) {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String fileKey = null;

        try {
            if (ossClient.doesBucketExist(bucketName)) {
                logger.info("您已经创建Bucket：" + bucketName + "。");
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + bucketName + "。");
                ossClient.createBucket(bucketName);
            }
            // 文件后缀
            String fileExt = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
            // 确认文件绝对路径
            fileKey = UUID.randomUUID().toString() + "/" + multipartFile.getOriginalFilename() + fileExt;
            // 上传
            ossClient.putObject(bucketName, fileKey, multipartFile.getInputStream());
            logger.info("Object：" + fileKey + "存入OSS成功。");
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return fileKey;
    }

    /**
     * 上传文件
     * @param fileAbsolutePath  文件绝对路径
     * @param inputStream       文件输入流
     */
    public static String upload2OSS(String fileAbsolutePath, InputStream inputStream) {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String fileKey = null;

        try {
            if (ossClient.doesBucketExist(bucketName)) {
                logger.info("您已经创建Bucket：" + bucketName + "。");
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + bucketName + "。");
                ossClient.createBucket(bucketName);
            }
            fileKey = fileAbsolutePath;
            ossClient.putObject(bucketName, fileKey, inputStream);
            logger.info("Object：" + fileKey + "存入OSS成功。");
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return fileKey;
    }

    /**
     * 下载文件
     * @param fileAbsolutePath    文件绝对路径
     * @return  文件输入流
     */
    public static InputStream downloadFromOSS(String fileAbsolutePath) {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        OSSObject ossObject = null;

        try {
            if (ossClient.doesBucketExist(bucketName)) {
                logger.info("您已经创建Bucket：" + bucketName);
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + bucketName);
                ossClient.createBucket(bucketName);
            }
            ossObject = ossClient.getObject(new GetObjectRequest(bucketName, fileAbsolutePath));
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return ossObject.getObjectContent();
    }

    /**
     * 下载文件
     * @param fileAbsolutePath  文件绝对路径
     * @return  文件对象
     */
    public static File downloadFileFromOSS(String fileAbsolutePath) {

        File file = null;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 临时文件
            file = File.createTempFile(fileAbsolutePath, ".tmp");
            // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            ossClient.getObject(new GetObjectRequest(bucketName, fileAbsolutePath), file);
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return file;
    }

    /**
     * 下载文件到浏览器
     * @param response          response对象
     * @param fileAbsolutePath  文件绝对路径
     * @return  文件对象
     */
    public static void downloadFileFromOSS(final HttpServletResponse response, String fileAbsolutePath) {

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
//			try {
//				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}

        File file = downloadFileFromOSS(fileAbsolutePath);
        // 实现文件下载
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
//            InputStream inputStream = OSSUtils.downloadFromOSS(realPath);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileAbsolutePath  文件绝对路径
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileAbsolutePath) {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        boolean flag = false;

        try {
            /*
             * 删除文件
             * 如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
             */
//            ossClient.deleteObject(bucketName, fileAbsolutePath);
            // 如果文件存在进行删除
            if (ossClient.doesObjectExist(bucketName, fileAbsolutePath)) {
                flag = true;
                ossClient.deleteObject(bucketName, fileAbsolutePath);
            } else {
                logger.info("Bucket：《{}》中不存在文件：{}", bucketName, fileAbsolutePath);
            }
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return flag;
    }

    /**
     * 转文件格式
     *
     * @param img 照片文件
     */
    public static byte[] fileToByte(File img) throws Exception {
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "jpg", baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            // 关闭输出流
            baos.close();
        }
        return bytes;
    }
}

