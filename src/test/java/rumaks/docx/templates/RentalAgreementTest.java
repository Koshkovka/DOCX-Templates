/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package rumaks.docx.templates;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kotyo
 */
public class RentalAgreementTest {
    Map<String, String> data;
    public RentalAgreementTest() {
        data = new HashMap<>();
        data.put("agreementNumber", "2025-01");
        data.put("agreementDate", "11.06.2025");
        data.put("city", "Москва");
        data.put("landlordFullName", "ООО 'Арендодатель'");
        data.put("landlordShortName", "Иванов И.И.");
        data.put("tenantFullName", "ООО 'Арендатор'");
        data.put("tenantShortName", "Петров П.П.");
        data.put("propertyDescription", "офисное помещение, 100 кв.м.");
        data.put("propertyAddress", "г. Москва, ул. Примерная, д. 1");
        data.put("startDate", "15.06.2025");
        data.put("endDate", "15.06.2026");
        data.put("rentAmount", "100000");
        data.put("rentAmountWords", "Сто тысяч");
        data.put("paymentDay", "5");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of generateContent method, of class RentalAgreement.
     */
    @Test
    public void testGenerateContent() throws IOException {
        var filePath = System.getProperty("user.home") + "\\rent.docx";
        var instance = new RentalAgreement(data);
        instance.generateContent();
        instance.saveToFile(filePath);
        
        assertTrue(new File(filePath).exists());

    }
    
}
