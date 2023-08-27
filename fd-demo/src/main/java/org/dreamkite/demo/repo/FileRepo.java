package org.dreamkite.demo.repo;

import org.dreamkite.demo.model.entity.ImageEntity;

public interface FileRepo {

    String uploadImageFile(ImageEntity imageEntity);

    ImageEntity getImageFile(String id);
}
