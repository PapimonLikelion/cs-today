package com.newsletter.cstoday.content.application;

import com.newsletter.cstoday.content.domain.Content;
import com.newsletter.cstoday.content.domain.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final MultipartFileConverter multipartFileConverter;
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
        final Content content = multipartFileConverter.toContent(newsLetterFile);
        contentRepository.save(content);
        return content.getId();
    }

    public String getContentText(Long contentId) {
        final Content content = contentRepository.findById(contentId)
                .orElseThrow(IllegalArgumentException::new);
        return content.getText();
    }
}
