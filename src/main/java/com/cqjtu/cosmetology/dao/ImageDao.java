package com.cqjtu.cosmetology.dao;

import com.cqjtu.cosmetology.entity.Image;

import java.util.List;

public interface ImageDao {
    List<Image> queryImageByType(String type);
}
