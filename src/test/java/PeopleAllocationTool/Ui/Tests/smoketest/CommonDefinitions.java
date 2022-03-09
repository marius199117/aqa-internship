package PeopleAllocationTool.Ui.Tests.smoketest;

import PeopleAllocationTool.Ui.Tests.Environment;
import PeopleAllocationTool.Ui.Tests.Utility;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;


public class CommonDefinitions {
    public static final String SCREENSHOTS_PATH = "./test-logs";
    public static WebDriver driver = null;
    public static Properties properties = Utility.getPropertiesFromFile("config.properties");
    public static Environment environment = Environment.newInstance("PeopleAllocationTool/Ui/Tests/context.json");
    public String var;

    public void waitAndClick(String xpath, int seconds) {
        WebDriverWait waiter = new WebDriverWait(driver, seconds);
        waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void findElemByClassAndClick() throws InterruptedException {
        String child = getVar();
        String clickXpath = changeVarFromXpath(environment.resolve("pagination.xpathTemplate"), child);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,600)", "");

        Thread.sleep(1000);
        driver.findElement(By.xpath(clickXpath)).click();
    }

    public void waitAndType(String xpathForStatedTextBox, String stringToType, int seconds) {
        WebDriverWait waiter = new WebDriverWait(driver, seconds);
        waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathForStatedTextBox)));
        WebElement statedTextBox = findElementByXPath(xpathForStatedTextBox);
        Assert.assertNotNull(statedTextBox);
        statedTextBox.sendKeys(stringToType);
    }

    public boolean waitUntilElementIsDisplayed(String xpath, int seconds) {
        WebDriverWait waiter = new WebDriverWait(driver, seconds);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(environment.resolve(xpath))));
        return driver.findElement(By.xpath(environment.resolve(xpath))).isDisplayed();
    }

    public void searchChildByProperty(String parentElem, String searchedProperty) {
        WebDriverWait waiter = new WebDriverWait(driver, 10);
        waiter.until(ExpectedConditions
                .elementToBeClickable(By.xpath(parentElem)));
        WebElement t = findElementByXPath(parentElem);
        List<WebElement> c = t.findElements(By.xpath(".//*[@role]"));
        for (WebElement i : c) {
            if (i.getAttribute("role").equals(environment.resolve(searchedProperty))) {
                setVar(i.getAttribute("class"));
            }
        }
    }

    public void policyProcessing() {
        WebElement acceptAndClose = findElementByXPath(environment.resolve("policy"));
        if (acceptAndClose != null && acceptAndClose.isDisplayed()) {
            acceptAndClose.click();
        }
    }

    public boolean isPageOnHost(String host) {
        boolean isOnHost = false;
        try {
            isOnHost = new URL(driver.getCurrentUrl()).getHost().contains(host);
        } catch (MalformedURLException e) {
        }
        return isOnHost;
    }

    public WebElement findElementByXPath(String xPathToSearchFor) {
        try {
            return driver.findElement(By.xpath(xPathToSearchFor));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    public String changeVarFromXpath(String xpath, String childClass) {
        try {
            String xpathString = environment.resolve(xpath);
            String token = "var";
            return xpathString.replace(token, childClass);
        } catch (NullPointerException e) {
            System.out.println("ChildElement Xpath is Null");
            return null;
        }
    }
}
