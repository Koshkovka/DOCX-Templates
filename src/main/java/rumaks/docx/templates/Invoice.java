package rumaks.docx.templates;

import java.math.BigInteger;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

public class Invoice extends Document {
    private static final int COLUMNS_COUNT = 6;
    private List<InvoiceItem> items = new ArrayList<>();

    public Invoice(Map<String, String> data) {
        super(data);
        parseItems(data);
    }

    @Override
    public void generateContent() {
        // Заголовок документа
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setFontFamily(FONT);
        titleRun.setFontSize(TITLE_SIZE);
        titleRun.setBold(true);
        titleRun.setText("СЧЕТ-ФАКТУРА № " + data.getOrDefault("number", "") + " от " + formatDate(data.getOrDefault("date", "")));
        addEmptyLine(title, titleRun, 2);

        // Реквизиты поставщика и покупателя
        addPartyInfo("Поставщик:", 
                   data.getOrDefault("providerName", ""),
                   data.getOrDefault("providerAddress", ""),
                   data.getOrDefault("providerINN", ""),
                   data.getOrDefault("providerKPP", ""));

        addPartyInfo("Покупатель:", 
                   data.getOrDefault("customerName", ""),
                   data.getOrDefault("customerAddress", ""),
                   data.getOrDefault("customerINN", ""),
                   data.getOrDefault("customerKPP", ""));

        addEmptyLine(document.createParagraph(), titleRun, 1);

        // Таблица с товарами
        XWPFTable table = document.createTable(1, COLUMNS_COUNT);
        styleTable(table);

        // Заголовки таблицы
        setTableHeader(table, 0, "№");
        setTableHeader(table, 1, "Наименование товара");
        setTableHeader(table, 2, "Ед. изм.");
        setTableHeader(table, 3, "Количество");
        setTableHeader(table, 4, "Цена");
        setTableHeader(table, 5, "Сумма");

        // Заполнение таблицы товарами
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            InvoiceItem item = items.get(i);
            XWPFTableRow row = table.createRow();
            
            setTableCell(row, 0, String.valueOf(i + 1));
            setTableCell(row, 1, item.name);
            setTableCell(row, 2, item.unit);
            setTableCell(row, 3, String.format("%.2f", item.quantity));
            setTableCell(row, 4, String.format("%.2f", item.price));
            
            double sum = item.quantity * item.price;
            setTableCell(row, 5, String.format("%.2f", sum));
            
            total += sum;
        }

        // Итоговая строка
        XWPFTableRow totalRow = table.createRow();
        setTableCell(totalRow, 0, "Итого:", 4);
        setTableCell(totalRow, 5, String.format("%.2f", total), true);

        // НДС строка
        XWPFTableRow vatRow = table.createRow();
        setTableCell(vatRow, 0, "В том числе НДС:", 4);
        setTableCell(vatRow, 5, String.format("%.2f", total * 0.2), true);

        // Всего с НДС
        XWPFTableRow totalWithVatRow = table.createRow();
        setTableCell(totalWithVatRow, 0, "Всего к оплате:", 4);
        setTableCell(totalWithVatRow, 5, String.format("%.2f", total * 1.2), true);

        // Подписи
        addSignatures();
    }

    private void parseItems(Map<String, String> data) {
        int i = 1;
        while (data.containsKey("itemName" + i)) {
            try {
                String name = data.get("itemName" + i);
                String unit = data.getOrDefault("itemUnit" + i, "шт.");
                double quantity = Double.parseDouble(data.get("itemQuantity" + i));
                double price = Double.parseDouble(data.get("itemPrice" + i));
                
                items.add(new InvoiceItem(name, unit, quantity, price));
                i++;
            } catch (NumberFormatException e) {
                break;
            }
        }
    }

    private void addPartyInfo(String title, String name, String address, String inn, String kpp) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = createRunWithFormatting(paragraph);
        run.setText(title);
        run.addBreak();
        run.setText(name);
        run.addBreak();
        run.setText("Адрес: " + address);
        run.addBreak();
        run.setText("ИНН/КПП: " + inn + " / " + kpp);
        addEmptyLine(paragraph, run, 1);
    }

    private void styleTable(XWPFTable table) {
        table.setWidth("100%");
        table.setTableAlignment(TableRowAlign.CENTER);
        
        // Настройка границ таблицы
        table.setInsideHBorder(XWPFBorderType.SINGLE, 1, 0, "000000");
        table.setInsideVBorder(XWPFBorderType.SINGLE, 1, 0, "000000");
        table.setTopBorder(XWPFBorderType.SINGLE, 1, 0, "000000");
        table.setBottomBorder(XWPFBorderType.SINGLE, 1, 0, "000000");
        table.setLeftBorder(XWPFBorderType.SINGLE, 1, 0, "000000");
        table.setRightBorder(XWPFBorderType.SINGLE, 1, 0, "000000");
    }

    private void setTableHeader(XWPFTable table, int col, String text) {
        XWPFTableCell cell = table.getRow(0).getCell(col);
        cell.setText(text);
        cell.setColor("DDDDDD");
        XWPFParagraph paragraph = cell.getParagraphs().get(0);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.getRuns().forEach(r -> r.setBold(true));
    }

    private void setTableCell(XWPFTableRow row, int col, String text) {
        setTableCell(row, col, text, 1, false);
    }

    private void setTableCell(XWPFTableRow row, int col, String text, boolean bold) {
        setTableCell(row, col, text, 1, bold);
    }

    private void setTableCell(XWPFTableRow row, int col, String text, int colspan) {
        setTableCell(row, col, text, colspan, false);
    }

    private void setTableCell(XWPFTableRow row, int col, String text, int colspan, boolean bold) {
        XWPFTableCell cell = row.getCell(col);
        cell.setText(text);
        
        if (colspan > 1) {
            for (int i = 1; i < colspan; i++) {
                row.addNewTableCell();
            }
            cell.getCTTc().addNewTcPr().addNewGridSpan().setVal(BigInteger.valueOf(colspan));
        }
        
        XWPFParagraph paragraph = cell.getParagraphs().get(0);
        paragraph.setAlignment(col == 1 ? ParagraphAlignment.LEFT : ParagraphAlignment.RIGHT);
        if (bold) {
            paragraph.getRuns().forEach(r -> r.setBold(true));
        }
    }

    private void addSignatures() {
        
        
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = createRunWithFormatting(paragraph);
        addEmptyLine(paragraph, run, 2);
        run.setText("Руководитель организации ___________________ / " + 
                   data.getOrDefault("providerDirector", "") + " /");
        run.addBreak();
        run.setText("Главный бухгалтер ___________________ / " + 
                   data.getOrDefault("providerAccountant", "") + " /");
    }

    private String formatDate(String date) {
        String[] parts = date.split("\\.");
        return parts.length == 3 ? parts[0] + "." + parts[1] + "." + parts[2] : date;
    }

    private static class InvoiceItem {
        String name;
        String unit;
        double quantity;
        double price;

        public InvoiceItem(String name, String unit, double quantity, double price) {
            this.name = name;
            this.unit = unit;
            this.quantity = quantity;
            this.price = price;
        }
    }
}