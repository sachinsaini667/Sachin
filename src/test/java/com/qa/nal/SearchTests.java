package com.qa.nal;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import com.qa.nal.utils.ExcelReader;
import io.github.cdimascio.dotenv.Dotenv;
import io.qase.commons.annotation.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.function.Try;
import org.slf4j.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("My Application - Core User Flows")
public class SearchTests extends BaseTest {

        Dotenv dotenv = Dotenv.load();
        private final String username = dotenv.get("APP_USERNAME");
        private final String password = dotenv.get("PASSWORD");
        private final String loginUrl = dotenv.get("DEVDEMO_URL");
        private static final Logger log = LoggerFactory.getLogger(SearchTests.class);
        public static List<Locator> resultList;
        static boolean htmlfound;
        static boolean elementInvisible;

        @Test
        @Order(1)
        @QaseId(1)
        @QaseTitle("Navigate to Login")
        public void navigateToLoginPage() {
                try {
                        page.navigate("https://www.flipkart.com",
                                        new Page.NavigateOptions().setWaitUntil(WaitUntilState.LOAD));

                        page.waitForURL(url -> url.contains("flipkart.com"),
                                        new Page.WaitForURLOptions().setTimeout(45000));
                        Assertions.assertTrue(page.url().contains("flipkart.com"), "Not redirected on login page");
                        log.info("Navigating to login page: {}", loginUrl);
                } catch (Exception e) {
                        log.info("Navigating to login page: {}", page.url());
                        Assertions.fail("Login page not found: " + e.getMessage());
                }
        }

        @Test
        @Order(2)
        @QaseId(2)
        @QaseTitle("Perform Automation actions")
        public void performAutomationActions() {
                try {
                        page.waitForSelector(
                                        "#container > div > div.q8WwEU > div > div > div > div > div > div > div > div > div > div._2nl6Ch.k2FAh4 > div > div > header > div._2msBFL > div:nth-child(2) > div > div");
                        page.locator(
                                        "#container > div > div.q8WwEU > div > div > div > div > div > div > div > div > div > div._2nl6Ch.k2FAh4 > div > div > header > div._2msBFL > div:nth-child(2) > div > div")
                                        .click();
                        page.waitForTimeout(3000);

                        // Wait for the input to be visible
                        page.waitForSelector(
                                        "#container > div > div.VCR99n > div > div.Sm1-5F.col.col-3-5 > div > form > div.I-qZ4M.vLRlQb > input");

                        // Fill the input directly (no .click() needed unless the field needs to be
                        // focused)
                        page.locator(
                                        "#container > div > div.VCR99n > div > div.Sm1-5F.col.col-3-5 > div > form > div.I-qZ4M.vLRlQb > input")
                                        .fill("9667603866");
                        page.waitForTimeout(3000);

                        // Wait for the button to appear
                        page.waitForSelector(
                                        "#container > div > div.VCR99n > div > div.Sm1-5F.col.col-3-5 > div > form > div.LSOAQH > button");

                        // Click the button
                        page.locator(
                                        "#container > div > div.VCR99n > div > div.Sm1-5F.col.col-3-5 > div > form > div.LSOAQH > button")
                                        .click();

                        page.waitForTimeout(20000);

                } catch (Exception e) {
                        log.info("Test Failed: {}", e.getMessage());
                        Assertions.fail("Test Failed: " + e.getMessage());
                }
        }

        @Test
        @Order(3)
        @QaseId(3)
        @QaseTitle("Perform Automation ")
        public void performAutomation() {
                List<String> searchQueries = ExcelReader.readQueriesFromExcel(
                                "src/test/Resources/FIipkartProduct.xlsx",
                                "Product");

                Random rnd = new Random();
                int randomIndex = rnd.nextInt(searchQueries.size());

                String product = searchQueries.get(randomIndex);
                try {

                        page.waitForSelector(
                                        "#container > div > div.q8WwEU > div > div > div > div > div > div > div > div > div > div._2nl6Ch.k2FAh4 > div > div > header > div._3ZqtNW > div._3NorZ0._3jeYYh > form > div > div > input");
                        page.locator(
                                        "#container > div > div.q8WwEU > div > div > div > div > div > div > div > div > div > div._2nl6Ch.k2FAh4 > div > div > header > div._3ZqtNW > div._3NorZ0._3jeYYh > form > div > div > input")
                                        .fill(product);

                        page.waitForTimeout(2000);

                        page.locator(
                                        "#container > div > div.q8WwEU > div > div > div > div > div > div > div > div > div > div._2nl6Ch.k2FAh4 > div > div > header > div._3ZqtNW > div._3NorZ0._3jeYYh > form > div > div > input")
                                        .press("Enter");

                        page.waitForTimeout(2000);

                        // Wait for the selector to be visible/attached
                        page.locator(
                                        "#container > div > div.nt6sNV.JxFEK3._48O0EI > div.DOjaWF.YJG4Cf > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(1)");

                        // Press Enter on that element
                        page.locator(
                                        "#container > div > div.nt6sNV.JxFEK3._48O0EI > div.DOjaWF.YJG4Cf > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(1)")
                                        .press("Enter");

                        // Optional wait
                        page.waitForTimeout(2000);

                } catch (Exception e) {
                        log.info("Test Failed: {}", e.getMessage());
                        Assertions.fail("Test Failed: " + e.getMessage());

                }
        }

        // sachin tanwar
}