package local;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class InventoryPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(), 'Products')]")
    private WebElement productsTitle;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement closeMenuButton;

    @FindBy(linkText = "Logout")
    private WebElement logout;

    @FindBy(id = "about_sidebar_link")
    private WebElement about;

    @FindBy(className = "shopping_cart_link")
    private WebElement cart;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemsNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemsPrices;

    @FindBy(xpath = "//*[@class='pricebar']/button")
    private List<WebElement> itemsButtons;

    @FindBy(className = "inventory_item_desc")
    private List<WebElement> itemsDescriptions;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(xpath = "//option[@value='az']")
    private WebElement optionAToZ;

    @FindBy(xpath = "//option[@value='za']")
    private WebElement optionZToA;

    @FindBy(xpath = "//option[@value='lohi']")
    private WebElement optionLowToHigh;

    @FindBy(xpath = "//option[@value='hilo']")
    private WebElement optionHighToLow;

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getProductsTitle() {
        return productsTitle;
    }

    public WebElement getCartBadge() {
        return cartBadge;
    }

    public List<WebElement> getItemsNames() {
        return itemsNames;
    }

    public List<WebElement> getItemsPrices() {
        return itemsPrices;
    }

    public List<WebElement> getItemsButtons() {
        return itemsButtons;
    }

    public List<WebElement> getItemsDescriptions() {
        return itemsDescriptions;
    }

    public WebElement getOptionAToZ() {
        return optionAToZ;
    }

    public WebElement getOptionZToA() {
        return optionZToA;
    }

    public WebElement getOptionLowToHigh() {
        return optionLowToHigh;
    }

    public WebElement getOptionHighToLow() {
        return optionHighToLow;
    }

    public MainPage goBack() {
        driver.navigate().back();
        return new MainPage();
    }

    public MainPage logout() {
        wait.until(d -> menuButton.isDisplayed());
        menuButton.click();
        wait.until(d -> logout.isDisplayed());
        logout.click();
        return new MainPage();
    }

    public InventoryPage sortByNameAToZ() {
        wait.until(d -> sortDropdown.isDisplayed());
        sortDropdown.click();
        optionAToZ.click();
        return this;
    }

    public InventoryPage sortByNameZToA() {
        wait.until(d -> sortDropdown.isDisplayed());
        sortDropdown.click();
        optionZToA.click();
        return this;
    }

    public InventoryPage sortByPriceLowToHigh() {
        wait.until(d -> sortDropdown.isDisplayed());
        sortDropdown.click();
        optionLowToHigh.click();
        return this;
    }

    public InventoryPage sortByPriceHighToLow() {
        wait.until(d -> sortDropdown.isDisplayed());
        sortDropdown.click();
        optionHighToLow.click();
        return this;
    }

    public InventoryPage addToCart(int itemNumber) {
        if (itemNumber < 1 || itemNumber > 6)
            throw new IllegalArgumentException("Value must be between 1 and 6.");
        int index = itemNumber - 1;
        wait.until(d -> itemsButtons.get(index).isDisplayed());
        if (!Objects.equals(itemsButtons.get(index).getText(), "Add to cart"))
            throw new IllegalStateException("Button caption must be \"Add to cart\".");
        itemsButtons.get(index).click();
        return this;
    }

    public InventoryPage remove(int itemNumber) {
        if (itemNumber < 1 || itemNumber > 6)
            throw new IllegalArgumentException("Value must be between 1 and 6.");
        int index = itemNumber - 1;
        if (!Objects.equals(itemsButtons.get(index).getText(), "Remove"))
            throw new IllegalStateException("Button caption must be \"Remove\".");
        itemsButtons.get(index).click();
        return this;
    }

    public ItemPage clickItem(int itemNumber) {
        if (itemNumber < 1 || itemNumber > 6)
            throw new IllegalArgumentException("Value must be between 1 and 6.");
        int index = itemNumber - 1;
        wait.until(d -> itemsNames.get(index).isDisplayed());
        itemsNames.get(index).click();
        return new ItemPage();
    }

    public CartPage openCart() {
        cart.click();
        return new CartPage();
    }

}
