package com.bro.markdown.markdown_to_pdf.repository;

import com.bro.markdown.markdown_to_pdf.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
