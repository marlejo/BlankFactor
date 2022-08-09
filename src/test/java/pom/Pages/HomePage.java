package pom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pom.Base.Base;

public class HomePage extends Base{

	public HomePage(WebDriver driver) {
		super(driver);
	}

	By link_InsightsTitle = By.xpath("//*[@id='menu-item-4436']/a[1]/span[1]");
	By link_Blog = By.xpath("//*[@id=\"menu-item-4436\"]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/p[1]");
	By btn_acceptCookies = By.id("hs-eu-confirmation-button");
	
	public void openBlogLink() {		
		mouseOver(link_InsightsTitle);
		click(link_Blog);
		waitForTitleContains("Blog | Insights | Blankfactor");
	}
	
	public void acceptCookies() {
		if(isDisplayed(btn_acceptCookies)) {
			click(btn_acceptCookies);
		}
	}
}
