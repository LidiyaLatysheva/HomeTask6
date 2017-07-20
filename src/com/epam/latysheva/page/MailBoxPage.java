package com.epam.latysheva.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class MailBoxPage extends Page {
    protected static String saveTime;
    protected static int emailCount;
    protected static String receivedTime;
    protected List<String> emailTimeList;
    private static final String SENT_OK_URL = "sendmsgok";
    protected final static By EMAIL_IN_LIST = By.className("js-href b-datalist__item__link");

    public MailBoxPage(WebDriver driver) {
        super(driver);
    }

    public DraftsPage openDrafts() {
        driver.findElement(DRAFT_LINK).click();
        return new DraftsPage(driver);
    }

    public SentsPage openSents() {
        driver.findElement(SENT_LINK).click();
        return new SentsPage(driver);
    }

    public InboxPage openInbox() {
        driver.findElement(INBOX_LINK).click();
        return new InboxPage(driver);
    }

    public boolean isEmailSent() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains(SENT_OK_URL);
    }

    public HomePage logout() {
        driver.findElement(LOGOUT_LINK).click();
        return new HomePage(driver);
    }

    public MailBoxPage clickDelete() {
        driver.findElement(DELETE_BUTTON).click();
        return this;
    }

    public boolean isEmailDeleted() {
        try {
            waitForElementVisible(DELETE_EMAIL_CONFIRM_MESSAGE);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public List<String> getEmailTimes() {
        int size = driver.findElements(By.xpath(EMAIL_TIME)).size();
        for (int i = 0; i < size; i++) {
            emailTimeList.add(i, driver.findElements(By.xpath(EMAIL_TIME)).get(i).getText());
        }
        return emailTimeList;
    }
}
