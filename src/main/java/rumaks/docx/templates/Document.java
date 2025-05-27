/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rumaks.docx.templates;

/**
 *
 * @author kotyo
 */
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

//  Базовый класс для всех документов

public abstract class Document {
    protected XWPFDocument document; 
    protected Map<String, String> data;  // Данные для заполнения

    public Document(Map<String, String> data) {
        this.document = new XWPFDocument();
        this.data = data;
    }

    public abstract void generateContent();  // Заполнение документа

    public void saveToFile(String filePath) throws IOException {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            document.write(out);
        }
    }
}