/*
 * Базовый класс для всех документов
 */
package rumaks.docx.templates;

import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public abstract class Document {
    protected XWPFDocument document; 
    protected Map<String, String> data;  // Данные для заполнения
    protected static final String FONT = "Times New Roman";
    protected static final int TITLE_SIZE = 14;
    protected static final int TEXT_SIZE = 12;

    public Document(Map<String, String> data) {
        this.document = new XWPFDocument();
        this.data = data;
    }

    public abstract void generateContent();  // Заполнение документа

    public void saveToFile(String filePath) throws IOException {
        try (var out = new FileOutputStream(filePath)) {
            document.write(out);
        }
    }

    protected void addEmptyLine(XWPFParagraph paragraph, XWPFRun run, int count) {
        for (int i = 0; i < count; i++) {
            run.addBreak();
        }
    }

    protected XWPFRun createRunWithFormatting(XWPFParagraph paragraph) {
        XWPFRun run = paragraph.createRun();
        run.setFontFamily(FONT);
        run.setFontSize(TEXT_SIZE);
        return run;
    }
}