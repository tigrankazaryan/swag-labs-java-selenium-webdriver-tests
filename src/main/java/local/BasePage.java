package local;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class BasePage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public static void setup(WebDriver _driver, WebDriverWait _wait) {
        driver = _driver;
        wait = _wait;
    }

}
