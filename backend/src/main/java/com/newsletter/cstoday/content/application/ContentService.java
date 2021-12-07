package com.newsletter.cstoday.content.application;

import com.newsletter.cstoday.content.domain.Content;
import com.newsletter.cstoday.content.domain.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    @Value("${setting.upload.password}")
    private String password;

    public Long uploadNewsLetter(MultipartFile newsLetterFile, String password) {
        if (this.password.equals(password)) {
            return uploadNewsLetterToServer(newsLetterFile);
        }
        throw new IllegalArgumentException();
    }

    private Long uploadNewsLetterToServer(MultipartFile newsLetterFile) {
        try {
            final ByteArrayInputStream stream = new ByteArrayInputStream(newsLetterFile.getBytes());
            final String newsLetterText = IOUtils.toString(stream, StandardCharsets.UTF_8);
            final Content content = new Content(newsLetterText);
            contentRepository.save(content);
            return content.getId();
        } catch (IOException exception) {
            throw new IllegalArgumentException();
        }
    }

    public String getContentText(Long contentId) {
        final Content content = contentRepository.findById(contentId)
                .orElseThrow(IllegalArgumentException::new);
        return content.getText();
    }
}
