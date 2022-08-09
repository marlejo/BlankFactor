package pom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pom.Base.Base;

public class PostPage extends Base{

	public PostPage(WebDriver driver) {
		super(driver);
	}
	
	By txt_authorName = By.className("author-name");
	By txt_bioContent = By.cssSelector(".post-meta__bio.acf-text-content.text-small");
	By btn_subscribe = By.id("form-newsletter-blog-submit-btn");
	By input_subscribeEmail = By.name("Email");
	By txt_subscriptionMessage = By.cssSelector(".mc4wp-response");
	
	public boolean isAuthorNameDisplayed() {
		waitForElementVisible(txt_authorName);
		return isDisplayed(txt_authorName);
	}
	
	public boolean isBioContentDisplayed() throws InterruptedException {
		waitForElementVisible(txt_bioContent);
		return isDisplayed(txt_bioContent);
	}
	
	public void subscribeToNewsletter() throws InterruptedException {
		scrollToElement(btn_subscribe);
		waitForElementClickable(input_subscribeEmail);
		type("marlejo@yopmail.com", input_subscribeEmail);
		click(btn_subscribe);
		waitForElementVisible(txt_subscriptionMessage);
	}

}
