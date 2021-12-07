package com.newsletter.cstoday.content.application;

import com.newsletter.cstoday.content.domain.Content;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class MultipartFileConverter {

    public Content toContent(MultipartFile newsLetterFile) {
        try {
            final ByteArrayInputStream stream = new ByteArrayInputStream(newsLetterFile.getBytes());
            final String newsLetterText = IOUtils.toString(stream, StandardCharsets.UTF_8);
            return new Content(newsLetterText);
        } catch (IOException exception) {
            throw new IllegalArgumentException();
        }
    }
}
