/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rumaks.docx.templates;

import java.util.Map;
import org.apache.poi.xwpf.usermodel.*;

public class Invoice extends Document {
    public Invoice(Map<String, String> data) {
        super(data);
    }

    @Override
    public void generateContent() {
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("СЧЁТ-ФАКТУРА №" + data.get("number"));
        titleRun.setBold(true);
        titleRun.setFontSize(16);

        XWPFParagraph info = document.createParagraph();
        XWPFRun run = info.createRun();
        run.setText("Поставщик: " + data.get("provider"));
        run.addBreak();
        run.setText("Покупатель: " + data.get("customer"));
        run.addBreak();
        run.setText("Дата: " + data.get("date"));
        run.addBreak();

        // Таблица с товарами
        XWPFTable table = document.createTable();
        // Заголовки таблицы
        XWPFTableRow headerRow = table.getRow(0);
        headerRow.getCell(0).setText("Товар/Услуга");
        headerRow.addNewTableCell().setText("Количество");
        headerRow.addNewTableCell().setText("Цена");
        headerRow.addNewTableCell().setText("Сумма");

        // Данные (можно сделать динамически)
        XWPFTableRow row1 = table.createRow();
        row1.getCell(0).setText(data.get("item1"));
        row1.getCell(1).setText(data.get("quantity1"));
        row1.getCell(2).setText(data.get("price1"));
        row1.getCell(3).setText(data.get("sum1"));

        XWPFParagraph total = document.createParagraph();
        XWPFRun totalRun = total.createRun();
        totalRun.setText("Итого: " + data.get("total") + " руб.");
        totalRun.setBold(true);
    }
}

