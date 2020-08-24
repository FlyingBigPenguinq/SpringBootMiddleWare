package com.study.boot.SpringBootMiddleWare.server.service;

import com.study.boot.SpringBootMiddleWare.model.entity.Appendix;
import com.study.boot.SpringBootMiddleWare.server.Enums.SysMudule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName CommService
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/23
 * @Version V1.0
 **/
@Service
public class CommService {

    private static final Logger log = LoggerFactory.getLogger(CommService.class);

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private Environment env;
    @Autowired
    private AppendixMapper appendixMapper;

    public void upLoadv1(MultipartFile multipartFile, final Integer moduleId) throws Exception{
        //TODO 保存上传的文件到我们的硬盘上
        String fileName = multipartFile.getOriginalFilename();
        Long size = multipartFile.getSize();
        String suffix = StringUtils.substring(fileName,StringUtils.indexOf(fileName,"."));
        String rootPath = env.getProperty("/media/lxl/Linux/test_workplace") + DATE_FORMAT.format(new Date()) + File.separator + moduleId;
        String newFileName = System.nanoTime() +  suffix;
        String newFile = rootPath + newFileName;
        File file = new File(newFile);
        if (!file.getParentFile().exists()){ //TODO 如果文件的父目录不存在　则创建一个父目录
            file.getParentFile().mkdirs();
        }

        multipartFile.transferTo(file);
        //TODO 吧文件的相关信息存储到数据库里
        Appendix entity = new Appendix(moduleId, "001", "商品", newFileName, size.toString(), suffix, newFile,new Date());
        appendixMapper.insertSelective(entity);
    }

    /**
     * @MethodName: upLoadv2
     * @Description: TODO NIO的方式上传文件
     * @Param: [multipartFile, moduleId]
     * @Return: void
     * @Author: lxl
     * @Date: 下午2:46
    **/
    public void upLoadv2(MultipartFile multipartFile, final Integer moduleId) throws Exception{
        //TODO 保存上传的文件到我们的硬盘上
        String fileName = multipartFile.getOriginalFilename();
        Long size = multipartFile.getSize();
        String suffix = StringUtils.substring(fileName,StringUtils.indexOf(fileName,"."));
        String rootPath = env.getProperty("/media/lxl/Linux/test_workplace") + DATE_FORMAT.format(new Date()) + File.separator + moduleId;
        String newFileName = System.nanoTime() +  suffix;
        String newFile = rootPath + newFileName;
        File file = new File(newFile);
        if (!file.getParentFile().exists()){ //TODO 如果文件的父目录不存在　则创建一个父目录
            file.getParentFile().mkdirs();
        }

        //方式一　用Files　来write进入文件（相同文件名不服该）
        /*Path path = Paths.get(newFile);
        Files.write(path,multipartFile.getBytes());*/

        //方式二　用Files 来copy进入文件(如果文件名相同则覆盖)　　*但是前面用的纳秒命名所以一般不会覆盖
        Path path = Paths.get(newFile);
        Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        //TODO 吧文件的相关信息存储到数据库里
        Appendix entity = new Appendix(moduleId, "001", "商品", newFileName, size.toString(), suffix, newFile,new Date());
        appendixMapper.insertSelective(entity);
    }

    //通用的上传文件的方式
    public void upLoadv3(MultipartFile multipartFile, final Integer moduleId, final SysMudule sysMudule) throws Exception{
        //TODO 保存上传的文件到我们的硬盘上
        String fileName = multipartFile.getOriginalFilename();
        Long size = multipartFile.getSize();
        String suffix = StringUtils.substring(fileName,StringUtils.indexOf(fileName,"."));
        String rootPath = env.getProperty("/media/lxl/Linux/test_workplace") + DATE_FORMAT.format(new Date()) + File.separator + moduleId;
        String newFileName = System.nanoTime() +  suffix;
        String newFile = rootPath + newFileName;
        File file = new File(newFile);
        if (!file.getParentFile().exists()){ //TODO 如果文件的父目录不存在　则创建一个父目录
            file.getParentFile().mkdirs();
        }

        multipartFile.transferTo(file);
        //TODO 吧文件的相关信息存储到数据库里
        Appendix entity = new Appendix(moduleId, sysMudule.getCode(), sysMudule.getName(), newFileName, size.toString(), suffix, newFile,new Date());
        appendixMapper.insertSelective(entity);
    }

    //TODO 通用的下载文件的方式
    public void downFile(HttpServletResponse response, InputStream is, String fileName) throws Exception{
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        try {
            bis=new BufferedInputStream(is);
            bos=new BufferedOutputStream(response.getOutputStream());

            //TODO:往响应流设置响应类型和响应头
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso-8859-1"));

            //TODO:其实就是一个不断的读、写的过程
            byte[] buffer=new byte[10240];
            int len=bis.read(buffer);
            while (len!=-1){
                bos.write(buffer,0,len);
                len=bis.read(buffer);
            }

            bos.flush();
        }finally {
            if (bos != null) {
                bos.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    //TODO 通过NIO的方式实现文件的下载
    public void downFilev2(HttpServletResponse response, FileInputStream is, String fileName) throws Exception{
        //TODO:往响应流设置响应类型和响应头
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso-8859-1"));

        //10KB
        int bufferSize=10240;
        byte[] byteArr=new byte[bufferSize];

        //6*10KB=‭61440‬
        FileChannel fileChannel=is.getChannel();
        ByteBuffer buffer= ByteBuffer.allocateDirect(61440);

        int nRead;
        int nGet;
        try {
            while ( (nRead=fileChannel.read(buffer)) !=-1){
                if (nRead==0){
                    continue;
                }

                buffer.position(0);
                buffer.limit(nRead);
                while (buffer.hasRemaining()){
                    nGet=Math.min(buffer.remaining(),bufferSize);

                    //TODO:从磁盘中读I - Input
                    buffer.get(byteArr,0,nGet);
                    //TODO:往浏览器响应流写O - Output
                    response.getOutputStream().write(byteArr);
                }

                buffer.clear();
            }
        }finally {
            buffer.clear();
            fileChannel.close();
            is.close();
        }
    }
}
