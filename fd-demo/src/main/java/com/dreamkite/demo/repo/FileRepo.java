package com.dreamkite.demo.repo;

import com.dreamkite.demo.model.entity.ImageEntity;

public interface FileRepo {

    String uploadImageFile(ImageEntity imageEntity);

    ImageEntity getImageFile(String id);
}
