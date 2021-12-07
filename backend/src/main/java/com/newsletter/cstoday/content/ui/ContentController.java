package com.newsletter.cstoday.content.ui;

import com.newsletter.cstoday.content.application.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Controller
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload.html";
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadNewsLetter(@RequestParam("file") MultipartFile newsLetterFile,
                                                 @RequestParam String password) {
        final Long uploadedNewsLetterId = contentService.uploadNewsLetter(newsLetterFile, password);
        return ResponseEntity.created(URI.create("content/" + uploadedNewsLetterId)).build();
    }

    @GetMapping(value = "/content/{contentId:[\\d]+}", produces = "text/html")
    public ResponseEntity<String> getContent(@PathVariable Long contentId) {
        final String contentText = contentService.getContentText(contentId);
        return ResponseEntity.ok().body(contentText);
    }
}
