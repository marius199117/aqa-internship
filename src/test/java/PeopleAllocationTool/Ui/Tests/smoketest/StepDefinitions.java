package PeopleAllocationTool.Ui.Tests.smoketest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;


public class StepDefinitions extends CommonDefinitions {

    // GIVEN Steps

    @Given("^I Open The Browser$")
    public void initialize() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();


    }

    @Given("^I Close The Browser$")
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Given("^I Navigate To \"(.*)\" Page$")
    public void i_Navigate_To_Page(String value) {
        String[] listUrl = value.split("_");
        String rootUrl = listUrl[0];
        String pathUrl = listUrl[1];
        Assert.assertNotNull("Base URL is null! ", rootUrl);
        driver.navigate().to(properties.getProperty(rootUrl) + environment.resolve(pathUrl));

        JavascriptExecutor js = (JavascriptExecutor) driver;

    }

    @When("I Login With Valid Token")
    public void i_Login_With_Valid_Token() {
        driver.findElement(By.xpath(environment.resolve("loginPage.detailsBtn"))).click();
        driver.findElement(By.xpath(environment.resolve("loginPage.proceedBtn"))).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("localStorage.setItem(arguments[0],arguments[1])", "PATPermissions", environment.resolve("tokens.PATPermissions"));
        js.executeScript("localStorage.setItem(arguments[0],arguments[1])", "PATool.componentsState", environment.resolve("tokens.PAToolComponentsState"));
        js.executeScript("localStorage.setItem(arguments[0],arguments[1])", "PAToken", environment.resolve("tokens.PAToken"));

        driver.navigate().refresh();
    }

    @Then("I Verify That I Am Redirected To {string} Page")
    public void i_Verify_That_I_Am_Redirected_To_Page(String string) {
        String[] listUrl = string.split("_");
        String rootUrl = listUrl[0];
        String pathUrl = listUrl[1];
        String expectedURL = "";
        String redirectURL = "";

        if (pathUrl.contains("3rdPartyPages")) {
            expectedURL = environment.resolve(pathUrl);
            redirectURL = driver.getCurrentUrl();
        } else if (rootUrl.equals("")) {
            System.out.println("Base URL is NULL");
            Assert.assertNotEquals("", rootUrl);
        } else {
            expectedURL = properties.getProperty(rootUrl) + environment.resolve(pathUrl);
            redirectURL = driver.getCurrentUrl();
        }
        Assert.assertEquals(redirectURL, expectedURL);
    }

    @When("I Click On {string} Element")
    public void i_Click_On_Element(String string) {
        waitAndClick(environment.resolve(string), 10);
    }

    @Then("I Verify That {string} Is Displayed")
    public void i_Verify_That_Is_Displayed(String string) {
        Boolean found = waitUntilElementIsDisplayed(string, 15);
        Assert.assertEquals(found, true);
    }

    @When("I Insert Text {string} In The {string} Field")
    public void i_Insert_Text_In_The_Field(String textSearch, String textBar) {
        waitAndType(environment.resolve(textBar), environment.resolve(textSearch), 10);
    }

    @And("I Switch To Open Tab")
    public void and_I_Switch_To_Open_Tab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.close();
        driver.switchTo().window(tabs.get(1));
    }

    @Then("I Verify That I Am Logged Out")
    public void i_Verify_That_I_Am_Logged_Out() {
        Boolean found = waitUntilElementIsDisplayed("loginPage.loginBtn", 15);
        Assert.assertEquals(found, true);
    }

    @When("I Click On Button {string} Under {string} Element")
    public void i_Click_On_Button_Under_Element(String child, String parent) throws InterruptedException {
        searchChildByProperty(environment.resolve(parent), environment.resolve(child));
        findElemByClassAndClick();
    }
}