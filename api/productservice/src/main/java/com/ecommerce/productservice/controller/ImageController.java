package com.ecommerce.productservice.controller;

import ch.qos.logback.core.model.Model;
import com.ecommerce.productservice.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/product/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/photos/add")
    public String addPhoto(@RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        Long id = imageService.addPhoto(title, image);
        return "redirect:/photos/" + id;
    }
}
