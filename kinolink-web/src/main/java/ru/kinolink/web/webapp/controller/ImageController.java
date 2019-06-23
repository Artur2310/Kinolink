package ru.kinolink.web.webapp.controller;

import org.hibernate.validator.constraints.URL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kinolink.web.webapp.Url;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class ImageController {

    @RequestMapping(Url.GET_IMAGE)
    public byte[] getImage(@RequestParam("path") String path) throws IOException {
        InputStream in = null;
        File f = new File(path);

        in = new FileInputStream(f);

        //return IOUtils.toByteArray(in);
        return null;
    }
}
