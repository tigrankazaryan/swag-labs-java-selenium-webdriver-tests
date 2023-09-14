package local;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Thank you for your order!')]")
    private WebElement thankYou;

    public CheckoutCompletePage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getThankYou() {
        return thankYou;
    }

}
