package org.sixcity.web;

import model.Result;

import model.ResultCode;
import org.sixcity.service.impl.UploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@RestController
@RequestMapping(value = "upload")
@PreAuthorize("hasRole('ROLE_USER')")
public class UploadController {

    private final UploadService uploadService;

    @Autowired
    public UploadController( UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "uploadImage", method = RequestMethod.POST)
    public Result uploadImage(@RequestParam(value = "image") MultipartFile image) {
        String path = "";
        if (image != null && image.getSize() > 0) {
            //FileModel ufi = uploadService.buildFileIndex(image, PathConst.USER_UPLOAD_IMAGE_FOLDER);
            path = uploadService.saveTempImage(image);
        } else {
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR,"请上传图片");
        }
        return Result.createSuccessResult(Collections.singletonMap("path", path), "上传成功");
    }
}
