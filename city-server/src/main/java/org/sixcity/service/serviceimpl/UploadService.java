package org.sixcity.service.serviceimpl;

import org.sixcity.constant.PathConst;
import util.RandomUtils;

import exception.ApplicationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {

    private static Path getRootPath() {
        return Paths.get(PathConst.USER_UPLOAD_ROOT);
    }

    public String saveTempImage(MultipartFile image) {
        Path tmpPath = getRootPath().resolve(PathConst.USER_UPLOAD_IMAGE_TEMP_FOLDER);
        File tmpFile = new File(tmpPath.toUri());
        if (!tmpFile.isDirectory() && !tmpFile.mkdirs()) {
            throw new ApplicationException("目标文件夹不存在，创建文件夹失败");
        }
        File upFile = new File(tmpFile, RandomUtils.uuid() + image.getOriginalFilename());
        try {
            image.transferTo(upFile);
        } catch (IllegalStateException | IOException ex) {
            throw new ApplicationException(ex);
        }
        return upFile.getName();
    }


    public void saveImage(String imageName) throws IOException {
        Path tmpPath = getRootPath().resolve(PathConst.USER_UPLOAD_IMAGE_TEMP_FOLDER).resolve(imageName);
        Path imagePath = getRootPath().resolve(PathConst.USER_UPLOAD_IMAGE_FOLDER);
        File imageFile = imagePath.toFile();
        if (!imageFile.isDirectory() && !imageFile.mkdirs()) {
            throw new ApplicationException("目标文件夹不存在，创建文件夹失败");
        }
        File tmpFile = tmpPath.toFile();
        if(!tmpFile.renameTo(getRootPath().resolve(PathConst.USER_UPLOAD_IMAGE_FOLDER).resolve(tmpFile.getName()).toFile())){
            throw new ApplicationException("图片失效,请重新上传");
        }
    }
}
