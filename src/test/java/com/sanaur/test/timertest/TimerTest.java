package com.sanaur.test.timertest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources/Features", tags = {"@one"}, format = {"html:target/cucumber-report/AppAutomation", "json:target/cucumber.json"})
public class TimerTest {
}