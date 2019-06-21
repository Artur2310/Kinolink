package ru.kinolink.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.kinolink.service.model.Movie;
import ru.kinolink.service.repository.MovieDAO;
import ru.kinolink.service.service.ImageService;
import ru.kinolink.service.util.MovieImageUtil;

@Service("movieImageService")
public class MovieImageService implements ImageService {

    private static final Logger logger = LoggerFactory.getLogger(MovieImageService.class);

    private static ThreadLocal<MovieImageUtil> movieImageUtil = new ThreadLocal<MovieImageUtil>();

    @Value("${upload.dir}/image")
    private String uploadDir;

    @Autowired
    MovieDAO movieDAO;

    @Override
    @Transactional
    public String updateImage(MultipartFile file, Integer id) {

        if (file != null && !file.isEmpty()) {
            try {
                Movie movie = movieDAO.findById(id).get();
                movieImageUtil.set(new MovieImageUtil());
                String path = movieImageUtil.get().saveImage(id,file.getBytes(), uploadDir);
                movie.setPicturePath(path);
                movieDAO.save(movie);
                return path;

            } catch (Exception e) {
                logger.info("You failed to upload " + file.getName() + " => " + e.getMessage());
                return null;
            }
        } else {
            logger.info("You failed to upload " + file.getName() + " because the file was empty.");
            return null;
        }
    }
}
