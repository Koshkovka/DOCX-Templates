package rumaks.docx.templates;

import java.util.Map;
import org.apache.poi.xwpf.usermodel.*;

public class RentalAgreement extends Document {
    public RentalAgreement(Map<String, String> data) {
        super(data);
    }

    @Override
    public void generateContent() {
        // Разбираем дату на компоненты
        String[] dateParts = parseDate(data.getOrDefault("agreementDate", ""));
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];
        
        String[] startDateParts = parseDate(data.getOrDefault("startDate", ""));
        String startDay = startDateParts[0];
        String startMonth = startDateParts[1];
        String startYear = startDateParts[2];
        
        String[] endDateParts = parseDate(data.getOrDefault("endDate", ""));
        String endDay = endDateParts[0];
        String endMonth = endDateParts[1];
        String endYear = endDateParts[2];

        // Заголовок документа
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setFontFamily(FONT);
        titleRun.setFontSize(TITLE_SIZE);
        titleRun.setBold(true);
        titleRun.setText("ДОГОВОР АРЕНДЫ № " + data.getOrDefault("agreementNumber", ""));
        addEmptyLine(title, titleRun, 1);

        // Место и дата заключения
        XWPFParagraph details = document.createParagraph();
        XWPFRun detailsRun = createRunWithFormatting(details);
        detailsRun.setText("г. " + data.getOrDefault("city", "") + " «" + day + "» " + 
                          month + " " + year + " г.");
        addEmptyLine(details, detailsRun, 2);

        // Стороны договора
        XWPFParagraph parties = document.createParagraph();
        XWPFRun partiesRun = createRunWithFormatting(parties);
        partiesRun.setText(data.getOrDefault("landlordFullName", "") + 
                         ", именуемый(ая) в дальнейшем «Арендодатель», с одной стороны, и");
        partiesRun.addBreak();
        partiesRun.setText(data.getOrDefault("tenantFullName", "") + 
                         ", именуемый(ая) в дальнейшем «Арендатор», с другой стороны, заключили настоящий договор о нижеследующем:");
        addEmptyLine(parties, partiesRun, 2);

        // Предмет договора
        addSectionTitle("1. ПРЕДМЕТ ДОГОВОРА");
        XWPFParagraph subject = document.createParagraph();
        XWPFRun subjectRun = createRunWithFormatting(subject);
        subjectRun.setText("1.1. Арендодатель предоставляет, а Арендатор принимает во временное владение и пользование следующее имущество: " + 
                          data.getOrDefault("propertyDescription", "") + ", расположенное по адресу: " + 
                          data.getOrDefault("propertyAddress", ""));
        addEmptyLine(subject, subjectRun, 1);
        
        subjectRun.setText("1.2. Имущество передаётся в состоянии, соответствующем его назначению.");
        addEmptyLine(subject, subjectRun, 2);

        // Срок договора
        addSectionTitle("2. СРОК ДЕЙСТВИЯ ДОГОВОРА");
        XWPFParagraph term = document.createParagraph();
        XWPFRun termRun = createRunWithFormatting(term);
        termRun.setText("2.1. Настоящий договор заключён на срок с «" + startDay + "» " + 
                       startMonth + " " + startYear + " г. по «" + endDay + "» " + 
                       endMonth + " " + endYear + " г.");
        addEmptyLine(term, termRun, 2);

        // Арендная плата
        addSectionTitle("3. АРЕНДНАЯ ПЛАТА");
        XWPFParagraph payment = document.createParagraph();
        XWPFRun paymentRun = createRunWithFormatting(payment);
        paymentRun.setText("3.1. Арендная плата составляет " + 
                         data.getOrDefault("rentAmount", "") + " (" + 
                         data.getOrDefault("rentAmountWords", "") + ") рублей в месяц.");
        addEmptyLine(payment, paymentRun, 1);
        
        paymentRun.setText("3.2. Оплата производится не позднее " + 
                         data.getOrDefault("paymentDay", "") + 
                         " числа каждого месяца на расчётный счёт Арендодателя.");
        addEmptyLine(payment, paymentRun, 2);

        // Подписи сторон
        addSectionTitle("4. ПОДПИСИ СТОРОН");
        XWPFParagraph signatures = document.createParagraph();
        signatures.setAlignment(ParagraphAlignment.BOTH);
        
        XWPFRun landlordRun = createRunWithFormatting(signatures);
        landlordRun.setText("Арендодатель: ___________________ / " + 
                          data.getOrDefault("landlordShortName", "") + " /");
        landlordRun.addBreak();
        
        XWPFRun tenantRun = createRunWithFormatting(signatures);
        tenantRun.setText("Арендатор: ___________________ / " + 
                        data.getOrDefault("tenantShortName", "") + " /");
    }

    private String[] parseDate(String date) {
        // Разбиваем дату формата "dd.mm.yy" на компоненты
        String[] parts = date.split("\\.");
        if (parts.length == 3) {
            return parts;
        }
        // Возвращаем значения по умолчанию, если дата невалидна
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