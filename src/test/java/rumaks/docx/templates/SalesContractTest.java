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
public class SalesContractTest {
    Map<String, String> data;
    public SalesContractTest() {
        data = new HashMap<>();
        data.put("contractNumber", "45-КП/2025");
        data.put("contractDate", "11.06.2025");
        data.put("city", "Санкт-Петербург");
        data.put("sellerFullName", "ООО 'Продавец'");
        data.put("sellerSignatory", "Сидоров С.С.");
        data.put("sellerAddress", "г. Санкт-Петербург, ул. Главная, д. 10");
        data.put("sellerINN", "7801234567");
        data.put("sellerAccount", "40702810900000000001");
        data.put("sellerBank", "АО 'Банк'");
        data.put("sellerBIK", "044525225");
        data.put("buyerFullName", "ООО 'Покупатель'");
        data.put("buyerSignatory", "Кузнецов К.К.");
        data.put("buyerAddress", "г. Екатеринбург, ул. Центральная, д. 5");
        data.put("buyerINN", "6601234567");
        data.put("buyerAccount", "40702810900000000002");
        data.put("buyerBank", "АО 'Банк2'");
        data.put("buyerBIK", "046577777");
        data.put("productDescription", "Компьютерная техника");
        data.put("quantity", "10");
        data.put("unit", "шт.");
        data.put("totalPrice", "500000");
        data.put("totalPriceWords", "Пятьсот тысяч");
        data.put("paymentTerms", "100% предоплата в течение 3 банковских дней");
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
     * Test of generateContent method, of class SalesContract.
     */
    @Test
    public void testGenerateContent() throws IOException {
        var filePath = System.getProperty("user.home") + "\\sales.docx";
        

        var instance = new SalesContract(data);
        instance.generateContent();
        instance.saveToFile(filePath);
        
        assertTrue(new File(filePath).exists());

    }
    
}
