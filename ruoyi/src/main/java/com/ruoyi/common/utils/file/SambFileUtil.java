package com.ruoyi.common.utils.file;

import com.ruoyi.common.exception.file.FileException;
import com.ruoyi.common.utils.StringUtils;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
     * 拷贝远程文件到本地目录
     * @param smbFile 远程SmbFile
     * @param localDirectory 本地存储目录,本地目录不存在时会自动创建,本地目录存在时可自行选择是否清空该目录下的文件,默认为不清空
     * @return boolean 是否拷贝成功
     */
    public static boolean copyRemoteFile(SmbFile smbFile, String localDirectory) {
        SmbFileInputStream in = null;
        FileOutputStream out = null;
        try {
            File[] localFiles = new File(localDirectory).listFiles();

            if (null == localFiles) {
                // 目录不存在的话,就创建目录
                new File(localDirectory).mkdirs();
            }
            in = new SmbFileInputStream(smbFile);
            out = new FileOutputStream(localDirectory + smbFile.getName());
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            return false;
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
