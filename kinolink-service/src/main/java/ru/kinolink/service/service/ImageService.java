package ru.kinolink.service.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String updateImage(MultipartFile file, Integer id);
}
