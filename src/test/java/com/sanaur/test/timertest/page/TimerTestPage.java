package com.sanaur.test.timertest.page;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimerTestPage {

	public WebDriver driver;

	public TimerTestPage(WebDriver driver) {
		this.driver = driver;

	}

	public void openTimerURL() {
		driver.get("http://e.ggtimer.com/");
	}

	public void waitForTextBoxAndGoButton() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("start_a_timer"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("timergo"))));
	}

	public void enterTimerValue(String timeValue) {
		driver.findElement(By.id("start_a_timer")).clear();
		driver.findElement(By.id("start_a_timer")).sendKeys(timeValue);

	}

	public void clickGoButton() {
		driver.findElement(By.id("timergo")).click();
	}

	public void checkTimerStarted() {
		Assert.assertTrue(driver.findElement(By.id("progressText")).isDisplayed());
	}

	public void checkCounterDecrements(int secondsEntered) {

		// edge case time has stopped
		int oldSecondsValue = secondsEntered;
		int totalSecondsRemaining = secondsEntered;
		while (totalSecondsRemaining > 1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String timeText = driver.findElement(By.id("progressText")).getText();
			String[] splitTimeArray = timeText.split(" ");
			int splitTimeArrayLength = splitTimeArray.length;
			if (splitTimeArrayLength == 1) {
				totalSecondsRemaining = 0;
			} else if (splitTimeArrayLength == 2) {
				// only seconds
				if (splitTimeArray[1].contains("minute")) {
					totalSecondsRemaining = Integer.parseInt(splitTimeArray[0]) * 60;
				}else {
					totalSecondsRemaining = Integer.parseInt(splitTimeArray[0]);
				}
				
			} else if (splitTimeArrayLength == 4) {
				// minutes and seconds
				int remainingMinutes = Integer.parseInt(splitTimeArray[0]);
				totalSecondsRemaining = Integer.parseInt(splitTimeArray[2]);
				totalSecondsRemaining = remainingMinutes * 60 + totalSecondsRemaining;
			}
			Assert.assertTrue(totalSecondsRemaining < oldSecondsValue);
			oldSecondsValue = totalSecondsRemaining;
		}

		//checkTimerExpiredAlertDisplayed();
	}

	public void checkTimerExpiredAlertDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		Assert.assertTrue(alertText.contains("Time Expired!"));

	}

}
