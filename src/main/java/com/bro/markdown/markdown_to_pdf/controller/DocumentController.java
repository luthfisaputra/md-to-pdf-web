package com.bro.markdown.markdown_to_pdf.controller;

import com.bro.markdown.markdown_to_pdf.entity.Document;
import com.bro.markdown.markdown_to_pdf.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doc")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // Ambil daftar semua file
    @GetMapping("/all")
    public ResponseEntity<List<Document>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAllDocuments());
    }

    // Bikin file baru
    @PostMapping("/create")
    public ResponseEntity<Document> createDocument(@RequestBody String title) {
        return ResponseEntity.ok(documentService.createNewDocument(title));
    }

    // Save konten ke file tertentu (berdasarkan ID)
    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateDocument(@PathVariable Long id, @RequestBody(required = false) String content) {
        if (content == null) content = "";
        documentService.updateDocument(id, content);
        return ResponseEntity.ok("Tersimpan!");
    }

    @PutMapping("/rename/{id}") // Kita pakai PUT karena ini proses update data
    public ResponseEntity<String> renameDocument(@PathVariable Long id, @RequestBody String newTitle) {
        documentService.renameDocument(id, newTitle);
        return ResponseEntity.ok("Judul berhasil diganti!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok("File berhasil dihapus!");
    }
}
