package com.bro.markdown.markdown_to_pdf.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "documents")
public class Document {

    @Id // Ini Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // Teks Markdown bisa panjang, gunakan columnDefinition TEXT
    @Column(columnDefinition = "TEXT")
    private String content;

    // Constructor Kosong (Wajib untuk JPA)
    public Document() {}

    // Constructor Biasa
    public Document(String content, String title) {
        this.content = content;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
