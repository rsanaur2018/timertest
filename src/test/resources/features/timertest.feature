Feature: Test eggtimer page

  @one
  Scenario Outline: Verify Eggmeter home page is loaded and user able to start the counter
    Given Eggmeter timer site is opened and page is loaded
    When User enters <CountDownTimeInSeconds> and click Go
    Then Countdown timer should start
    And Remaining time decreases in one-sec increments
    And Timer expired popup is displayed
  Examples:
  |CountDownTimeInSeconds|
  |25|
  |65|