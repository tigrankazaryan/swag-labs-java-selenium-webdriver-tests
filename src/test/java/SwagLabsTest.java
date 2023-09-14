import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import local.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static local.ConfigProvider.*;
import static local.CustomFunctions.invertFirstCharacter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Feature("Swag Labs tests")
public class SwagLabsTest extends BaseTest {

    @Test
    @Order(1)
    @DisplayName("Standard user login")
    @Description("Authorization of standard user with correct username and password.")
    public void standardUserLogin() {
        wait.until(d -> new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                getProductsTitle().isDisplayed());
    }

    @Test
    @Order(2)
    @DisplayName("Standard user login by pressing Enter")
    @Description("Authorization of standard user with correct username and password using Enter key instead of clicking Login button.")
    public void standardUserLoginByPressingEnter() {
        wait.until(d -> new MainPage().userLoginByPressingEnter(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                getProductsTitle().isDisplayed());
    }

    @Test
    @Order(3)
    @DisplayName("Blocked user login")
    @Description("Attempt to authorize blocked user.")
    public void blockedUserLogin() {
        wait.until(d -> new MainPage().incorrectUserLogin(BLOCKED_USER_LOGIN, BLOCKED_USER_PASSWORD).
                getBlockedUserErrorMessage().isDisplayed());
    }

    @Test
    @Order(4)
    @DisplayName("Wrong password")
    @Description("Attempt to log in with name of existing user and wrong password.")
    public void wrongPassword() {
        wait.until(d -> new MainPage().incorrectUserLogin(STANDARD_USER_LOGIN, "SomeWrongPassword").
                getNoSuchUsernameAndPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Order(5)
    @DisplayName("Wrong username and correct password")
    @Description("Attempt to log in with nonexistent username and correct password of some user.")
    public void wrongUsernameAndCorrectPassword() {
        wait.until(d -> new MainPage().incorrectUserLogin("SomeWrongUsername", STANDARD_USER_PASSWORD).
                getNoSuchUsernameAndPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Order(6)
    @DisplayName("Wrong case of correct username")
    @Description("Attempt to authorize existing user but with mistaken case in username.")
    public void wrongCaseOfCorrectUsername() {
        wait.until(d -> new MainPage().incorrectUserLogin(invertFirstCharacter(STANDARD_USER_LOGIN), STANDARD_USER_PASSWORD).
                getNoSuchUsernameAndPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Order(7)
    @DisplayName("Wrong case of correct password")
    @Description("Attempt to authorize existing user but with mistaken case in password.")
    public void wrongCaseOfCorrectPassword() {
        wait.until(d -> new MainPage().incorrectUserLogin(STANDARD_USER_LOGIN, invertFirstCharacter(STANDARD_USER_PASSWORD)).
                getNoSuchUsernameAndPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Order(8)
    @DisplayName("Empty username and password")
    @Description("Clicking \"Login\" button with username and password input fields not filled out.")
    public void emptyUsernameAndPassword() {
        wait.until(d -> new MainPage().incorrectUserLogin().getNoUsernameErrorMessage().isDisplayed());
    }

    @Test
    @Order(9)
    @DisplayName("Empty username")
    @Description("Clicking \"Login\" button with username input field not filled out. Password of existing user is used.")
    public void emptyUsername() {
        wait.until(d -> new MainPage().incorrectUserLogin(STANDARD_USER_PASSWORD).
                getNoUsernameErrorMessage().isDisplayed());
    }

    @Test
    @Order(10)
    @DisplayName("Empty password")
    @Description("Clicking \"Login\" button with password input field not filled out. Name of existing user is used.")
    public void emptyPassword() {
        wait.until(d -> new MainPage().typeUsername(STANDARD_USER_LOGIN).incorrectUserLogin().
                getNoPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Order(11)
    @DisplayName("Navigating after user login")
    @Description("Navigating back and forward in browser after successful authorization and checking authorization for being kept after navigation.")
    public void navigateAfterLogin() {
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD);
        wait.until(d -> inventoryPage.getProductsTitle().isDisplayed());
        MainPage mainPage = inventoryPage.goBack();
        wait.until(d -> mainPage.getLoginButton().isDisplayed());
        wait.until(d -> mainPage.goForward().getProductsTitle().isDisplayed());
    }

    @Test
    @Order(12)
    @DisplayName("Navigating after user logout")
    @Description("Logging out and trying to navigate back to page that requires authorization.")
    public void navigateAfterLoginAndLogout() {
        MainPage mainPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                logout();
        wait.until(d -> mainPage.getLoginButton().isDisplayed());
        wait.until(d -> mainPage.goBackAfterLogout().getNoAuthorizationErrorMessage().isDisplayed());
    }

    @Test
    @Order(13)
    @DisplayName("Sorting items by name (from A to Z)")
    @Description("Sorting items on inventory page by name in ascending order.")
    public void sortingAToZ() {
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                sortByNameAToZ();
        assertTrue(inventoryPage.getOptionAToZ().isSelected(),
                "Checking \"Name (A to Z)\" option for being selected");
        List<WebElement> items = inventoryPage.getItemsNames();
        List<String> itemsNames = items.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> sortedItemsNames = itemsNames.stream().sorted().collect(Collectors.toList());
        assertEquals(sortedItemsNames, itemsNames,
                "Checking items for being sorted by name from A to Z");
    }

    @Test
    @Order(14)
    @DisplayName("Sorting items by name (from Z to A)")
    @Description("Sorting items on inventory page by name in descending order.")
    public void sortingZToA() {
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                sortByNameZToA();
        assertTrue(inventoryPage.getOptionZToA().isSelected(),
                "Checking \"Name (Z to A)\" option for being selected");
        List<WebElement> items = inventoryPage.getItemsNames();
        List<String> itemsNames = items.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> sortedItemsNames = itemsNames.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertEquals(sortedItemsNames, itemsNames,
                "Checking items for being sorted by name from Z to A");
    }

    @Test
    @Order(15)
    @DisplayName("Sorting items by price (from low to high)")
    @Description("Sorting items on inventory page by price in ascending order.")
    public void sortingLowToHigh() {
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                sortByPriceLowToHigh();
        assertTrue(inventoryPage.getOptionLowToHigh().isSelected(),
                "Checking \"Price (low to high)\" option for being selected");
        List<WebElement> prices = inventoryPage.getItemsPrices();
        List<Double> pricesValues = prices.stream().map(x -> Double.valueOf(x.getText().substring(1))).collect(Collectors.toList());
        List<Double> sortedPricesValues = pricesValues.stream().sorted().collect(Collectors.toList());
        assertEquals(sortedPricesValues, pricesValues,
                "Checking items for being sorted by price in ascending order");
    }

    @Test
    @Order(16)
    @DisplayName("Sorting items by price (from high to low)")
    @Description("Sorting items on inventory page by price in descending order.")
    public void sortingHighToLow() {
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                sortByPriceHighToLow();
        assertTrue(inventoryPage.getOptionHighToLow().isSelected(),
                "Checking \"Price (high to low)\" option for being selected");
        List<WebElement> prices = inventoryPage.getItemsPrices();
        List<Double> pricesValues = prices.stream().map(x -> Double.valueOf(x.getText().substring(1))).collect(Collectors.toList());
        List<Double> sortedPricesValues = pricesValues.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertEquals(sortedPricesValues, pricesValues,
                "Checking items for being sorted by price in descending order");
    }

    @Test
    @Order(17)
    @DisplayName("Add to cart and remove")
    @Description("Add an item to cart and then remove it.")
    public void addToCartAndRemove() {
        int itemNumber = 1;
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                addToCart(itemNumber);
        assertEquals("1", inventoryPage.getCartBadge().getText(),
                "Checking for cart badge indicating there is one item in the cart");
        inventoryPage.remove(itemNumber);
        assertEquals("Add to cart", inventoryPage.getItemsButtons().get(itemNumber - 1).getText(),
                "Checking item for being removed from cart");
    }

    @Test
    @Order(18)
    @DisplayName("Add to cart two items")
    @Description("Adding to cart two items and checking cart badge counter.")
    public void addToCartTwoItems() {
        int itemNumber1 = 1;
        int itemNumber2 = 2;
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                addToCart(itemNumber1).addToCart(itemNumber2);
        assertEquals("2", inventoryPage.getCartBadge().getText(),
                "Checking for cart badge indicating there is two items in the cart");
        assertEquals("Remove", inventoryPage.getItemsButtons().get(itemNumber1 - 1).getText(),
                "Checking item for being added to cart");
        assertEquals("Remove", inventoryPage.getItemsButtons().get(itemNumber2 - 1).getText(),
                "Checking item for being added to cart");
    }

    @Test
    @Order(19)
    @DisplayName("Open item page")
    @Description("Opening item page and checking item name, item description, item price.")
    public void openItemPage() {
        Random random = new Random();
        int itemNumber = random.nextInt(6);
        itemNumber++;
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD);
        String itemName = inventoryPage.getItemsNames().get(itemNumber - 1).getText();
        String itemDescription = inventoryPage.getItemsDescriptions().get(itemNumber - 1).getText();
        Double itemPrice = Double.valueOf(inventoryPage.getItemsPrices().get(itemNumber - 1).getText().substring(1));
        ItemPage itemPage = inventoryPage.clickItem(itemNumber);
        assertEquals(itemName, itemPage.getItemName().getText(),
                "Checking that item name is the same as one's on inventory page");
        assertEquals(itemDescription, itemPage.getItemDescription().getText(),
                "Checking that item description is the same as one's on inventory page");
        assertEquals(itemPrice, Double.valueOf(itemPage.getItemPrice().getText().substring(1)),
                "Checking that item price is the same as one's on inventory page");
    }

    @Test
    @Order(20)
    @DisplayName("Empty first name on client information page")
    @Description("Clicking \"Continue\" button on client information page with first name input field being not filled out.")
    public void checkoutFirstNameErrorMessage() {
        wait.until(d -> new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                addToCart(1).openCart().clickCheckout().clickContinueWithError().
                getNoFirstNameErrorMessage().isDisplayed());
    }

    @Test
    @Order(21)
    @DisplayName("Empty last name on client information page")
    @Description("Clicking \"Continue\" button on client information page with last name input field being not filled out.")
    public void checkoutLastNameErrorMessage() {
        wait.until(d -> new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                addToCart(1).openCart().clickCheckout().typeFirstName("Test").
                clickContinueWithError().getNoLastNameErrorMessage().isDisplayed());
    }

    @Test
    @Order(22)
    @DisplayName("Empty postal code on client information page")
    @Description("Clicking \"Continue\" button on client information page with postal code input field being not filled out.")
    public void checkoutPostalCodeErrorMessage() {
        wait.until(d -> new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                addToCart(1).openCart().clickCheckout().typeFirstName("Test").
                typeLastName("Testing").clickContinueWithError().getNoPostalCodeErrorMessage().isDisplayed());
    }

    @Test
    @Order(23)
    @DisplayName("Buying two items")
    @Description("Buying two items and checking item name, item description and item price on at each step.")
    public void twoItemsPurchase() {
        int itemsAmount = 2;
        String firstName = "Test";
        String lastName = "Testing";
        String postalCode = "344000";
        double tax = 3.2;
        List<Integer> itemsNumbers = new ArrayList<Integer>();
        for (int i = 1; i <= itemsAmount; i++)
            itemsNumbers.add(i);
        InventoryPage inventoryPage = new MainPage().userLogin(STANDARD_USER_LOGIN, STANDARD_USER_PASSWORD).
                addToCart(itemsNumbers.get(0)).addToCart(itemsNumbers.get(1));
        List<String> inventoryItemsNames = new ArrayList<String>();
        List<String> inventoryItemsDescriptions = new ArrayList<String>();
        List<Double> inventoryItemsPrices = new ArrayList<Double>();
        double inventoryItemsPricesSum = 0.0;
        for (int i = 0; i < itemsAmount; i++) {
            inventoryItemsNames.add(inventoryPage.getItemsNames().get(i).getText());
            inventoryItemsDescriptions.add(inventoryPage.getItemsDescriptions().get(i).getText());
            inventoryItemsPrices.add(Double.valueOf(inventoryPage.getItemsPrices().get(i).getText().substring(1)));
            inventoryItemsPricesSum+=inventoryItemsPrices.get(i);
        }
        CartPage cartPage = inventoryPage.openCart();
        wait.until(d -> cartPage.getCartBadge().isDisplayed());
        for (int i = 0; i < itemsAmount; i++) {
            assertEquals(inventoryItemsNames.get(i), cartPage.getItemsNames().get(i).getText(),
                    "Checking that items names are the same as ones on inventory page");
            assertEquals(inventoryItemsDescriptions.get(i), cartPage.getItemsDescriptions().get(i).getText(),
                    "Checking that items descriptions are the same as ones on inventory page");
            assertEquals(inventoryItemsPrices.get(i), Double.valueOf(cartPage.getItemsPrices().get(i).getText().substring(1)),
                    "Checking that items prices are the same as ones on inventory page");
            assertEquals("1", cartPage.getItemsCounters().get(i).getText(),
                    "Checking that there are one unit of item");
        }
        CheckoutOverviewPage overviewPage = cartPage.clickCheckout().typeFirstName(firstName).
                typeLastName(lastName).typePostalCode(postalCode).clickContinue();
        wait.until(d -> overviewPage.getCheckoutOverviewTitle().isDisplayed());
        for (int i = 0; i < itemsAmount; i++) {
            assertEquals(inventoryItemsNames.get(i), overviewPage.getItemsNames().get(i).getText(),
                    "Checking that items names are the same as ones on inventory page");
            assertEquals(inventoryItemsDescriptions.get(i), overviewPage.getItemsDescriptions().get(i).getText(),
                    "Checking that items descriptions are the same as ones on inventory page");
            assertEquals(inventoryItemsPrices.get(i), Double.valueOf(overviewPage.getItemsPrices().get(i).getText().substring(1)),
                    "Checking that items prices are the same as ones on inventory page");
            assertEquals("1", overviewPage.getItemsCounters().get(i).getText(),
                    "Checking that there are one unit of item");
        }
        assertEquals(inventoryItemsPricesSum, Double.valueOf(overviewPage.getSubtotal().getText().substring(13)),
                "Checking the sum of all items prices");
        assertEquals(tax, Double.valueOf(overviewPage.getTax().getText().substring(6)),
                "Checking tax value");
        assertEquals(inventoryItemsPricesSum + tax, Double.valueOf(overviewPage.getTotal().getText().substring(8)),
                "Checking total price");
        wait.until(d -> overviewPage.clickFinish().getThankYou().isDisplayed());
    }

}
