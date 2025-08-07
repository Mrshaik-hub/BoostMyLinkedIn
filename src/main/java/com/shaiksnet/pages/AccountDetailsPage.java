package com.shaiksnet.pages;

import com.shaiksnet.utility.DataManager;
import com.shaiksnet.utility.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.*;

public class AccountDetailsPage {
    private final WebDriver driver;
    private final Logger logger = (Logger) LogManager.getLogger(this.getClass());

    public AccountDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public static String getFixedLengthSkillsString(int targetLength) {
        List<String> allSkills = Arrays.asList(
                "Java", "Core Java", "Selenium WebDriver", "TestNG", "JUnit", "Cucumber", "REST Assured", "Postman",
                "Karate", "API Testing", "Automation Testing", "Manual Testing", "SDET", "Agile", "Scrum", "POM",
                "BDD", "TDD", "Maven", "Gradle", "Jenkins", "Git", "GitHub", "Bitbucket", "CI/CD", "DevOps", "Docker",
                "Kubernetes", "Linux", "MySQL", "MongoDB", "Oracle", "Swagger", "JSON", "XML", "YAML", "STLC", "SDLC",
                "JIRA", "Confluence", "XPath", "Playwright", "Cypress", "Appium", "BrowserStack", "Sauce Labs",
                "Bug Tracking", "Test Strategy", "Test Planning", "Smoke Testing", "Regression Testing",
                "Functional Testing", "Cross Browser Testing", "Test Reporting", "Version Control"
//                //-- DSA & Problem Solving Keywords --//
//                "Data Structures", "Algorithms", "Problem Solving", "Time Complexity", "Space Complexity",
//                "Arrays", "Strings", "Linked Lists", "Trees", "Graphs", "Recursion", "Backtracking",
//                "Dynamic Programming", "Greedy Algorithms", "HashMap", "Stack", "Queue", "Heap", "Binary Search",
//                "BFS", "DFS", "Two Pointers", "Sliding Window", "Bit Manipulation", "Sorting", "Searching",
//                "LeetCode", "HackerRank", "Codeforces", "Algorithm Optimization", "Coding Challenges"
        );

        Collections.shuffle(allSkills);

        StringBuilder result = new StringBuilder();
        for (String skill : allSkills) {
            if (result.length() + skill.length() + 2 > targetLength) break; // +2 for ", "
            if (result.length() > 0) result.append(", ");
            result.append(skill);
        }

        return result.toString();
    }

    public static String get245Chars() {
        String skills245 = getFixedLengthSkillsString(245);
        return skills245;
    }

    public static String getRandomSreachKey() {
        List<String> allSkills = Arrays.asList(
                "Java Developer", "Core Java Expert", "Selenium Automation Engineer", "TestNG Automation",
                "JUnit Framework", "Cucumber BDD", "REST Assured API Automation", "Postman Collections",
                "Karate API Framework", "API Automation Tester", "Manual and Automation Testing",
                "QA SDET", "Agile QA", "Scrum Team QA", "Page Object Model",
                "BDD Framework", "TDD Approach", "Maven Project", "Gradle Automation",
                "Jenkins CI/CD", "Git Version Control", "GitHub Actions", "Bitbucket Pipelines",
                "CI/CD Automation", "DevOps Testing", "Docker with Selenium",
                "Kubernetes QA Integration", "Linux for Testers", "MySQL for QA",
                "MongoDB Queries", "Oracle SQL Testing", "Swagger API Docs",
                "JSON Parsing", "XML Validation", "YAML Config Testing",
                "STLC Process", "SDLC QA Life Cycle", "JIRA Bug Tracking",
                "Confluence Documentation", "XPath Selectors", "Playwright Automation",
                "Cypress E2E Testing", "Appium Mobile Testing", "BrowserStack Integration",
                "Sauce Labs Execution", "Bug Tracking Tools", "QA Test Strategy",
                "Test Planning Skills", "Smoke & Regression Testing", "Functional Test Cases",
                "Cross-Browser Automation", "Test Reporting Tools", "Version Control Systems"
        );

        Random random = new Random();
        return allSkills.get(random.nextInt(allSkills.size()));
    }

