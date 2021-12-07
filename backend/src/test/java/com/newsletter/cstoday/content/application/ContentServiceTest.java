package com.newsletter.cstoday.content.application;

import com.newsletter.cstoday.content.domain.Content;
import com.newsletter.cstoday.content.domain.repository.ContentRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ContentServiceTest {

    public static final String FILE_PATH = "/src/test/resources/";
    public static final String TEST_HTML = "test.html";

    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentRepository contentRepository;

    @DisplayName("비밀번호가 맞다면, MultipartFile 형식으로 전송된 HTML 파일을 텍스트 형식으로 저장할 수 있다.")
    @Test
    void uploadNewsLetter() throws IOException {
        // given
        File originalFile = new File(new File("").getAbsolutePath() + FILE_PATH + TEST_HTML);
        FileInputStream fileInputStream = new FileInputStream(originalFile);
        MultipartFile multipartFile = new MockMultipartFile("test.html", originalFile.getName(), "text/html", IOUtils.toByteArray(fileInputStream));

        final ByteArrayInputStream stream = new ByteArrayInputStream(FileUtils.readFileToByteArray(originalFile));
        final String newsLetterText = IOUtils.toString(stream, StandardCharsets.UTF_8);

        // when
        final Long id = contentService.uploadNewsLetter(multipartFile, "test");

        // then
        final Content content = contentRepository.findById(id).get();
        assertThat(content.getText()).isEqualTo(newsLetterText);
    }
}
