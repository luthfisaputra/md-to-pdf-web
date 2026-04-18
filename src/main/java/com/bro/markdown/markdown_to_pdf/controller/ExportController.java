package com.bro.markdown.markdown_to_pdf.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bro.markdown.markdown_to_pdf.service.MarkdownService;
import com.bro.markdown.markdown_to_pdf.service.PdfService;

@RestController
@RequestMapping("/api")
public class ExportController {
    
    private final MarkdownService markdownService;
    private final PdfService pdfService;

    // Inject service ke controller
    public ExportController(MarkdownService markdownService, PdfService pdfService) {
        this.markdownService = markdownService;
        this.pdfService = pdfService;
    }

    // Endpoint untuk tes hasil HTML (sebelum nanti diubah ke PDF)
    @PostMapping("/test-html")
    public String testMarkdownToHtml(@RequestBody String markdown) {
        return markdownService.renderToHtml(markdown);
    }

    // Endpoint untuk download PDF
    @PostMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]>  exportToPdf(@RequestBody String markdown) {
        System.out.println("=== Mulai Proses PDF ===");
        System.out.println("1. Teks Markdown yang masuk: " + markdown);

        // 1. Ubah Markdown ke HTML
        String html = markdownService.renderToHtml(markdown);
        System.out.println("2. Hasil HTML: " + html);

        // 2. Ubah HTML ke PDF (berupa byte array)
        byte[] pdfBytes = pdfService.generatePdfFromHtml(html);
        System.out.println("3. Ukuran file PDF: " + pdfBytes.length + " bytes");

        // 3. Set header agar browser ngerti ini file PDF yang harus di-download
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Nama file default nya "markdownto.pdf"
        headers.setContentDispositionFormData("attachment", "markdownto.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    @PostMapping(value = "/export-pdf-html", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportToPdfFromHtml(@RequestBody String htmlContent) {

        // Langsung cetak HTML-nya jadi PDF
        byte[] pdfBytes = pdfService.generatePdfFromHtml(htmlContent);

        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "dokumen-rapi.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
    
}
