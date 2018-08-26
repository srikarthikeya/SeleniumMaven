/**
 * @author KarthikeyaSri
 */
package com.automation.ecommerce.pageUI;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.automation.ecommerce.basePage.BasePage;

public class CreateLoginAccount extends BasePage
{
	public String uuid = UUID.randomUUID().toString();

	@FindBy(xpath = "//a[@class='login']")
	WebElement signInLocator;

	@FindBy(id = "email_create")
	WebElement emailLocator;

	@FindBy(xpath = "//*[@id='create_account_error']/ol/li")
	WebElement errSignInPage;

	@FindBy(id = "SubmitCreate")
	WebElement createAccountBtnLocator;

	@FindBy(xpath = "//h1[text()='Create an account']")
	WebElement createAccTextLocator;

	@FindBy(xpath = "//*[@id='center_column']/div")
	WebElement errFormPage;

	@FindBy(id = "id_gender1")
	WebElement genderLocator;

	@FindBy(id = "customer_firstname")
	WebElement firstNameLocator;

	@FindBy(id = "customer_lastname")
	WebElement lastNameLocator;

	@FindBy(id = "passwd")
	WebElement pwdLocator;

	@FindBy(id = "days")
	WebElement daysLocator;

	@FindBy(id = "months")
	WebElement monthsLocator;

	@FindBy(id = "years")
	WebElement yearsLocator;

	@FindBy(id = "newsletter")
	WebElement newsltrChkBxLocator;

	@FindBy(id = "optin")
	WebElement optinChkBxLocator;

	@FindBy(id = "company")
	WebElement companyLocator;

	@FindBy(id = "address1")
	WebElement address1Locator;
	
	/*@FindBy(id = "address2")
	WebElement address2Locator;*/
	
	@FindBy(id = "city")
	WebElement cityLocator;
	
	@FindBy(id = "id_state")
	WebElement stateLocator;
	
	@FindBy(id = "postcode")
	WebElement postcodeLocator;
	
	// @FindBy(id = "id_country") WebElement countryLocator;
	@FindBy(id = "other")
	WebElement additionalInfoTextBxLocator;
	
	@FindBy(id = "phone")
	WebElement homePhoneNumLocator;
	
	@FindBy(id = "phone_mobile")
	WebElement mobPhoneNumLocator;
	
	@FindBy(id = "alias")
	WebElement addressAliasLocator;
	
	@FindBy(id = "submitAccount")
	WebElement registerBtnLocator;

	@FindBy(xpath = "//a[@class='logout']")
	WebElement signoutLocator;
	
	@FindBy(xpath = "//h1[text()='My account']")
	WebElement myAccTextLocator;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[3]/a")
	WebElement tShirtLocator;

	public CreateLoginAccount(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void createAccount() throws Exception
	{
        //checkTitle(driver,"Login - My Store");
		signInLocator.click();
		emailLocator.sendKeys(getData("firstName") + getData("lastName") + randomNumber() + getData("domainName"));
		//#registered email : qatest4430@gmail.com and pwd abc@123
		createAccountBtnLocator.click();
		elementVisible(30, createAccTextLocator);
	}

	public void accountDetailsForm() throws Exception
	{
		genderLocator.click();
		firstNameLocator.sendKeys(getData("firstName")); // RandomStringUtils.randomAlphabetic(8)
		lastNameLocator.sendKeys(getData("lastName"));
		pwdLocator.sendKeys(getData("password"));
		
		elementVisible(30, createAccTextLocator);
		assertEquals(createAccTextLocator.getText(), "CREATE AN ACCOUNT");
		//assertEquals("Login - My Store", driver.getTitle());
		
		selectOption(daysLocator, 5);
		selectOption(monthsLocator, 5);
		selectOption(yearsLocator, 5);

		newsltrChkBxLocator.click();
		optinChkBxLocator.click();

		companyLocator.sendKeys(getData("company"));
		address1Locator.sendKeys(getData("address"));
		//address2Locator.sendKeys(getData("city"));
		cityLocator.sendKeys(getData("city"));
		
		selectOption(stateLocator, 5);
		
		postcodeLocator.sendKeys(getData("postalcode"));
		
		additionalInfoTextBxLocator.sendKeys(getData("addinfo"));
		
		homePhoneNumLocator.sendKeys(getData("homemob"));
		mobPhoneNumLocator.sendKeys(getData("mobphone"));
		addressAliasLocator.sendKeys(getData("aliasadd"));
		
		registerBtnLocator.click();
		
		elementVisible(30, myAccTextLocator);
		assertEquals(myAccTextLocator.getText(), "MY ACCOUNT");
		
		Thread.sleep(3000L);
		tShirtLocator.click();

	}

	/*public void screenShot(WebDriver driver, String screenShotName)
	{
		try
		{
			
			 * Date d = new Date(); DateFormat dFormat = new
			 * SimpleDateFormat("dd-MM-YYY HH:mm:ss");
			 
			TakesScreenshot t = (TakesScreenshot) driver;
			File src = t.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("./Screenshots/" + screenShotName + ".png")); // RandomStringUtils.randomAlphabetic(10)
			System.out.println("Screenshot taken !" + screenShotName);
		} catch (Exception e)
		{
			System.out.println("Exception while taking screen shot !" + "\t" + e.getMessage());
		}
	}*/

	public String signInPageError()
	{
		return errSignInPage.getText();
	}

	public String formPageError()
	{
		return errFormPage.getText();
	}

	public void logout()
	{
		if (signoutLocator.isDisplayed())
		{
			signoutLocator.click();
		}
	}
}