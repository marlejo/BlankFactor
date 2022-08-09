package pom.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pom.Pages.BlogPage;
import pom.Pages.HomePage;
import pom.Pages.PostPage;

/**
 * 1. Navigate to “http://blankfactor.com”
 * 2. Open the “Blog” section
 * 3. Scroll down to - “Why Fintech in Latin America Is Having a Boom” and click the post by Sofia Gonzalez
 * 4. Make validation (that the script is on the correct page, by verifying the URL, and some of the text on the page)
 * 5. Subscribe to the newsletter using the subscribe form
 * 6. Go back to the Blog section and print a list of all post titles with related links.
*/
public class OpenBlogPage_Test {
	
	private WebDriver driver;
	HomePage homePage;
	BlogPage blogPage;
	PostPage postPage;

	@Before
	public void setUp() throws Exception {
		homePage = new HomePage(driver);		
		driver = homePage.chromeDriverConnection();
		homePage.visit("https://blankfactor.com/");
		driver.manage().window().maximize();
		blogPage = new BlogPage(driver);
		postPage = new PostPage(driver);
	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}

	@Test
	public void OpenBlogPage() throws InterruptedException {
		homePage.acceptCookies();
		homePage.openBlogLink();		
		blogPage.openPostByTitle("Why Fintech in Latin America Is Having a Boom");
		//blogPage.openPostByTitle("How fintech is powering the hospitality sector");
		assertTrue(postPage.isBioContentDisplayed());
		assertEquals("https://blankfactor.com/insights/blog/fintech-in-latin-america/", postPage.getUrl());
		//assertEquals("https://blankfactor.com/insights/blog/hospitality-sector/", postPage.getUrl());
		postPage.subscribeToNewsletter();
		blogPage.printAllPost();
	}
	

}
