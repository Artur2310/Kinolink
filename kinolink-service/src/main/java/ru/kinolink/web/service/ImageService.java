package ru.kinolink.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String updateImage(MultipartFile file, Integer id);
}
