package stepDefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class steps {
	WebDriver driver;
	
	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://magento.softwaretestingboard.com");
		driver.findElement(By.xpath("//div[contains(@class,'header')]//li[@class='authorization-link']/a[contains(text(),'Sign In')]")).click();
	    System.out.println("Successfully launched url");
	}

	@When("the user enters valid credentials \\(username: {string}, password: {string})")
	public void the_user_enters_valid_credentials_username_password(String emailId, String pwd) {
		driver.findElement(By.id("email")).sendKeys(emailId);
		driver.findElement(By.id("pass")).sendKeys(pwd);
		System.out.println("Successfully entered account details");
	}

	@When("the user clicks on the Login button")
	public void the_user_clicks_on_the_login_button() {
		driver.findElement(By.id("send2")).click();
		System.out.println("LogIn btn clicked");
	}

	@Then("the user should be redirected to the My Account page")
	public void the_user_should_be_redirected_to_the_my_account_page() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		String acName = driver.findElement(By.xpath("//div[@class='panel header']//li[contains(@class,'greet welcome')]")).getText();
	    System.out.println("AccountName = "+acName);
	    System.out.println("Account Page Validated");
	}

	@Then("the user should see a welcome message and logout")
	public void the_user_should_see_a_welcome_message_and_logout() {
		driver.findElement(By.xpath("//div[@class='panel header']//button[@type='button']")).click();
		List<WebElement> options1 = driver.findElements(By.xpath("//div[@aria-hidden='false']//ul[@class='header links']//li"));
		for(WebElement option:options1)
		{
			//System.out.println(option.getText());
			if(option.getText().contains("Sign"))
			{
				option.click();
				System.out.println("Option Selected ");
				break;
			}
		}
		
		System.out.println("Logout Done");
		driver.quit();
	}

}
