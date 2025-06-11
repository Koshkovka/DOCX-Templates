/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package rumaks.docx.templates;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author kotyo
 */
public class InvoiceTest {
    Map<String, String> data;
    public InvoiceTest() {
        data = new HashMap<>();
        data.put("number", "123");
        data.put("date", "15.05.2023");
        data.put("providerName", "ООО 'Ромашка'");
        data.put("providerAddress", "г. Москва, ул. Ленина, д.1");
        data.put("providerINN", "1234567890");
        data.put("providerKPP", "987654321");
        data.put("providerDirector", "Иванов И.И.");
        data.put("providerAccountant", "Петрова П.П.");
        data.put("customerName", "ООО 'Василек'");
        // Товарные позиции
        data.put("itemName1", "Ноутбук Lenovo IdeaPad");
        data.put("itemUnit1", "шт.");
        data.put("itemQuantity1", "2");
        data.put("itemPrice1", "45000.50");
        data.put("itemName2", "Мышь беспроводная");
        data.put("itemUnit2", "шт.");
        data.put("itemQuantity2", "5");
        data.put("itemPrice2", "1200.00");
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    /**
     * Test of generateContent method, of class Invoice.
     */
    @org.junit.Test
    //Проверка, файла на создание
    public void testGenerateContent() throws IOException {
        var filePath = System.getProperty("user.home") + "\\invoice.docx";

        var invoice = new Invoice(data);
        invoice.generateContent();
        invoice.saveToFile(filePath);
        
        assertTrue(new File(filePath).exists());

    }
    
}
