import io.github.bonigarcia.wdm.WebDriverManager;
import local.BasePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract public class BaseTest {

    protected WebDriver driver;
    protected ChromeOptions options;
    protected WebDriverWait wait;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60L));
        //driver.manage().window().maximize();
        BasePage.setup(driver, wait);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

}
