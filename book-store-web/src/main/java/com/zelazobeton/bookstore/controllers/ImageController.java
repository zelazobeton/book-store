package com.zelazobeton.bookstore.controllers;

import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.model.User;
import com.zelazobeton.bookstore.services.interfaces.IImageService;
import com.zelazobeton.bookstore.services.interfaces.IItemService;
import com.zelazobeton.bookstore.services.interfaces.IReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ImageController {
    private final IReviewService reviewService;
    private final IItemService itemService;
    private final IImageService imageService;

    public ImageController(IReviewService reviewService,
                           IItemService itemService,
                           IImageService imageService) {
        this.reviewService = reviewService;
        this.itemService = itemService;
        this.imageService = imageService;
    }

    @GetMapping("**/item={id}/img={imgId}")
    public void renderImageFromDb(@PathVariable("id") Long id,
                                  HttpServletResponse response,
                                  @AuthenticationPrincipal User user) throws IOException {
        Item item = itemService.findById(id);
        response.setContentType("image/jpeg");
        IOUtils.copy(imageService.getImgInputStream(item.getImage()),
                     response.getOutputStream());
    }
}
