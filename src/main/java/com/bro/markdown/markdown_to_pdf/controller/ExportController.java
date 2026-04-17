package com.bro.markdown.markdown_to_pdf.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bro.markdown.markdown_to_pdf.service.MarkdownService;

@RestController
@RequestMapping("/api")
public class ExportController {
    
    private final MarkdownService markdownService;

    // Inject service ke controller
    public ExportController(MarkdownService markdownService) {
        this.markdownService = markdownService;
    }

    // Endpoint untuk tes hasil HTML (sebelum nanti diubah ke PDF)
    @PostMapping("/test-html")
    public String testMarkdownToHtml(@RequestBody String markdown) {
        return markdownService.renderToHtml(markdown);
    }
}
