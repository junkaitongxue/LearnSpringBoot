package com.dreamkite.demo.controller;

import com.dreamkite.core.exception.ServiceException;
import com.dreamkite.demo.model.entity.ImageEntity;
import com.dreamkite.demo.repo.FileRepo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;


@Api(value = "文件控制器")
@RestController
@Slf4j
@Validated
@RequestMapping("/file")
public class FileController {
    @Resource
    FileRepo fileRepo;

    @PostMapping(value = "uploadImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadImage(@RequestParam(value = "imageFile") MultipartFile file) {
        try {
            ImageEntity imageEntity = ImageEntity.builder().name(file.getOriginalFilename()).content(file.getBytes()).build();
            return fileRepo.uploadImageFile(imageEntity);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }


    @GetMapping(value = "/viewImage/{id}")
    public void viewImage(@PathVariable @NotBlank(message = "图片id不能为空") String id, HttpServletResponse response) {
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ImageEntity imageFile = fileRepo.getImageFile(id);
            if (imageFile == null) {
                throw new ServiceException("Id of image is not exist");
            }
            outputStream.write(imageFile.getContent());
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }


    @GetMapping(value = "/downloadImg/{id}")
    public void downloadImg(@PathVariable @NotBlank(message = "图片id不能为空") String id, HttpServletResponse response) {
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ImageEntity imageFile = fileRepo.getImageFile(id);
            if (imageFile == null) {
                throw new ServiceException("Id of image is not exist");
            }
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + imageFile.getName());
            response.setContentLength(imageFile.getContent().length);
            outputStream.write(imageFile.getContent());
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }
}
