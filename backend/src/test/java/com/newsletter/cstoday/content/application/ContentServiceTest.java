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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("비밀번호가 틀렸다면, 뉴스레터를 업로드할 수 없다.")
    @Test
    void uploadFailWhenPasswordWrong() throws IOException {
        // given
        File originalFile = new File(new File("").getAbsolutePath() + FILE_PATH + TEST_HTML);
        FileInputStream fileInputStream = new FileInputStream(originalFile);
        MultipartFile multipartFile = new MockMultipartFile("test.html", originalFile.getName(), "text/html", IOUtils.toByteArray(fileInputStream));

        // then
        assertThatThrownBy(() -> contentService.uploadNewsLetter(multipartFile, "wrongPW"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("contentId에 해당하는 뉴스레터의 text를 가져올 수 있다.")
    @Test
    void getContextTest() {
        // given
        final Content savedContent = contentRepository.save(new Content("hello world"));

        // when
        final String contentText = contentService.getContentText(savedContent.getId());

        // then
        assertThat(contentText).isEqualTo("hello world");
    }


    @DisplayName("contentId에 해당하는 뉴스레터가 없다면 예외가 발생한다.")
    @Test
    void cannotGetContextTest() {
        assertThatThrownBy(() -> contentService.getContentText(99999999L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
