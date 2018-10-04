package com.sanaur.test.timertest.stepdefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sanaur.test.timertest.page.TimerTestPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TimerTestStepDefinition {

	private TimerTestPage timerTestPage;

	private WebDriver driver;

	private int countDownTimeInSeconds;

	@Before
	public void setUp(Scenario scenario) throws Exception {

		System.setProperty("webdriver.gecko.driver", "c:\\geckodriver\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		timerTestPage = new TimerTestPage(driver);

	}

	@After
	public void closethebrowser() {
		driver.close();
		driver.quit();
	}

	@Given("^Eggmeter timer site is opened and page is loaded$")
	public void goToHomePage() {
		timerTestPage.openTimerURL();
		timerTestPage.waitForTextBoxAndGoButton();
	}

	@When("^User enters ([^\"]*) and click Go$")
	public void enterTimeAndClickGoButton(String countDownTimeInSeconds) {
		timerTestPage.enterTimerValue(countDownTimeInSeconds);
		this.countDownTimeInSeconds = Integer.parseInt(countDownTimeInSeconds);
		timerTestPage.clickGoButton();
	}

	@Then("^Countdown timer should start$")
	public void countdownTimerShouldStart() {
		timerTestPage.checkTimerStarted();
	}

	@And("^Remaining time decreases in one-sec increments$")
	public void remainingTimeDecreasesInOneSecIncrements() {
		timerTestPage.checkCounterDecrements(countDownTimeInSeconds);
	}

	@And("^Timer expired popup is displayed$")
	public void checkTimerExpiredAlertDisplayed() {
		timerTestPage.checkTimerExpiredAlertDisplayed();
	}

}
