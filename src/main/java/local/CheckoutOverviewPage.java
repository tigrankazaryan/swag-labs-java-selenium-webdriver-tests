package local;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Checkout: Overview')]")
    private WebElement checkoutOverviewTitle;

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

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotal;

    @FindBy(className = "summary_tax_label")
    private WebElement tax;

    @FindBy(xpath = "//*[@class='summary_info_label summary_total_label']")
    private WebElement total;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutOverviewPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getCheckoutOverviewTitle() {
        return checkoutOverviewTitle;
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

    public WebElement getSubtotal() {
        return subtotal;
    }

    public WebElement getTax() {
        return tax;
    }

    public WebElement getTotal() {
        return total;
    }

    public CheckoutCompletePage clickFinish() {
        finishButton.click();
        return new CheckoutCompletePage();
    }

}
