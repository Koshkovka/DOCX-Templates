/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rumaks.docx.templates;


import java.util.Map;
import org.apache.poi.xwpf.usermodel.*;

public class SalesContract extends Document {
    public SalesContract(Map<String, String> data) {
        super(data);
    }

    @Override
    public void generateContent() {
        // Создаём заголовок
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("ДОГОВОР КУПЛИ-ПРОДАЖИ");
        titleRun.setBold(true);
        titleRun.setFontSize(16);

        // Добавляем основные данные
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Этот договор заключён между " + data.get("seller") + " (Продавец) и " + 
                   data.get("buyer") + " (Покупатель).");
        run.addBreak();

        run.setText("Предмет договора: " + data.get("product") + ".");
        run.addBreak();

        run.setText("Цена: " + data.get("price") + " руб.");
        run.addBreak();

        // Подписи
        XWPFParagraph signatures = document.createParagraph();
        signatures.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun sigRun = signatures.createRun();
        sigRun.setText("Продавец: ___________________ (" + data.get("seller") + ")");
        sigRun.addBreak();
        sigRun.setText("Покупатель: ___________________ (" + data.get("buyer") + ")");
    }
}

