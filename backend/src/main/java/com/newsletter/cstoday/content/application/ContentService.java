package com.newsletter.cstoday.content.application;

import com.newsletter.cstoday.content.domain.Content;
import com.newsletter.cstoday.content.domain.repository.ContentRepository;
import com.newsletter.cstoday.exception.CsTodayException;
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
        throw new CsTodayException("비밀번호가 잘못되었습니다.");
    }

    private Long uploadNewsLetterToServer(MultipartFile newsLetterFile) {
        final Content content = multipartFileConverter.toContent(newsLetterFile);
        contentRepository.save(content);
        return content.getId();
    }

    public String getContentText(Long contentId) {
        final Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new CsTodayException("찾을 수 없는 contentId 입니다."));
        return content.getText();
    }
}
