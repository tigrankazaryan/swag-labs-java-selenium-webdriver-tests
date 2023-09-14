package local;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    public static final String URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[contains(text(), 'Epic sadface: Sorry, this user has been locked out.')]")
    private WebElement blockedUserErrorMessage;

    @FindBy(xpath = "//*[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]")
    private WebElement noSuchUsernameAndPasswordErrorMessage;

    @FindBy(xpath = "//*[contains(text(), 'Epic sadface: Username is required')]")
    private WebElement noUsernameErrorMessage;

    @FindBy(xpath = "//*[contains(text(), 'Epic sadface: Password is required')]")
    private WebElement noPasswordErrorMessage;

    @FindBy(xpath = "//*[contains(text(), 'Epic sadface: You can only access')]")
    private WebElement noAuthorizationErrorMessage;

    public MainPage() {
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getBlockedUserErrorMessage() {
        return blockedUserErrorMessage;
    }

    public WebElement getNoSuchUsernameAndPasswordErrorMessage() {
        return noSuchUsernameAndPasswordErrorMessage;
    }

    public WebElement getNoUsernameErrorMessage() {
        return noUsernameErrorMessage;
    }

    public WebElement getNoPasswordErrorMessage() {
        return noPasswordErrorMessage;
    }

    public WebElement getNoAuthorizationErrorMessage() {
        return noAuthorizationErrorMessage;
    }

    public MainPage typeUsername(String username) {
        wait.until(d -> usernameInput.isDisplayed());
        usernameInput.sendKeys(username);
        return this;
    }

    public InventoryPage userLogin(String username, String password) {
        typeUsername(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new InventoryPage();
    }

    public InventoryPage userLoginByPressingEnter(String username, String password) {
        typeUsername(username);
        passwordInput.sendKeys(password, Keys.ENTER);
        return new InventoryPage();
    }

    public MainPage incorrectUserLogin(String username, String password) {
        typeUsername(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        return this;
    }

    public MainPage incorrectUserLogin() {
        wait.until(d -> loginButton.isDisplayed());
        loginButton.click();
        return this;
    }

    public MainPage incorrectUserLogin(String password) {
        wait.until(d -> passwordInput.isDisplayed());
        passwordInput.sendKeys(password);
        loginButton.click();
        return this;
    }

    public InventoryPage goForward() {
        driver.navigate().forward();
        return new InventoryPage();
    }

    public MainPage goBackAfterLogout() {
        driver.navigate().back();
        return this;
    }

}
