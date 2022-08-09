package pom.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pom.Base.Base;

public class BlogPage extends Base{
	
	public BlogPage(WebDriver driver) {
		super(driver);
	}

	By txt_postTitle = By.cssSelector(".posts-list .post-title");
	By btn_LoadMore = By.xpath("//div[contains(@class, 'load-more-btn-wrap')]/button");
	By txt_results = By.xpath("//div[contains(@class, 'results')]");
	By spinner_LoadMorePost = By.className("ajax-spinner");
	
	Boolean titleFound = false;
	
	public void openPostByTitle(String title) throws InterruptedException {
		while (getCurrentPostShown() <= getPostsTotal()) {
			List <WebElement> titles = findElements(txt_postTitle);
			clickPostTitle(titles, title);
			if(titleFound) break;
			scrollToElement(btn_LoadMore);
			click(btn_LoadMore);
			waitForElementVisible(spinner_LoadMorePost);
			waitForElementNotVisible(spinner_LoadMorePost);	
		}
		
	}
	
	public void clickPostTitle(List<WebElement> elements, String title) throws InterruptedException {
		System.out.println("Cantidad de titulos verificados: "+elements.size());
		for (int i = 0; i < elements.size() ; i++) {
			if (title.equals(getText(elements.get(i)))) {
				click(elements.get(i));
				titleFound = true;
				break;
			}
		}
	}
	
	public void printAllPost() throws InterruptedException {
		goBack();
		waitForTitleContains("Blog | Insights | Blankfactor");
		while (getCurrentPostShown() < getPostsTotal()) {
			scrollToElement(txt_results);
			if(isDisplayed(btn_LoadMore)) {
				click(btn_LoadMore);
				waitForElementVisible(spinner_LoadMorePost);
				waitForElementNotVisible(spinner_LoadMorePost);
			}			
		}
		List <WebElement> titles = findElements(txt_postTitle);
		for (int i = 0; i < titles.size() ; i++) {
			System.out.println("Post title: "+getText(titles.get(i))+" Post Link: "+titles.get(i).findElement(By.xpath("a")).getAttribute("href"));
		}
	}
	
	public int getPostsTotal() {
		String fullText = getText(txt_results);
		String[] fullTextSplited = new String[3];
		fullTextSplited = fullText.split(" ");
		String numerberOfPosts = fullTextSplited[2].replace("(", "").replace(")", "");
		return Integer.parseInt(numerberOfPosts);
	}
	
	public int getCurrentPostShown() {
		String fullText = getText(txt_results);
		String[] fullTextSplited = new String[3];
		fullTextSplited = fullText.split(" ");
		String numberPostShown = fullTextSplited[1].split("-")[1];
		return Integer.parseInt(numberPostShown);
	}
}
