package org.dreamkite.demo.repo.dao;

import org.dreamkite.demo.model.entity.ImageEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SysFileDao {

    void uploadImageFile(ImageEntity imageEntity);

    ImageEntity getImageFile(String id);
}
