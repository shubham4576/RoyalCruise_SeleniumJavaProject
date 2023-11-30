package RoyalCruisesTesting;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CruiseTestMain {
    public static WebDriver driver;
    public String baseURL = "https://www.royalcaribbean.com/alaska-cruises";

    public void createDriver() {
        driver = DriverSetup.getWebDriver(baseURL);
    }

    public void handleSignupPopup() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification-banner__section-close")));
            driver.findElement(By.className("notification-banner__section-close")).click();
        } catch (Exception e) {
            e.printStackTrace();
            driver.close();
        }
    }

    public void scrollThePage() {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            String jsSelector = "document.querySelector(\"#rciFooterGroup-1-4 > a\")";
            String script = "return " + jsSelector;
            WebElement elementTillWhereThePageScrolls = (WebElement) jsExecutor.executeScript(script);
            jsExecutor.executeScript("arguments[0].scrollIntoView(" +
                    "{behaviour: 'smooth', block:'center', inline:'center'});", elementTillWhereThePageScrolls);
        } catch (Exception e) {
            e.printStackTrace();
            driver.close();
        }
    }

    public void checkHolidayCruisePresence() {
        By elementHolidayCruise = By.xpath("//*[@id=\"rciFooterGroup-1-4\"]/a");
        List<WebElement> temp = driver.findElements(elementHolidayCruise);
        if (!temp.isEmpty()) {
            System.out.println("Element is Present");
            driver.findElement(elementHolidayCruise).click();
        } else {
            System.out.println("Element is Not Present");
        }
    }

    public void checkSearchOptionPresence() {
        By elementSearchButton = By.id("rciSearchHeaderIcon");
        if (!driver.findElements(elementSearchButton).isEmpty()) {
            System.out.println("Search Option Element is Present");
            driver.findElement(elementSearchButton).click();
        } else {
            System.out.println("Search Option Element is not Present");
        }
    }

    public void enterSearchText(String searchText) {
        WebDriverWait searchWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        searchWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rciHeader\"]" +
                "/div/div/div[6]/div/div/div/div[1]/div")));
        driver.findElement(By.xpath("//*[@id=\"rciHeader\"]/div/div/div[6]" +
                "/div/div/div/div[1]/div/input")).click();
        driver.findElement(By.xpath("//*[@id=\"rciHeader\"]/div/div/div[6]" +
                "/div/div/div/div[1]/div/input")).sendKeys(searchText, Keys.ENTER);
    }

    public void displaySearchResults() {

        // This Java code uses a regular expression to find and capture numbers with optional commas.
        // The regular expression `\\b(\\d{1,3}(,\\d{3})*)(\\.\\d+)?\\b` breaks down as follows:

        // - `\b`: Word boundary to ensure the match is a whole number.
        // - `(\\d{1,3}(,\\d{3})*)`: Matches one to three digits, optionally
        // followed by groups of three digits separated by commas (for thousands).
        // - `(\\.\\d+)?`: Optionally matches a decimal part with one or more digits.
        // - `\b`: Another word boundary.
        // The code then extracts the matched number, removes commas, converts it to an integer, and prints the result.
        // This approach is flexible and can handle various formats of
        // numbers with commas and decimals. Adjust the regular expression according to your specific needs.
        String searchResult = driver.findElement(By.xpath("//*[@id='siteSearchApp']/" +
                "div[1]/div/div[2]/div")).getText();
        Pattern pattern = Pattern.compile("\\b(\\d{1,3}(,\\d{3})*)(\\.\\d+)?\\b");
        Matcher matcher = pattern.matcher(searchResult);
        int searchResultCount = 0;
        if(matcher.find()){
            String extractedNumber = matcher.group(1);
            extractedNumber = extractedNumber.replaceAll(",","");
            searchResultCount = Integer.parseInt(extractedNumber);
            System.out.println("Total no of Search Result: " + searchResultCount);
        }
        if (searchResultCount < 300000) {
            System.out.println("Number of search results displayed are less then 300000");
        } else {
            System.out.println("Number of search results displayed are more then 300000");
        }

    }

    public void cruisesDate(String year) {
        try {
            // Clicking on the find the cruise button/link.
            driver.findElement(By.xpath("//*[@id='rciHeaderMenuItem-1']")).click();
            // Clicking on the cruise Date filter button
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.xpath("//*[@id='filters-content']/button[1]")).click();

            List<WebElement> sizeOfYearsList = driver.findElements(By.xpath("//*[@id='dates-filter-wrap']/" +
                    "section/div[2]/div[2]/div/div/div"));
            int sizeOfYears = sizeOfYearsList.size();
            String month = "";
            for (int y = 1; y <= sizeOfYears; y++) {
                By yearXpath = By.xpath("//*[@id='dates-filter-wrap']/section/" +
                        "div[2]/div[2]/div/div/div[" + y + "]/div[1]");
                if (year.equalsIgnoreCase(driver.findElement(yearXpath).getText())) {
                    month = "//*[@id='dates-filter-wrap']/section/" +
                            "div[2]/div[2]/div/div/div[" + y + "]/div[2]/div/button";
                    break;
                }
            }
            for (int i = 1; i <= 12; i++) {
                WebElement monthButton = driver.findElement(By.xpath(month + "[" + i + "]"));
                if (monthButton.isEnabled()) {
                    monthButton.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            driver.close();
        }

    }

    public void departurePort(String continent) {
        try {
            // Clicking on the Departure Port Button.
            driver.findElement(By.xpath("//*[@id='filter-modal']" +
                    "/div/section[1]/div/div/button[2]")).click();
            List<WebElement> sizeOfContinentList = driver.findElements(By.xpath("//*[@id='filter" +
                    "-modal']/div/section[2]/div/div[2]/div/div[@class='MuiTypography-root " +
                    "MuiTypography-title5 components__SectionTitleContent-sc-zsi07w-9 " +
                    "jGuvEA sectionTitle css-17fghhl']"));
            int sizeOfContinent = sizeOfContinentList.size();
            String regions = "";
            for (int i = 1; i <= sizeOfContinent; i++) {
                By continentXpath = By.xpath("(//*[@id='filter-modal']/div/section[2]/div/child::div[2]/div/div[1])" + "[" + i + "]");
                if (continent.equalsIgnoreCase(driver.findElement(continentXpath).getText())) {
                    // region div = //*[@id='filter-modal']/div/section[2]/div/div[2]/div[i]/div[2]/div
                    regions = "//*[@id='filter-modal']/div/section[2]/div/div[2]/div[" + i + "]/div[2]/div";
                    // System.out.println("Done");
                    break;
                }
            }
            List<WebElement> regionSize = driver.findElements(By.xpath(regions));
            for (int i = 1; i <= regionSize.size(); i++) {
                WebElement regionButton = driver.findElement(By.xpath(regions + "[" + i + "]"));
                if (regionButton.isEnabled()) {
                    regionButton.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            driver.close();
        }
    }

    public void destinations() {
        try {
            // Clicking on the Destination Button.
            driver.findElement(By.xpath("//*[@id='filter-modal']" +
                    "/div/section[1]/div/div/button[3]")).click();
            List<WebElement> sizeOfDestinationList = driver.findElements(By.xpath("//*[@id='filter-modal']" +
                    "/div/section/div[2]/div[3]/div"));
            String destinations = "//*[@id='filter-modal']/div/section/div[2]/div[3]/div";
            for (int i = 1; i <= sizeOfDestinationList.size(); i++) {
                WebElement destinationButton = driver.findElement(By.xpath(destinations + "[" + i + "]"));
                if (destinationButton.isEnabled()) {
                    destinationButton = driver.findElement(By.xpath(destinations + "/button" + "[" + i + "]"));
                    destinationButton.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            driver.close();
        }
    }

    public void numberOfNights() {
        try {
            // Click on the Number of Nights Button
            driver.findElement(By.xpath("//*[@id='filter-modal']" +
                    "/div/section[1]/div/div/button[4]")).click();
            List<WebElement> sizeOfNumberOfNightsList = driver.findElements(By.xpath("//*[@id='filter" +
                    "-modal']/div/section[2]/div[2]/section/section/div[2]/div"));
            String nights = "//*[@id='filter-modal']/div/section[2]/div[2]/section/section/div[2]/div";
            for (int i = 1; i <= sizeOfNumberOfNightsList.size(); i++) {
                WebElement nightSelectorButton = driver.findElement(By.xpath(nights + "[" + i + "]"));
                if (nightSelectorButton.isEnabled()) {
                    nightSelectorButton.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            driver.close();
        }
    }

    public void ships() {
        try {
            // Click on the Ships Button
            driver.findElement(By.xpath("//*[@id='filter-modal']" +
                    "/div/section[1]/div/div/button[5]")).click();
            List<WebElement> sizeOfShipsList = driver.findElements(By.xpath("//*[@id='filter" +
                    "-modal']/div/section[2]/div[2]/div[2]/div"));
            String ship = "//*[@id='filter-modal']/div/section[2]/div[2]/div[2]/div";
            for (int i = 1; i <= sizeOfShipsList.size(); i++) {
                WebElement shipSelectorButton = driver.findElement(By.xpath(ship + "[" + i + "]"));
                if (shipSelectorButton.isEnabled()) {
                    shipSelectorButton.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            driver.close();
        }
    }

    public void showResult() {
        // Clicking the result button;
        driver.findElement(By.xpath("//*[@id='filter-modal']/section/button")).click();
    }

    public void filterCruises(String year, String continent) {
        cruisesDate(year);
        departurePort(continent);
        destinations();
        numberOfNights();
        ships();
        showResult();
    }

    public void takeResultScreenshot(String path) throws Exception {
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File(path));
    }

    public static void main(String[] args) throws Exception {
        CruiseTestMain ctm = new CruiseTestMain();
        ctm.createDriver();
        ctm.handleSignupPopup();
        ctm.scrollThePage();
        ctm.checkHolidayCruisePresence();
        ctm.checkSearchOptionPresence();
        String searchText = "Rhapsody of the Seas";
        ctm.enterSearchText(searchText);
        ctm.displaySearchResults();
        ctm.filterCruises("2024", "North America");
        ctm.takeResultScreenshot("C:\\Users\\shubh\\IdeaProjects\\RoyalCruises\\Output");
        Thread.sleep(3000);
        driver.quit();
    }
}
