package ru.kinolink.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kinolink.service.service.ImageService;
import ru.kinolink.service.model.Movie;
import ru.kinolink.service.repository.MovieDAO;
import ru.kinolink.service.util.PersonImageUtil;

@Service("movieImageService")
public class MovieImageService implements ImageService {

    private static final Logger logger = LoggerFactory.getLogger(MovieImageService.class);

    private static ThreadLocal<PersonImageUtil> personImageUtil = new ThreadLocal<PersonImageUtil>();

    @Autowired
    MovieDAO movieDAO;

    @Override
    public String updateImage(MultipartFile file, Integer id) {

        if (file != null && !file.isEmpty()) {
            try {
                Movie movie = movieDAO.findById(id).get();
                String path = personImageUtil.get().saveImage(id,file.getBytes());
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
