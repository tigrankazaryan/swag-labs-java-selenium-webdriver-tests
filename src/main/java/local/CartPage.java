package local;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemsNames;

    @FindBy(className = "inventory_item_desc")
    private List<WebElement> itemsDescriptions;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemsPrices;

    @FindBy(className = "cart_quantity")
    private List<WebElement> itemsCounters;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getItemsNames() {
        return itemsNames;
    }

    public List<WebElement> getItemsDescriptions() {
        return itemsDescriptions;
    }

    public List<WebElement> getItemsPrices() {
        return itemsPrices;
    }

    public List<WebElement> getItemsCounters() {
        return itemsCounters;
    }

    public WebElement getCartBadge() {
        return cartBadge;
    }

    public CheckoutClientInformationPage clickCheckout() {
        wait.until(d -> checkoutButton.isDisplayed());
        checkoutButton.click();
        return new CheckoutClientInformationPage();
    }

}