    public static String SreachKey() {
        String randomSkill = getRandomSreachKey();
        return randomSkill;
    }



    public  void theUserUpdateNaukriKeywords() {
        try {
              logger.info("In theUserUpdateNaukriKeywords started");
              WebElement viewProfile = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"viewProfile")));
              Thread.sleep(2000); // Wait for the element to be visible
              viewProfile.click();
           WebElement naukriSkill =  driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"updateHeadLine")));
           naukriSkill.click();
           WebElement naukriSkillInput = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"skillInput")));
           naukriSkillInput.clear();
           String  skills = get245Chars();
           naukriSkillInput.sendKeys(skills);
              WebElement naukriSkillAddButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"naukriSkillSaveBtn")));
            naukriSkillAddButton.click();

            //key skills update
            WebElement keySkill =  driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"keySkills")));
            Thread.sleep(2000);
            keySkill.click();
            Thread.sleep(2000);
            WebElement keySkillInput = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"keySkillInput")));
            //removeskills
            Thread.sleep(3000); // Wait for skills to load

            int skillCount = driver.findElements(By.xpath(Util.getXpath(getClass().getSimpleName(), "removeSkills"))).size();

            for (int i = 0; i < skillCount; i++) {
                WebElement removeButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(), "removeSkills")));
                removeButton.click();
                Thread.sleep(300); // Allow DOM to refresh after each removal
            }
            keySkillInput.sendKeys(skills);
            Thread.sleep(2000);
            WebElement keySkillAddButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"keySkillSaveBtn")));
            keySkillAddButton.click();



            logger.info("In theUserUpdateNaukriKeywords completed");

        } catch (Exception e) {
            logger.error("Failed to update Naukri keywords", e);
        }
    }

    public void userUploadsTheResumeToNaukriProfile() {
        try{
            logger.info("In userUploadsTheResumeToNaukriProfile Started");
            String path = Util.getProperty("resumePath");

            // Get all files in the folder
            File folder = new File(System.getProperty("user.dir")+path);
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf") || name.toLowerCase().endsWith(".doc") || name.toLowerCase().endsWith(".docx"));

            if (files == null || files.length == 0) {
                logger.error("No resume files found in folder: " + path);
                return;
            }

            // Pick a random resume
            File randomResume = files[new Random().nextInt(files.length)];
            logger.info("Selected Resume: " + randomResume.getAbsolutePath());
            DataManager.setString("randomResume", randomResume.getAbsolutePath().toString());



            WebElement uploadResumeButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"updateResumeBTn")));

            // 🔓 Make it visible
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].setAttribute('style', 'display:block !important; visibility:visible !important; opacity:1 !important; position:relative; z-index:9999;');",
                    uploadResumeButton
            );

            // Wait for the element to be visible

            System.out.println("Is file input displayed? " + uploadResumeButton.isDisplayed());
            System.out.println("Is file input enabled? " + uploadResumeButton.isEnabled());


            uploadResumeButton.sendKeys(randomResume.getAbsolutePath());
            Thread.sleep(3000);




        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserShouldSeeTheUpdatedNaukriProfile() {
        try{

            String resumeNameUploaded = DataManager.getString("randomResume");
            resumeNameUploaded= resumeNameUploaded.replaceAll(".*\\((\\d+)\\)\\.pdf$", "$1");

            WebElement resumeTitle = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"resumeTitle")));

            String resumeNameInNaukri =resumeTitle.getText().trim();
            resumeNameInNaukri= resumeNameInNaukri.replaceAll(".*?(\\d+)\\.pdf$", "$1");

            System.out.println(resumeNameInNaukri+resumeNameUploaded);

            if (resumeNameUploaded.equals(resumeNameInNaukri)) {
                logger.info("Resume uploaded successfully: " + resumeNameInNaukri);
            } else {
                Assert.assertTrue(" Resume name last character mismatch! Expected: "
                        + resumeNameInNaukri + ", but got: "
                        + resumeNameUploaded, false);
            }

        }catch (Exception e){
            logger.error("Failed to verify updated Naukri profile", e);

        }
    }

    public void theuserapplyforjobsinnaukri() {
        try{
            logger.info("In theuserapplyforjobsinnaukri started");
            Util.waitForPageToLoad(driver);

            Thread.sleep(2000); // Wait for the page to load

            WebElement jobsTab = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"jobs")));
            Util.waitUntilElementIsClickable(driver, jobsTab);
            jobsTab.click();

            Thread.sleep(2000); // Wait for the jobs page to load

            List<WebElement> jobSelectBoxList = driver.findElements(By.xpath(Util.getXpath(getClass().getSimpleName(),"jobCheckboxList")));
            int count=0;
             JavascriptExecutor js = (JavascriptExecutor) driver;
            List<WebElement> shuffled = new ArrayList<>(jobSelectBoxList);
            Collections.shuffle(shuffled);

            for (WebElement job : shuffled) {
                if (count == 5) break;

                try {
                    if (job.isDisplayed() && job.isEnabled()) {
                        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", job);
                        try {
                            job.click(); // Try normal click first
                        } catch (ElementClickInterceptedException e) {
                            js.executeScript("arguments[0].click();", job); // Fallback to JS click
                        }
                        Thread.sleep(1000);
                        count++;
                    }
                } catch (Exception e) {
                    System.out.println("Failed to click a checkbox: " + e.getMessage());
                    // Continue to next checkbox
                }

            }

            WebElement ApplyButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"applyBtn")));
            ApplyButton.click();

            Thread.sleep(2000); // Wait for the apply modal to appear

            WebElement chatExitButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"exitBtn")));
            if (chatExitButton.isDisplayed()) {
                chatExitButton.click();
            }


