/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Workspace;

import Repository.Paper.PaperDAO;
import Repository.Paper.PaperDTO;
import Utils.Utils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;

public class PublishCoreServlet extends HttpServlet {

    private static P addImageToParagraph(Inline inline) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        p.getContent().add(r);
        Drawing drawing = factory.createDrawing();
        r.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return p;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "PublishPaper.jsp";

        try {
            String ID = request.getParameter("ID");

            PaperDTO paper = null;
            try {
                paper = PaperDAO.getPaperID(ID);
            } catch (SQLException | ClassNotFoundException ex) {
            }

            String filename = Utils.filePath("", request.getServletContext()) + paper.getPicture().substring(5);
            String fileNamePDF = ID +  ".pdf";
            String fileNameDocx = ID + ".docx";
            String fileSavePDFLocation = Utils.filePath("File\\Temp\\", request.getServletContext()) + fileNamePDF;
            String fileSaveDocxLocation = Utils.filePath("File\\Temp\\", request.getServletContext()) + fileNameDocx;
            request.setAttribute("paper", paper);
            request.setAttribute("fileSavePDFLocation", fileSavePDFLocation);
            request.setAttribute("fileSaveDocxLocation", fileSaveDocxLocation);
            request.setAttribute("fileNamePDF", fileNamePDF);
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(fileSavePDFLocation));
                document.open();
                Paragraph title = new Paragraph(paper.getTitle(), new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD));

                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                Image image = Image.getInstance(filename);
                image.setAlignment(Element.ALIGN_CENTER);
                image.scaleToFit(300, 300);
                document.add(image);

                Paragraph authorTitle = new Paragraph("Author", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
                authorTitle.setAlignment(Element.ALIGN_LEFT);
                document.add(authorTitle);

                Paragraph author = new Paragraph(paper.getUsername(), new Font(Font.FontFamily.HELVETICA));
                author.setAlignment(Element.ALIGN_LEFT);
                document.add(author);

                Paragraph topicTitle = new Paragraph("Topic", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));

                topicTitle.setAlignment(Element.ALIGN_LEFT);
                document.add(topicTitle);

                Paragraph topic = new Paragraph(paper.getTopic(), new Font(Font.FontFamily.HELVETICA));

                topic.setAlignment(Element.ALIGN_LEFT);
                document.add(topic);

                Paragraph descriptionTitle = new Paragraph("Description", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));

                descriptionTitle.setAlignment(Element.ALIGN_LEFT);
                document.add(descriptionTitle);

                Paragraph description = new Paragraph(paper.getDescription(), new Font(Font.FontFamily.HELVETICA));

                description.setAlignment(Element.ALIGN_LEFT);
                document.add(description);

                Paragraph contentTitle = new Paragraph("Content", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));

                contentTitle.setAlignment(Element.ALIGN_LEFT);
                document.add(contentTitle);
                Paragraph content = new Paragraph(paper.getContent(), new Font(Font.FontFamily.HELVETICA));

                content.setAlignment(Element.ALIGN_LEFT);
                document.add(content);

            } catch (DocumentException | FileNotFoundException e) {
            } finally {
                document.close();
            }

            WordprocessingMLPackage wordPackage;
            File word = new File(fileSaveDocxLocation);
            try {
                wordPackage = WordprocessingMLPackage.createPackage();
                MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
                mainDocumentPart.addParagraphOfText("Title: " + paper.getTitle());
                File image = new File(filename);
                byte[] fileContent = Files.readAllBytes(image.toPath());
                BinaryPartAbstractImage imagePart = BinaryPartAbstractImage
                        .createImagePart(wordPackage, fileContent);
                Inline inline = imagePart.createImageInline("", "Alt Text", 1, 2, false);
                P Imageparagraph = addImageToParagraph(inline);
                mainDocumentPart.getContent().add(Imageparagraph);
                mainDocumentPart.addParagraphOfText("Author: " + paper.getUsername());
                mainDocumentPart.addParagraphOfText("Topic: " + paper.getTopic());
                mainDocumentPart.addParagraphOfText("Description: " + paper.getDescription());
                mainDocumentPart.addParagraphOfText("Content: " + paper.getContent());
                wordPackage.save(word);
            } catch (Exception e) {
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(URL);
            rd.forward(request, response);
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
