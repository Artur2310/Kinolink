package ru.kinolink.test.integration.service;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import ru.kinolink.service.model.Movie;
import ru.kinolink.service.model.builder.MovieBuilder;
import ru.kinolink.service.service.MovieService;
import ru.kinolink.service.service.impl.MovieImageService;
import ru.kinolink.test.integration.TestAppConfig;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class MovieImageServiceTest {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieImageService movieImageService;

    @Value("${upload.dir}")
    private String uploadDir;

    @Test
    @Transactional
    public void addImage() throws IOException {

        File resource = new ClassPathResource("image/test_image.png").getFile();
        MultipartFile image = new MockMultipartFile("test_image.png", new FileInputStream(resource));

        Movie one = MovieBuilder.newBuilder()
                .setTitle("One")
                .setDescription("Test ...")
                .setReleaseDate(new Date())
                .build();

        Integer id = movieService.add(one).getId();

        String path = movieImageService.updateImage(image, id);
        assertNotNull(resource);
        assertTrue(movieService.get(id).getPicturePath().equals(path));
        assertTrue(new File(path).exists());

        File dir = new File(uploadDir);
        FileUtils.deleteDirectory(dir);
        }
}
