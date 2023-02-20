import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DuotifyTest {
    WebDriver driver = new ChromeDriver();
    Faker faker = new Faker();

    @Test
    public void testDuotifyRegistration() throws InterruptedException {
        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");

        String expectedTitle = "Welcome to Duotify!";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        driver.findElement(By.id("hideLogin")).click();
        String userName = faker.name().username();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        driver.findElement(By.id("username")).sendKeys(userName);
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("email2")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password2")).sendKeys(password);
        driver.findElement(By.name("registerButton")).click();

        String expectedUrl = "http://duotify.us-east-2.elasticbeanstalk.com/browse.php?";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        String expectedFullName = firstName + " " + lastName;
        String actualFullName = driver.findElement(By.id("nameFirstAndLast")).getText();
        Thread.sleep (1000);
        Assert.assertEquals(actualFullName, expectedFullName);
        driver.findElement(By.id("nameFirstAndLast")).click();

        String expectedProfileName = firstName + " " + lastName;
        Thread.sleep (1000);
        String actualProfileName = driver.findElement(By.className("userInfo")).getText();
        Assert.assertEquals(actualProfileName, expectedProfileName);
        driver.findElement(By.id("rafael")).click();
        expectedUrl = "http://duotify.us-east-2.elasticbeanstalk.com/register.php";
        Thread.sleep (1000);
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        driver.findElement(By.id("loginUsername")).sendKeys(userName);
        driver.findElement(By.id("loginPassword")).sendKeys(password);
        driver.findElement(By.name("loginButton")).click();
        Thread.sleep (1000);
        Assert.assertTrue(driver.getPageSource().contains("You Might Also Like"));
        driver.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep (1000);
        driver.findElement(By.id("rafael")).click();
        expectedUrl = "http://duotify.us-east-2.elasticbeanstalk.com/register.php";
        Thread.sleep (1000);
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        driver.quit();
    }}
