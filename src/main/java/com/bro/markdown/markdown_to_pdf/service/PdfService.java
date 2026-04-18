package com.bro.markdown.markdown_to_pdf.service;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.springframework.stereotype.Service;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@Service
public class PdfService {
    
    public byte[] generatePdfFromHtml(String htmlContent) {

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            
            /* 1. Bungkus HTML hasil Flexmark dengan struktur HTML standar + sedikit CSS biar rapi
               Bikin HTML ke Super Ketat (XHTML style)
               Tambahkan  meta charset dan namespace W3C agar library nya tidak error*/
            String fullHtml = "<!DOCTYPE html>\n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                    "<head>\n" +
                    "<meta charset=\"UTF-8\" />\n" + // HARUS ada slash penutup />
                    "<style>\n" +
                    "body { font-family: sans-serif; padding: 40px; line-height: 1.6; }\n" +
                    "h1, h2, h3 { color: #333; }\n" +

                    "code { background-color: #f6f8fa; padding: 2px 5px; border-radius: 4px; font-family: 'Courier', monospace; font-size: 14px; color: #24292f; }\n" +
                    "pre { background-color: #f6f8fa; padding: 15px; border-radius: 6px; border: 1px solid #dcdcdc; white-space: pre-wrap; word-wrap: break-word; }\n" +
                    "pre code { background-color: transparent; padding: 0; white-space: pre-wrap; }\n" +

                    ".token.comment, .token.prolog, .token.doctype, .token.cdata { color: #708090; }\n" +
                    ".token.punctuation { color: #999; }\n" +
                    ".token.property, .token.tag, .token.boolean, .token.number, .token.constant, .token.symbol, .token.deleted { color: #905; }\n" +
                    ".token.selector, .token.attr-name, .token.string, .token.char, .token.builtin, .token.inserted { color: #690; }\n" +
                    ".token.operator, .token.entity, .token.url, .language-css .token.string, .style .token.string { color: #9a6e3a; }\n" +
                    ".token.atrule, .token.attr-value, .token.keyword { color: #07a; }\n" +
                    ".token.function, .token.class-name { color: #DD4A68; }\n" +
                    ".token.regex, .token.important, .token.variable { color: #e90; }\n" +

                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    htmlContent +
                    "\n</body>\n" +
                    "</html>";
                            
            // 2. Proses build PDF-nya
            PdfRendererBuilder builder = new PdfRendererBuilder();

            builder.useFont(new File(getClass().getResource("/font/CourierPrime-Regular.ttf").toURI()), "Courier");

            builder.withHtmlContent(fullHtml, null);
            builder.toStream(os);
            builder.run();

            // 3. Kembalikan hasilnya kedalam bentuk byte (data mentah file)
            return os.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Pdf gagal di buat: " + e.getMessage());
        }

    }
}
