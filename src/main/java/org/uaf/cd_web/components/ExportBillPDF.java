package org.uaf.cd_web.components;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ExportBillPDF {
    private final TemplateEngine templateEngine;

    public ExportBillPDF(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdf(String templateName, Context context) throws DocumentException, IOException {
//        String htmlContent = templateEngine.process(templateName, context);
//
//        Document document = new Document(PageSize.A4);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
//
//        document.open();
//        HTMLWorker htmlWorker = new HTMLWorker(document);
//        htmlWorker.parse(new StringReader(htmlContent));
//        document.close();
        // Generate HTML content from Thymeleaf template
        String htmlContent = templateEngine.process(templateName, context);

        // Create a ByteArrayOutputStream to hold the PDF data
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Initialize the PDF writer and document
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);

        // Set up the converter properties
        ConverterProperties converterProperties = new ConverterProperties();
        FontProvider fontProvider = new FontProvider();
        fontProvider.addStandardPdfFonts();
        fontProvider.addFont("src/main/resources/static/fonts/ARIAL.TTF");
        converterProperties.setFontProvider(fontProvider);

        // Convert HTML to PDF
        HtmlConverter.convertToPdf(htmlContent, pdfDocument, converterProperties);

        // Close the document
        pdfDocument.close();

        return outputStream.toByteArray();

    }
}
