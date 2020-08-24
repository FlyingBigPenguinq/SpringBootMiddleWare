package com.study.boot.SpringBootMiddleWare.server.service;

import com.study.boot.SpringBootMiddleWare.model.entity.Appendix;
import com.study.boot.SpringBootMiddleWare.model.entity.Item;
import com.study.boot.SpringBootMiddleWare.server.Enums.SysMudule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @ClassName ItemService
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/22
 * @Version V1.0
 **/
@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private AppendixMapper appendixMapper;
    @Autowired
    private CommService commService;
    @Autowired
    private Environment env;

    @Transactional(rollbackFor = Exception.class)
    public void addAndUpload(MultipartHttpServletRequest request) throws Exception {
        MultipartFile file = request.getFile("appendix");
        Item item = new Item();
        item.setName(request.getParameter("itemName"));
        item.setCode(request.getParameter("itemCode"));
        if (StringUtils.isBlank(item.getName()) || StringUtils.isBlank(item.getCode())){
            throw new RuntimeException("上传新的商品出错");
        }
        itemMapper.insertSelective(item);
        commService.upLoadv3(file,item.getId(), SysMudule.ModuleItem);
    }

    public Appendix getAppendix(final Integer id) throws Exception{
        return appendixMapper.selectByPrimaryKey(id);
    }
}
