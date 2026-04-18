package com.bro.markdown.markdown_to_pdf.service;

import com.bro.markdown.markdown_to_pdf.entity.Document;
import com.bro.markdown.markdown_to_pdf.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    // 1. Ambil semua file buat ditampilkan di Sidebar
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    // 2. Bikin file baru (Untitle...)
    public Document createNewDocument(String title) {
        Document newDoc = new Document(title, "# " + title + "\n\nMulai ngetik di sini bro...");
        return documentRepository.save(newDoc);
    }

    // 3. Auto-Save berdasarkan ID file yang lagi dibuka
    public void updateDocument(Long id, String content) {
        documentRepository.findById(id).ifPresent(doc -> {
            doc.setContent(content);
            documentRepository.save(doc);
        });
    }

    // Fungsi khusus buat ganti judul file
    public void renameDocument(Long id, String newTitle) {
        documentRepository.findById(id).ifPresent(doc -> {
            doc.setTitle(newTitle);
            documentRepository.save(doc);
        });
    }

    // Fungsi hapus file
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}
