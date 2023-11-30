//package RoyalCruisesTesting;
///*
//* This is a file just for testing the main functionalities
//* of the main code. So that we don't have to run the whole
//* code again and again. Just the Specific part of the code.
//* */
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.List;
//
//public class test {
//    public static void main(String[] args){
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.royalcaribbean.com/cruises");
//        driver.manage().window().maximize();
//        try {
//            // Clicking on the find the cruise button/link.
//            driver.findElement(By.xpath("//*[@id='filters-content']/button[1]")).click();
//            // main code start
//            driver.findElement(By.xpath("//*[@id='filter-modal']" +
//                    "/div/section[1]/div/div/button[3]")).click();
//            List<WebElement> sizeOfDestinationList = driver.findElements(By.xpath("//*[@id='filter-modal']/div/section[2]/div[2]/div[3]/div"));
//            String destinations = "//*[@id='filter-modal']/div/section[2]/div[2]/div[3]/div";
//            for (int i = 1; i <= sizeOfDestinationList.size(); i++) {
//                WebElement destinationButton = driver.findElement(By.xpath(destinations + "[" + i + "]"));
//                if (destinationButton.isEnabled()) {
//                    destinationButton.click();
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            driver.quit();
//        }
//    }
//}
//
//// 1
//
////driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
////        driver.findElement(By.xpath("//*[@id='filters-content']/button[1]")).click();
////        List<WebElement> sizeOfYearsList = driver.findElements(By.xpath("//*[@id='dates-filter-wrap']/" +
////        "section/div[2]/div[2]/div/div/div"));
////        int sizeOfYears = sizeOfYearsList.size();
////        String year = "2024";
////        String month = "";
////        for (int y = 1; y <= sizeOfYears; y++) {
////        By yearXpath = By.xpath("//*[@id='dates-filter-wrap']/section/" +
////        "div[2]/div[2]/div/div/div[" + y + "]/div[1]");
////        if (year.equalsIgnoreCase(driver.findElement(yearXpath).getText())) {
////        month = "//*[@id='dates-filter-wrap']/section/" +
////        "div[2]/div[2]/div/div/div[" + y + "]/div[2]/div/button";
////        break;
////        }
////        }
////        for (int i = 1; i <= 12; i++){
////        WebElement monthButton = driver.findElement(By.xpath(month + "[" + i + "]"));
////        if (monthButton.isEnabled()){
////        monthButton.click();
////        break;
////        }
////        }
//
//
//
//// 2
//
////
////driver.findElement(By.xpath("//*[@id='filter-modal']" +
////        "/div/section[1]/div/div/button[2]")).click();
////        // Getting the continents list.
////        List<WebElement> sizeOfContinentList = driver.findElements(By.xpath("//*[@id='filter" +
////        "-modal']/div/section[2]/div/div[2]/div/div[@class='MuiTypography-root " +
////        "MuiTypography-title5 components__SectionTitleContent-sc-zsi07w-9 " +
////        "jGuvEA sectionTitle css-17fghhl']"));
////        int sizeOfContinent = sizeOfContinentList.size();
////        String regions = "";
////        for (int i = 1; i <= sizeOfContinent; i++){
////        By continentXpath = By.xpath("(//*[@id='filter-modal']/div/section[2]/div/child::div[2]/div/div[1])" + "[" + i + "]");
////        if (continent.equalsIgnoreCase(driver.findElement(continentXpath).getText())) {
////        // region div = //*[@id='filter-modal']/div/section[2]/div/div[2]/div[i]/div[2]/div
////        regions = "//*[@id='filter-modal']/div/section[2]/div/div[2]/div[" + i + "]/div[2]/div";
////        // System.out.println("Done");
////        break;
////        }
////        }
////        List<WebElement> regionSize = driver.findElements(By.xpath(regions));
////        for (int i = 1; i <= regionSize.size(); i++){
////        WebElement regionButton = driver.findElement(By.xpath(regions + "[" + i + "]"));
////        if (regionButton.isEnabled()){
////        regionButton.click();
////        break;
////        }
////        }