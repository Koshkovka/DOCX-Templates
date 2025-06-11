package rumaks.docx.templates;

import java.util.Map;
import org.apache.poi.xwpf.usermodel.*;

public class SalesContract extends Document {
    public SalesContract(Map<String, String> data) {
        super(data);
    }

    @Override
    public void generateContent() {
        // Разбираем дату на компоненты
        String[] dateParts = parseDate(data.getOrDefault("contractDate", ""));

        // Заголовок документа
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setFontFamily(FONT);
        titleRun.setFontSize(TITLE_SIZE);
        titleRun.setBold(true);
        titleRun.setText("ДОГОВОР КУПЛИ-ПРОДАЖИ № " + data.getOrDefault("contractNumber", ""));
        addEmptyLine(title, titleRun, 1);

        // Место и дата заключения
        XWPFParagraph details = document.createParagraph();
        XWPFRun detailsRun = createRunWithFormatting(details);
        detailsRun.setText("г. " + data.getOrDefault("city", "") + " «" + dateParts[0] + "» " + 
                         dateParts[1] + " " + dateParts[2] + " г.");
        addEmptyLine(details, detailsRun, 2);

        // Стороны договора
        XWPFParagraph parties = document.createParagraph();
        XWPFRun partiesRun = createRunWithFormatting(parties);
        partiesRun.setText(data.getOrDefault("sellerFullName", "") + 
                         ", именуемый(ая) в дальнейшем «Продавец», с одной стороны, и");
        partiesRun.addBreak();
        partiesRun.setText(data.getOrDefault("buyerFullName", "") + 
                         ", именуемый(ая) в дальнейшем «Покупатель», с другой стороны, заключили настоящий договор о нижеследующем:");
        addEmptyLine(parties, partiesRun, 2);

        // Предмет договора
        addSectionTitle("1. ПРЕДМЕТ ДОГОВОРА");
        XWPFParagraph subject = document.createParagraph();
        XWPFRun subjectRun = createRunWithFormatting(subject);
        subjectRun.setText("1.1. Продавец обязуется передать в собственность Покупателя, а Покупатель обязуется принять и оплатить следующий товар: " + 
                          data.getOrDefault("productDescription", ""));
        addEmptyLine(subject, subjectRun, 1);
        
        subjectRun.setText("1.2. Количество: " + data.getOrDefault("quantity", "") + " " + 
                         data.getOrDefault("unit", "шт."));
        addEmptyLine(subject, subjectRun, 1);
        
        subjectRun.setText("1.3. Качество и комплектность товара должны соответствовать установленным стандартам и техническим условиям.");
        addEmptyLine(subject, subjectRun, 2);

        // Цена и порядок расчетов
        addSectionTitle("2. ЦЕНА И ПОРЯДОК РАСЧЕТОВ");
        XWPFParagraph price = document.createParagraph();
        XWPFRun priceRun = createRunWithFormatting(price);
        priceRun.setText("2.1. Общая стоимость товара составляет: " + 
                        data.getOrDefault("totalPrice", "") + " (" + 
                        data.getOrDefault("totalPriceWords", "") + ") рублей.");
        addEmptyLine(price, priceRun, 1);
        
        priceRun.setText("2.2. Оплата производится в следующем порядке: " + 
                        data.getOrDefault("paymentTerms", "100% предоплата в течение 3 банковских дней"));
        addEmptyLine(price, priceRun, 2);

        // Обязанности сторон
        addSectionTitle("3. ОБЯЗАННОСТИ СТОРОН");
        XWPFParagraph obligations = document.createParagraph();
        XWPFRun obligationsRun = createRunWithFormatting(obligations);
        obligationsRun.setText("3.1. Продавец обязан:");
        addEmptyLine(obligations, obligationsRun, 1);
        
        obligationsRun.setText("- передать товар в срок, установленный настоящим договором");
        addEmptyLine(obligations, obligationsRun, 1);
        
        obligationsRun.setText("- обеспечить надлежащее качество товара");
        addEmptyLine(obligations, obligationsRun, 1);
        
        obligationsRun.setText("3.2. Покупатель обязан:");
        addEmptyLine(obligations, obligationsRun, 1);
        
        obligationsRun.setText("- принять товар в установленные сроки");
        addEmptyLine(obligations, obligationsRun, 1);
        
        obligationsRun.setText("- оплатить товар в порядке и сроки, предусмотренные договором");
        addEmptyLine(obligations, obligationsRun, 2);

        // Ответственность сторон
        addSectionTitle("4. ОТВЕТСТВЕННОСТЬ СТОРОН");
        XWPFParagraph responsibility = document.createParagraph();
        XWPFRun responsibilityRun = createRunWithFormatting(responsibility);
        responsibilityRun.setText("4.1. За неисполнение или ненадлежащее исполнение обязательств по настоящему договору стороны несут ответственность в соответствии с действующим законодательством Российской Федерации.");
        addEmptyLine(responsibility, responsibilityRun, 2);

        // Реквизиты и подписи сторон
        addSectionTitle("5. РЕКВИЗИТЫ И ПОДПИСИ СТОРОН");
        
        // Реквизиты продавца
        XWPFParagraph sellerDetails = document.createParagraph();
        XWPFRun sellerRun = createRunWithFormatting(sellerDetails);
        sellerRun.setText("Продавец:");
        sellerRun.addBreak();
        sellerRun.setText(data.getOrDefault("sellerFullName", ""));
        sellerRun.addBreak();
        sellerRun.setText("Адрес: " + data.getOrDefault("sellerAddress", ""));
        sellerRun.addBreak();
        sellerRun.setText("ИНН: " + data.getOrDefault("sellerINN", ""));
        sellerRun.addBreak();
        sellerRun.setText("р/с: " + data.getOrDefault("sellerAccount", ""));
        sellerRun.addBreak();
        sellerRun.setText("в " + data.getOrDefault("sellerBank", ""));
        sellerRun.addBreak();
        sellerRun.setText("БИК: " + data.getOrDefault("sellerBIK", ""));
        addEmptyLine(sellerDetails,sellerRun, 1);
        
        sellerRun.setText("___________________ / " + data.getOrDefault("sellerSignatory", "") + " /");
        addEmptyLine(sellerDetails, sellerRun, 2);

        // Реквизиты покупателя
        XWPFParagraph buyerDetails = document.createParagraph();
        XWPFRun buyerRun = createRunWithFormatting(buyerDetails);
        buyerRun.setText("Покупатель:");
        buyerRun.addBreak();
        buyerRun.setText(data.getOrDefault("buyerFullName", ""));
        buyerRun.addBreak();
        buyerRun.setText("Адрес: " + data.getOrDefault("buyerAddress", ""));
        buyerRun.addBreak();
        buyerRun.setText("ИНН: " + data.getOrDefault("buyerINN", ""));
        buyerRun.addBreak();
        buyerRun.setText("р/с: " + data.getOrDefault("buyerAccount", ""));
        buyerRun.addBreak();
        buyerRun.setText("в " + data.getOrDefault("buyerBank", ""));
        buyerRun.addBreak();
        buyerRun.setText("БИК: " + data.getOrDefault("buyerBIK", ""));
        addEmptyLine(buyerDetails, buyerRun, 1);
        
        buyerRun.setText("___________________ / " + data.getOrDefault("buyerSignatory", "") + " /");
    }

    private String[] parseDate(String date) {
        String[] parts = date.split("\\.");
        if (parts.length == 3) {
            return parts;
        }
        return new String[]{"__", "_______", "____"};
    }

    private void addSectionTitle(String titleText) {
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun titleRun = title.createRun();
        titleRun.setFontFamily(FONT);
        titleRun.setFontSize(TEXT_SIZE);
        titleRun.setBold(true);
        titleRun.setText(titleText);
    }
}