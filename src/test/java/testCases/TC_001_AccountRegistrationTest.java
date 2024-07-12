package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"regression","master"})
	public void verify_account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest  ****");
		logger.debug("This is a debug log message");
		try
		{
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link.. ");

			hp.clickRegister();
			logger.info("Clicked on Register Link.. ");

			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);

			logger.info("Providing customer details...");
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
			regpage.setTelephone(randomeNumber());

			String password=randomeAlphaNumberic();

			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();

			logger.info("Validating expected message..");

			String confmsg = regpage.getConfirmationMsg();
			if (confmsg.equalsIgnoreCase("Your Account Has Been Created!")){
				logger.info("Test passed");
			}else {
				logger.error("Test failed");
				Assert.fail("Test failed");
			}

		}
		finally
		{
			logger.info("***** Finished TC001_AccountRegistrationTest *****");
		}

	}




}
