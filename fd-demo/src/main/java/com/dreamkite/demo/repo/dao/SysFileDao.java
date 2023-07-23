package com.dreamkite.demo.repo.dao;

import com.dreamkite.demo.model.entity.ImageEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SysFileDao {

    void uploadImageFile(ImageEntity imageEntity);

    ImageEntity getImageFile(String id);
}
