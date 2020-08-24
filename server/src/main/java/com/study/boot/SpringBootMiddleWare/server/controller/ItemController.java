package com.study.boot.SpringBootMiddleWare.server.controller;

import com.study.boot.SpringBootMiddleWare.api.response.BaseResponse;
import com.study.boot.SpringBootMiddleWare.api.response.StatusCode;
import com.study.boot.SpringBootMiddleWare.model.entity.Appendix;
import com.study.boot.SpringBootMiddleWare.server.service.CommService;
import com.study.boot.SpringBootMiddleWare.server.service.ItemService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ClassName ItemController
 * @Description: 添加商品　上传/下载　文件
 * @Author lxl
 * @Date 2020/8/22
 * @Version V1.0
 **/
@RestController
@RequestMapping("item")
public class ItemController extends AbstractController{

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;
    @Autowired
    private Environment env;
    @Autowired
    private CommService commService;
    //添加商品-上传文件
    @RequestMapping(value = "add/upload/v1",method = RequestMethod.POST)
    public BaseResponse addItemAndUpload(MultipartHttpServletRequest request){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("--添加商品-上传文件-添加商品-上传文件");
            itemService.addAndUpload(request);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "file/download/{id}",method = RequestMethod.GET)
    public @ResponseBody String downLoadFile(@PathVariable Integer id, HttpServletResponse response){
        if (id == null || id < 0){
            return null;
        }
        try {
            Appendix appendix = itemService.getAppendix(id);
            //appendix 表里面村的是相对路径　后面要加上父目录的路径
            String fileUrl = env.getProperty("file.upload.location.root.url") + appendix.getFileUrl();
            FileInputStream is = new FileInputStream(fileUrl);
            commService.downFile(response, is, appendix.getName());
        }catch (Exception e){

        }
        return null;
    }
}
