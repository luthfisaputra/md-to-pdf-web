package com.bro.markdown.markdown_to_pdf.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "documents")
public class Document {

    @Id // Ini Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Teks Markdown bisa panjang, gunakan columnDefinition TEXT
    @Column(columnDefinition = "TEXT")
    private String content;

    // Constructor Kosong (Wajib untuk JPA)
    public Document() {}

    // Constructor Biasa
    public Document(String content) {
        this.content = content;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
