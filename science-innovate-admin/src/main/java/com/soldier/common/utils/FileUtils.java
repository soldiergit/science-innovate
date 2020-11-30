package com.soldier.common.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author soldier
 * @Date 20-7-25 上午8:56
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:文件操作工具类
 */
public class FileUtils {

    /**
     * 上传文件工具：不管文件存不存在，直接新增或替换
     *
     * @param UPLOAD_FILES_PATH 上传路径
     * @param originalFilename  文件名称(例：吃西瓜.jpg-> originalFilename=吃西瓜.jpg)
     * @param file              文件
     */
    public static Boolean upLoadUtil(String UPLOAD_FILES_PATH, String originalFilename, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return false;
        } else {
            // 即将创建文件
            File dest = new File(UPLOAD_FILES_PATH + "/" + originalFilename);
            // 判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
        }
        return true;
    }

    /**
     * 上传文件工具：文件重复时，在后来的文件末尾添加数字1 2...以此类推
     *
     * @param UPLOAD_FILES_PATH 上传路径
     * @param filePrefix        文件名称(例：吃西瓜.jpg-> filePrefix=吃西瓜)
     * @param fileSuffix        文件名后缀(例：吃西瓜.jpg-> fileSuffix=.jpg)
     * @param file              文件
     * @return  上传成功时返回新的文件名，否则返回null
     * @throws IOException
     */
    public static String upLoadUtil(String UPLOAD_FILES_PATH, String filePrefix, String fileSuffix, MultipartFile file) throws IOException {
        String fileName = filePrefix + fileSuffix;
        if (file.isEmpty()) {
            return null;
        } else {
            // 即将创建文件
            File dest = new File(UPLOAD_FILES_PATH, fileName);
            // 判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            // 判断文件是否存在，如果存在在文件名则在文件名后面加数字：1、2、3...
            for (int i = 1; dest.exists() && i < Integer.MAX_VALUE; i++) {
                fileName = filePrefix + '(' + i + ')' + fileSuffix;
                dest = new File(UPLOAD_FILES_PATH, fileName);
            }
            file.transferTo(dest);
        }
        return fileName;
    }

    /**
     * 下载文件工具
     *
     * @param response response对象
     * @param realPath 文件路径
     */
    public static void downloadUtil(final HttpServletResponse response, String realPath) {
        File file = new File(realPath);
        if (file.exists()) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
//			try {
//				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}

            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
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
    }

    /**
     * 删除文件夹
     *
     * @param sPath 文件夹路径
     */
    public static boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
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

    public static void main(String[] args) {
        String fileName = "吃西瓜.jpg";
        System.out.println(fileName.substring(0, fileName.lastIndexOf(".")));
        System.out.println(fileName.substring(fileName.lastIndexOf(".")));
    }
}
