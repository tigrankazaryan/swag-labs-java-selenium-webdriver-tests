package local;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutClientInformationPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(xpath = "//*[contains(text(), 'Error: First Name is required')]")
    private WebElement noFirstNameErrorMessage;

    @FindBy(xpath = "//*[contains(text(), 'Error: Last Name is required')]")
    private WebElement noLastNameErrorMessage;

    @FindBy(xpath = "//*[contains(text(), 'Error: Postal Code is required')]")
    private WebElement noPostalCodeErrorMessage;

    public CheckoutClientInformationPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getNoFirstNameErrorMessage() {
        return noFirstNameErrorMessage;
    }

    public WebElement getNoLastNameErrorMessage() {
        return noLastNameErrorMessage;
    }

    public WebElement getNoPostalCodeErrorMessage() {
        return noPostalCodeErrorMessage;
    }

    public CheckoutClientInformationPage clickContinueWithError() {
        continueButton.click();
        return this;
    }

    public CheckoutClientInformationPage typeFirstName(String firstName) {
        wait.until(d -> firstNameInput.isDisplayed());
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public CheckoutClientInformationPage typeLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public CheckoutClientInformationPage typePostalCode(String postalCode) {
        postalCodeInput.sendKeys(postalCode);
        return this;
    }

    public CheckoutOverviewPage clickContinue() {
        continueButton.click();
        return new CheckoutOverviewPage();
    }

}
