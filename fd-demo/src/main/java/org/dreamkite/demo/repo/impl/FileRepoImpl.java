package org.dreamkite.demo.repo.impl;

import org.dreamkite.demo.model.entity.ImageEntity;
import org.dreamkite.demo.repo.FileRepo;
import org.dreamkite.demo.repo.dao.SysFileDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class FileRepoImpl implements FileRepo {
    @Resource
    private SysFileDao sysFileDao;

    @Override
    public String uploadImageFile(ImageEntity imageEntity) {
        String id = UUID.randomUUID().toString();
        imageEntity.setId(id);
        sysFileDao.uploadImageFile(imageEntity);
        return id;
    }

    @Override
    public ImageEntity getImageFile(String id) {
        return sysFileDao.getImageFile(id);
    }
}
