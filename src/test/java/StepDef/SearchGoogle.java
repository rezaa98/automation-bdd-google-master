package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SearchGoogle {
    WebDriver driver;
    @Given("I Open browser")
    public void iOpenBrowser() {
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        System.setProperty("webdriver.chrome.driver", dir+"/driver/chromedriver");
        driver = new ChromeDriver();
    }

    @And("Open website Google")
    public void openWebsiteGoogle() throws InterruptedException {
        driver.get("https://www.google.co.id/");
        Thread.sleep(1000);
        //thread untuk nunggu brp banyak waktu loadnya
    }

    @And("Located on google website")
    public void locatedOnGoogleWebsite() {
        driver.findElement(By.name("btnK")).isDisplayed();
    }

    @When("I search {string}")
    public void iSearch(String searchValue) {
        driver.findElement(By.name("q")).sendKeys(searchValue);
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @Then("Showing result related with {string}")
    public void showingResultRelatedWith(String result) {
        driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/in/rezayusufmaulana/']")).isDisplayed();
        String urlLinkedinReza = driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/in/rezayusufmaulana/']")).getText();
        System.out.println(urlLinkedinReza);
        driver.close();
        driver.quit();
    }
}
