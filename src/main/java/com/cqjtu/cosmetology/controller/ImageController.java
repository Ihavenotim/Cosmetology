package com.cqjtu.cosmetology.controller;

import com.cqjtu.cosmetology.service.ImageService;
import com.cqjtu.cosmetology.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (Image)表控制层
 *
 * @author makejava
 * @since 2023-03-06 15:38:34
 */
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/queryImageByType")
    public ResponseData queryImageByType(String type){
        return imageService.queryImageByType(type);
    }

}

