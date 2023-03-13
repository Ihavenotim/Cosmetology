package com.cqjtu.cosmetology.service.impl;

import com.cqjtu.cosmetology.dao.ImageDao;
import com.cqjtu.cosmetology.entity.Image;
import com.cqjtu.cosmetology.service.ImageService;
import com.cqjtu.cosmetology.util.ResponseCode;
import com.cqjtu.cosmetology.util.ResponseData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageDao imageDao;

    @Override
    public ResponseData queryImageByType(String type){
        try{
            List<Image> images=imageDao.queryImageByType(type);
            return new ResponseData(ResponseCode.SUCCESS01,images);
        }catch (Exception e){
            return new ResponseData(ResponseCode.FAIL01);
        }
    }
}
