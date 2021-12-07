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

@Controller
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload.html";
    }

    @PostMapping("/upload")
    public String uploadNewsLetter(@RequestParam("file") MultipartFile newsLetterFile,
                                   @RequestParam String password) {
        final Long contentId = contentService.uploadNewsLetter(newsLetterFile, password);
        return "redirect:/contents/" + contentId;
    }

    @GetMapping(value = "/contents/{contentId:[\\d]+}", produces = "text/html")
    public ResponseEntity<String> getContent(@PathVariable Long contentId) {
        final String contentText = contentService.getContentText(contentId);
        return ResponseEntity.ok().body(contentText);
    }
}
