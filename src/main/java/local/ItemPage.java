package local;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends BasePage {

    @FindBy(xpath = "//*[@class='inventory_details_name large_size']")
    private WebElement itemName;

    @FindBy(xpath = "//*[@class='inventory_details_desc large_size']")
    private WebElement itemDescription;

    @FindBy(xpath = "//*[@class='inventory_details_price']")
    private WebElement itemPrice;

    public ItemPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getItemName() {
        return itemName;
    }

    public WebElement getItemDescription() {
        return itemDescription;
    }

    public WebElement getItemPrice() {
        return itemPrice;
    }

}
