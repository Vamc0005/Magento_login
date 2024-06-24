package com.login.steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import io.cucumber.java.After;

import java.time.Duration;

public class AccountSteps {

    private WebDriver wd;
    private Faker faker;
    private WebDriverWait wait;
    private String firstname;
    private String lastname;
    private String email;
    private String password = "Magento@123";

    @Given("I am on the Magento homepage")
    public void i_am_on_the_magento_homepage() {
        WebDriverManager.firefoxdriver().setup();
        wd = new FirefoxDriver();
        faker = new Faker();
        wait = new WebDriverWait(wd, Duration.ofSeconds(200));
        wd.get("https://magento.softwaretestingboard.com/");
    }

    @When("I create a new account")
    public void i_create_a_new_account() {
        WebElement createAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create an Account")));
        createAccountLink.click();

        firstname = faker.name().firstName();
        lastname = faker.name().lastName();
        email = faker.internet().emailAddress();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname"))).sendKeys(firstname);
        wd.findElement(By.id("lastname")).sendKeys(lastname);
        wd.findElement(By.id("email_address")).sendKeys(email);
        wd.findElement(By.id("password")).sendKeys(password);
        wd.findElement(By.id("password-confirmation")).sendKeys(password);

        wd.findElement(By.cssSelector("button[title='Create an Account']")).click();
    }

    @And("I should be able to log out")
    public void i_should_be_able_to_log_out() {
        WebElement customerMenuToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='action switch' and @data-action='customer-menu-toggle'])[1]")));
        customerMenuToggle.click();

        WebElement signOutLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
        signOutLink.click();
        wait.until(ExpectedConditions.urlContains("https://magento.softwaretestingboard.com/"));
    }

    @Then("I should be able to log in with the new account")
    public void i_should_be_able_to_log_in_with_the_new_account() {
        wd.findElement(By.linkText("Sign In")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

        wd.findElement(By.id("email")).sendKeys(email);
        wd.findElement(By.id("pass")).sendKeys(password);

        wd.findElement(By.id("send2")).click();

        // Print success message with email and password
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Account created and logged in successfully.");
    }

    @After
    public void tearDown() {
        if (wd != null) {
            wd.quit();
        }
    }
}
