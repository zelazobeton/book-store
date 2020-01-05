package com.zelazobeton.bookstore.services.interfaces;

import com.zelazobeton.bookstore.commands.ReviewCommand;
import com.zelazobeton.bookstore.model.Review;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface IImageService {
    InputStream getImgInputStream(Byte[] image) throws IOException;
    void saveImageFile(Long itemId, MultipartFile file);
}
