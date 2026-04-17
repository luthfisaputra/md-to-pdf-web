package com.bro.markdown.markdown_to_pdf.service;

import org.springframework.stereotype.Service;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

@Service
public class MarkdownService {
    
    public String renderToHtml(String markdownText) {
        // 1. Setup konfigurasi default Flexmark
        MutableDataSet options = new MutableDataSet();

        // 2. Siapkan Parser (buat baca Markdown) dan Render (buat nulis HTML)
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // 3. Proses
        Node document = parser.parse(markdownText);
        return renderer.render(document);
    }
}
