package com.bro.markdown.markdown_to_pdf.service;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@Service
public class PdfService {
    
    public byte[] generatePdfFromHtml(String htmlContent) {

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            
            // 1. Bungkus HTML hasil Flexmark dengan struktur HTML standar + sedikit CSS biar rapi
            String fullHtml = "<!DOCTYPE html><head><style>" + "body { font-family: Arial, sans-serif; padding: 40px; Line-height: 1.6; }" + 
                              "h1, h2, h3 { color: #333; }" + "</style></head></body>" +
                              htmlContent + 
                              "</body></html>";
                            
            // 2. Proses build PDF-nya
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(fullHtml, ""); // "" ini base URI, dikosongkan untuk sementara
            builder.toStream(os);

            // 3. Kembalikan hasilnya kedalam bentuk byte (data mentah file)
            return os.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Pdf gagal di buat: " + e.getMessage());
        }

    }
}
