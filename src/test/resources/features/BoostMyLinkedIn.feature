@LinkedInLogin
Feature: Boost My LinkedIn Profile
  I want to use this template for boost Linked in profiles

  Background:
    Given the user logs into LinkedIn.com

 @Linkedin
  Scenario: updating naukri profile with keywords, job application and resume
     When the user search for SDET people
     And the user send connect to random people
     And the user like random posts
     Then the user logged out of LinkedIn.com


