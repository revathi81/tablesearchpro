package org.task.TableSearchpro;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class addtabletocart {

	public static void main(String[] args) throws InterruptedException {
		
        // Open Chrome Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		// Navigate to the website
		driver.get("https://www.webstaurantstore.com/");

		// Click in Search text field
		driver.findElement(By.xpath("(.//input[@name='searchval'])[1]")).click();

		// Enter stainless work table in Search Field
		driver.findElement(By.xpath("(.//input[@name='searchval'])[1]")).sendKeys("stainless work table");
		Thread.sleep(2000);

		// Click on Search Button
		driver.findElement(By.xpath("(.//button[@type='submit'])[1]")).click();

		Thread.sleep(2000);

		List<WebElement> searchlist = driver.findElements(By.xpath(".//div[@id='categoriesBox']//div/a"));
		
		List<String> TableLinknames = new ArrayList<String>();
		List<String> NoTablekeyWord = new ArrayList<String>();
		int counter = 0;
		for (WebElement searcheditem : searchlist) {
			counter++;
			String tagname = searcheditem.getText().toString().trim();

			if (tagname.contains("Table")) {
				// System.out.println("Link Name has Table key word=> " + tagname);
				TableLinknames.add(tagname);

			} else {
				// System.out.println("Link Name doesn't have Table key word=> " + tagname);
				NoTablekeyWord.add(tagname);
			}
			if (counter == searchlist.size()) {
				// Click on Last Result Link
				searcheditem.click();
			}
		}
		Thread.sleep(2000);

		// Click on Add to Cart
		driver.findElement(By.xpath("(.//input[@type='submit' and @value='Add to Cart'])[1]")).click();
		Thread.sleep(3000);

		// Click on view cart Button
		driver.findElement(By.xpath(".//div/a[@class='btn btn-small btn-primary']")).click();
		/*
		 * ########### If above line doesn't work use below line###### // Click on Cart
		 * to Open driver.findElement(By.
		 * xpath(".//a[@aria-label='Your cart has 1 items. View your cart.']")).click();
		 * 
		 */
		Thread.sleep(2000);

		// Empty Cart Click on button
		driver.findElement(By.xpath(".//div[@data-hypernova-key='EmptyCart']/child::button[@type='button']")).click();

		Thread.sleep(2000);
		
		// Click on Confirm Empty Cart Button
		driver.findElement(By.xpath("(.//div/footer/button[@type='button'])[1]")).click();
		
		Thread.sleep(3000);
		
		String EmptyCartMessage = driver.findElement(By.xpath("(.//div[@class='empty-cart__text']/p)[1]")).getText()
				.toString();
		
		//Validate if Cart is Empty else Fail the Test
		Assert.assertEquals(EmptyCartMessage,"Your cart is empty.", "Cart is not Empty");

		Thread.sleep(2000);
        // Close the browser
		driver.quit();

		System.out.println("==========================Links has No Table Keyword=========================");
		for (String link : NoTablekeyWord) {

			System.out.println(link);
		}

		// List of Links has Table Key word
		System.out.println("==========================Links has Table Keyword===========================");
		for (String link : TableLinknames) {

			System.out.println(link);
		}

	}
}