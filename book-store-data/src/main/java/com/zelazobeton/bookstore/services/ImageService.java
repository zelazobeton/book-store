package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.services.interfaces.IImageService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Service
public class ImageService implements IImageService {
    public ImageService() {}

    @Override
    public InputStream getImgInputStream(Byte[] image) throws IOException {
        if(image != null){
            byte[] byteArr = new byte[image.length];
            int i = 0;
            for(Byte wrappedByte : image){
                byteArr[i++] = wrappedByte;
            }
            return new ByteArrayInputStream(byteArr);
        }
        else{
            File fi = new File("../../../../../resources/images/no-img.png");
            byte[] fileContent = Files.readAllBytes(fi.toPath());
            return new ByteArrayInputStream(fileContent);
        }
    }
}