//            WebElement homeButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"homeBtn")));
            Thread.sleep(1000);
//            homeButton.click();
            driver.navigate().back();
            Thread.sleep(1000); // Wait for the home page to load
            driver.navigate().back();
             // Wait for the home page to load

            logger.info("In theuserapplyforjobsinnaukri completed");


        }catch(Exception e){
            logger.error("Failed to apply for jobs in Naukri", e);
            Assert.fail("Failed to apply for jobs in Naukri: " + e.getMessage());
        }





    }

    public void userMakeAVisitInGitAndLinkedIn() {
        try {
            logger.info("In userMakeAVisitInGitAndLinkedIn started");

            List<String> urls = Arrays.asList(
                    "https://github.com/Mrshaik-hub",
                    "linkedin", // trigger element click when this is found
                    "https://medium.com/@shaikmahaboobsubhani00"
            );

            int repeatCount = 40;
            Random random = new Random();

            List<Dimension> screenSizes = Arrays.asList(
                    new Dimension(1280, 800), // Desktop
                    new Dimension(375, 667),  // Mobile
                    new Dimension(768, 1024)  // Tablet
            );

            for (int i = 0; i < repeatCount; i++) {
                for (String url : urls) {
                    try {
                        Dimension screenSize = screenSizes.get(random.nextInt(screenSizes.size()));
                        driver.manage().window().setSize(screenSize);

                        if (url.contains("linkedin")) {
                            // ✅ Just find and click the LinkedIn button
                            WebElement linkedInBtn = driver.findElement(By.xpath(
                                    Util.getXpath(getClass().getSimpleName(), "linkedinBtn")
                            ));

                            ((JavascriptExecutor) driver).executeScript(
                                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                                    linkedInBtn
                            );
                            Thread.sleep(1000);
                            linkedInBtn.click();
                            logger.info("Clicked on LinkedIn button");
                        } else {
                            // Normal visit and scroll
                            driver.get(url);
                            logger.info("Visited: " + url);

                            JavascriptExecutor js = (JavascriptExecutor) driver;
                            int scrollSteps = 3;
                            for (int j = 0; j < scrollSteps; j++) {
                                int y = (j + 1) * (int) (driver.manage().window().getSize().getHeight() * 0.5);
                                js.executeScript("window.scrollTo(0, arguments[0]);", y);
                                Thread.sleep(1000 + random.nextInt(500));
                            }

                            // Optionally click a random visible link
//                            List<WebElement> links = driver.findElements(By.xpath("//a[string-length(@href) > 5]"));
//                            if (!links.isEmpty()) {
//                                WebElement randomLink = links.get(random.nextInt(links.size()));
//                                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", randomLink);
//                                Thread.sleep(500);
//                                try {
//                                    randomLink.click();
//                                    driver.navigate().back();
//                                } catch (Exception ignored) {}
//                            }
                        }

                        // Wait 5–10 seconds
                        Thread.sleep(5000 + random.nextInt(5000));

                    } catch (Exception e) {
                        logger.warn("Failed during visit/click for: " + url + " | " + e.getMessage());
                    }
                }
            }

            if (driver != null) {
                driver.quit();
            }

            logger.info("GitHub, LinkedIn & Medium visits completed");

        } catch (Exception e) {
            logger.error("Failed in SEO visit method", e);
            Assert.fail("SEO automation failed: " + e.getMessage());
        }
    }


    public void theUserSearchForSDETPeople() {
        try{
            logger.info("In theUserSearchForSDETPeople started");
            String searchKeyword = SreachKey();
            String searchKeyword2 = SreachKey();
            String searchKeyword3 = SreachKey();
            driver.get("https://www.linkedin.com/search/results/people/?keywords="+searchKeyword+"%20"+searchKeyword2+"%20"+searchKeyword3);
            Thread.sleep(5000);

            logger.info("theUserSearchForSDETPeople completed");

        }catch (Exception e) {
            logger.error("Failed to search for SDET people", e);
            Assert.fail("Failed to search for SDET people: " + e.getMessage());
        }
    }

    public void theUserSendConnectToRandomPeople() {
        try{
            logger.info("In theUserSendConnectToRandomPeople started");
            int connectionCount = 0;
            JavascriptExecutor js = (JavascriptExecutor) driver;


            while (connectionCount < 3) {
                List<WebElement> connectButtons = driver.findElements(By.xpath(Util.getXpath(getClass().getSimpleName(),"connectBtn")));

                Collections.shuffle(connectButtons); // Shuffle to randomize the order

                for (WebElement btn : connectButtons) {
                    try {
                        if (connectionCount >= 3) break;

                        //js.executeScript("arguments[0].scrollIntoView(true);", btn);
                        Thread.sleep(2000);

                        // Use this:
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                        Thread.sleep(2000);

                        // If popup appears
                        WebElement sendNow = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"sendBtn")));
                        sendNow.click();
                        connectionCount++;
                        System.out.println("Connected: " + connectionCount);

                        Thread.sleep(new Random().nextInt(2000) + 3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                // Scroll to load more profiles
                js.executeScript("window.scrollBy(0, 1000)");
                Thread.sleep(4000);
            }
            logger.info("In theUserSendConnectToRandomPeople completed, sent " + connectionCount + " connect requests");

        }catch (Exception e) {
            logger.error("Failed to send connect requests to random people", e);
            Assert.fail("Failed to send connect requests to random people: " + e.getMessage());
        }
    }

    public void theUserLikeRandomPosts() {
        try{
            logger.info("In theUserLikeRandomPosts started");

            WebElement homeButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"linkedInhomeBtn")));
            homeButton.click();
            Thread.sleep(2000);
            // Wait for the home page to load
            List<WebElement> likeBtn = driver.findElements(By.xpath(Util.getXpath(getClass().getSimpleName(),"likeBtn")));
            Collections.shuffle(likeBtn); // Shuffle to randomize the order
            int likeCount = 0;
            for (WebElement btn : likeBtn) {
                try {
                    if (btn.isDisplayed() && btn.isEnabled())  {
                        if (likeCount >= 3) break; // Limit to 5 likes
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
                        Thread.sleep(1000);
                        btn.click(); // Click the like button
                        likeCount++;
                        Thread.sleep(2000); // Wait before next action
                    }
                } catch (Exception e) {
                    logger.warn("Failed to like a post: " + e.getMessage());
                }
            }
         logger.info("In theUserLikeRandomPosts completed, liked " + likeCount + " posts");

        }catch (Exception e) {
            logger.error("Failed to like random posts", e);
            Assert.fail("Failed to like random posts: " + e.getMessage());
        }
    }
}
