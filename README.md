# automation-bdd-google

Hi:wave: Perkenalkan Nama Saya [Reza Yusuf Maulana](https://www.linkedin.com/in/rezayusufmaulana) :slightly_smiling_face:, biasa dipanggil Reza, Kalau mau cek linkedin bisa ke [Linkedin.](https://www.linkedin.com/in/rezayusufmaulana/) ini yaa

<p>Kali ini saya akan membagikan tutorial Test Automation menggunakan Selenium menggunakan bahasa pemrograman Java + BDD TOOLS Cucumber.</p>
<p>Semoga bermanfaat.</p>

**Tools yang dibutuhkan** :

- [Java 15](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- IntelliJ IDEA Plugin : [Cucumber for Java](https://plugins.jetbrains.com/plugin/7212-cucumber-for-java)
- IntelliJ IDEA Plugin : [Gherkin](https://plugins.jetbrains.com/plugin/9164-gherkin)
- [Chromedriver](https://chromedriver.chromium.org/downloads)
  or [Geckodriver](https://github.com/mozilla/geckodriver/releases)
- [Maven](https://maven.apache.org/download.cgi)

**Berikut adalah langkah2 nya :**

1. Buat Project pada IDE, disini saya menggunakan IntelliJ (https://www.jetbrains.com/idea/download/)

2. Install Plugin ini "Cucumber for Java" dan "Gherkin"

3. Buka pom.xml dan add dependency ini

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>automation-bdd-google</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>15</maven.compiler.source>
        <maven.compiler.target>15</maven.compiler.target>
    </properties>
    <dependencies>

        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>6.9.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/junit/junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.1</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>6.9.0</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.141.59</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/net.masterthought/cucumber-reporting -->
        <dependency>
            <groupId>net.masterthought</groupId>
            <artifactId>cucumber-reporting</artifactId>
            <version>5.4.0</version>
        </dependency>


    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.0</version>
                <configuration>
                    <testFailureIgnore>true</testFailureIgnore>
                </configuration>
            </plugin>
            <plugin>
                <groupId>net.masterthought</groupId>
                <artifactId>maven-cucumber-reporting</artifactId>
                <version>2.8.0</version>
                <executions>
                    <execution>
                        <id>execution</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <projectName>automation-bdd-google</projectName>
                            <outputDirectory>${project.build.directory}/cucumber-report-html</outputDirectory>
                            <cucumberOutput>${project.build.directory}/cucumber.json</cucumberOutput>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

4. Tambahkan Webdriver (disini saya menggunakan ChromeDriver dan bisa di download di "https://chromedriver.chromium.org/downloads") lalu buat Directory driver dan simpan ChromeDriver
   <img width="737" alt="Screen Shot 2021-10-23 at 14 50 28" src="https://user-images.githubusercontent.com/54229493/138548747-aa594413-2b0e-42a6-964c-306dfbcfa92e.png">

5. Buat Directory "StepDef" dan "TestRunner" pada src/test/java dan juga buat Directory "Features" pada src/test/resources
   <img width="465" alt="Screen Shot 2021-10-23 at 14 46 51" src="https://user-images.githubusercontent.com/54229493/138548839-de7dba43-6ac9-4d63-b69f-e8d53337da3c.png">

6. Buat Feature file "SearchGoogle.feature" pada directory Features

```gherkin
Feature: Search Google
  Scenario: I want to using feature search on google
    Given I Open browser
    And Open website Google
    And Located on google website
    When I search "Reza Yusuf Maulana"
    Then Showing result related with "Reza Yusuf Maulana"
```

7. Hover ke Gherkin scenarionya dan akan muncul action dan klik "More Action" lalu klik lagi "Create all step definitions" lalu buat nama class nya sesuai dengan nama Feature file dan klik "OK"

8. Lalu akan otomatis ke generate scenarionya seperti dibawah ini

9. Isi scenario di java code nya seperti dibawah ini ya

```java
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
```

Dan untuk cara mencari Elementnya bagaimana?
Klik kanan aja lalu klik inspect element dan arahkan pada elementnya

10. Hover pada feature file dan klik Run

11. Maka akan otomatis running test nya

12. Buat java class "TestRunner" pada Directory TestRunner

13. Lalu copy code ini

```java
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",
        glue= {"StepDef"},
        plugin ={"pretty","json:target/cucumber.json"})
```

14. Lalu open terminal pada IntelliJ dan ketikkan `mvn test` maka akan running test automation
15. Lalu ketikkan `mvn verify -DskipTests` untuk generate report test yang sudah kita jalankan sebelumnya

16. Buka Directory target dan open file ini pada chrome

17. Maka kita telah berhasil melakukan generate test menggunakan cucumber

Selamat Mencoba :)
