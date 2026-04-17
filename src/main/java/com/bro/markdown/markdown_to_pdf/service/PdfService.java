package com.bro.markdown.markdown_to_pdf.service;

import java.io.ByteArrayOutputStream;

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
                    "<meta charset=\"UTF-8\" />\n" + // HARUS asa slash penutup />
                    "<style>\n" +
                    "body { font-family: sans-serif; padding: 40px; line-height: 1.6; }\n" +
                    "h1, h2, h3 { color: #333; }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    htmlContent +
                    "\n</body>\n" +
                    "</html>";
                            
            // 2. Proses build PDF-nya
            PdfRendererBuilder builder = new PdfRendererBuilder();
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
