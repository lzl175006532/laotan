package com.laotan.net.common.util;

import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.handleException.CustomException;
import com.laotan.net.service.SystemParamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Copyright: 通泰信诚
 * @Author: tw
 * @Since: 2021-05-20 15:04
 * @Description: 文件上传下载工具类
 */
@Component
public class FileUtils {

    @Autowired
    private SystemParamService systemParamService;

    /**
     * 文件上传
     * filePath文件上传路径
     * @param file
     * @return
     */
    public String uploadFile(MultipartFile file,StringBuffer filePath) {
        int random = (int) ((Math.random() * 9 + 1) * 100000);
        String folder = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis())) + random;
        if(filePath == null || StringUtils.isEmpty(filePath.toString())){
            filePath.append(systemParamService.selectByParamKey("FILE_PATH",""));
        }
        if (StringUtils.isEmpty(filePath)) {
            throw new CustomException(ResultStatusCode.SYS_DATA_ERROR);
        }
        filePath.append(folder + "/");
        String fileName = file.getOriginalFilename();
        File dest = new File(filePath + fileName);
        if (!dest.exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            throw new CustomException(ResultStatusCode.EXCEPTION);
        }
        //拼接完整路径
        filePath.append(fileName);
        return fileName;
    }

    /**
     * 文件下载
     *
     * @param response
     * @param fileName
     * @param filePath
     */
    public void downloadFile(HttpServletResponse response, String fileName, String filePath) {
        File file = new File(filePath + fileName);

        if (!file.exists()) {
            throw new CustomException(ResultStatusCode.EXCEPTION);
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

        byte[] buffer = new byte[1024];
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            throw new CustomException(ResultStatusCode.EXCEPTION);
        }
    }

    /**
     * 多文件上传
     *
     * @param files
     * @return java.lang.String
     * @author zhaojiang
     * @date 2021/6/29 15:10
     */
    public String uploadFileBatch(MultipartFile[] files,StringBuffer filePath) {

        String fileNames = "";
        //保存
        for (MultipartFile file : files) {
            String fileName = this.uploadFile(file,filePath);
            fileNames += fileName + ",";
        }
        return fileNames.substring(0, fileNames.length() - 1);
    }
}
