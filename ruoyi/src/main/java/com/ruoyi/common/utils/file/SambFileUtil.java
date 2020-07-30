package com.ruoyi.common.utils.file;

import com.ruoyi.common.exception.file.FileException;
import com.ruoyi.common.utils.StringUtils;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

@Component
public class SambFileUtil {
    @Value("${samba.name}")
    private String name;
    @Value("${samba.password}")
    private  String password;
    @Value("${samba.ip}")
    private  String ip;
    @Value("${samba.url}")
    private  String url;
    @Value("${ruoyi.profile}")
    private  String profile;

    public  void testSmb() throws Exception {
        String path = profile + "/download/";
        String remotePath = url+"/UploadFiles/Upload/2020/5fae2ab2-450e-4e29-bdea-342c6362888f/67d63743-0490-4962-bf60-24f4802a9781/bbee22ad-f55d-4c79-812c-84cebba9b358.jpg";
    }


    /**
     * 获取远程文件
     * @param remoteFilepath 远程文件地址,该参数需以IP打头,如'192.168.8.2/aa/bb.java'或者'192.168.8.2/aa/',如'192.168.8.2/aa'是不对的
     * @return boolean 是否获取成功
     * @throws Exception
     */
    public SmbFile getRemoteFile(String remoteFilepath) throws Exception {
        if (remoteFilepath.startsWith("/") || remoteFilepath.startsWith("\\")) {
            throw new FileException("文件服务器路径错误！");
        }
        SmbFile smbFile = null;
        if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(password)) {
            smbFile = new SmbFile("smb://" + name + ":" + password + "@" + remoteFilepath);
        } else {
            smbFile = new SmbFile("smb://" + remoteFilepath);
        }

        if (smbFile != null) {
            if (smbFile.isDirectory()) {
                throw new FileException("文件不存在！");
            } else if (smbFile.isFile()) {
                return smbFile;
            }
        }else{
            throw new FileException("文件不存在！");
        }
        return null;
    }

    /**
     * 上传文件
     * @param remoteFilepath 共享文件夹路径
     * @param file 文件
     */
    public String uploadRemoteFile(String remoteFilepath, MultipartFile file) throws Exception  {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //MultipartFile转file
        inputStream = file.getInputStream();
        SmbFile smbFile = new SmbFile("smb://" + name + ":" + password + "@" + ip + remoteFilepath);
        smbFile.connect();
        outputStream = new SmbFileOutputStream(smbFile);
        byte[] buffer = new byte[4096];
        int len = 0; // 读取长度
        while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        // 刷新缓冲的输出流
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        return ip+remoteFilepath;
    }





}
