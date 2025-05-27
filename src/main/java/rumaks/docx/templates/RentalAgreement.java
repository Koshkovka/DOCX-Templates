/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rumaks.docx.templates;

import java.util.Map;
import org.apache.poi.xwpf.usermodel.*;

public class RentalAgreement extends Document {
    public RentalAgreement(Map<String, String> data) {
        super(data);
    }

    @Override
    public void generateContent() {
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("ДОГОВОР АРЕНДЫ");
        titleRun.setBold(true);
        titleRun.setFontSize(16);

        XWPFParagraph content = document.createParagraph();
        XWPFRun run = content.createRun();
        run.setText("Арендодатель: " + data.get("landlord"));
        run.addBreak();
        run.setText("Арендатор: " + data.get("tenant"));
        run.addBreak();
        run.setText("Объект аренды: " + data.get("property"));
        run.addBreak();
        run.setText("Срок аренды: " + data.get("duration"));
        run.addBreak();

        XWPFParagraph signatures = document.createParagraph();
        XWPFRun sigRun = signatures.createRun();
        sigRun.setText("Арендодатель: ___________________ (" + data.get("landlord") + ")");
        sigRun.addBreak();
        sigRun.setText("Арендатор: ___________________ (" + data.get("tenant") + ")");
    }
}

