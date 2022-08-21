package evaluation;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		WebElement SearchText = driver.findElement(By.id("twotabsearchtextbox"));
		SearchText.sendKeys("samsung");

		WebElement SearchButton = driver.findElement(By.id("nav-search-submit-button"));
		SearchButton.click();
		
		
		List<WebElement> AllProduct = driver.findElements(By.xpath("//div[@class='a-section']//span[starts-with(text(),'Samsung ')]"));
		List<WebElement> ProductList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
		
		System.out.println("Total Products found " +ProductList.size());
		for(int i=0;i<AllProduct.size();i++) {
			System.out.println(AllProduct.get(i).getText()+" "+ ProductList.get(i).getText());
		}

		String parentwindow = driver.getWindowHandle();
		String Expectedvalue = AllProduct.get(0).getText();
		AllProduct.get(0).click();
		Set<String> allwin = driver.getWindowHandles();
		for (String win : allwin) {
			System.out.println(win);
			if (!win.equals(parentwindow)) {

				driver.switchTo().window(win);
			}
		}
		WebElement producttitle = driver.findElement(By.id("productTitle"));
		String title = producttitle.getText();
		if (title.equals(Expectedvalue)) {
			System.out.println("Title is matched");
		} else {
			System.out.println("Titile is not matched");
		}

		driver.quit();

	}

}




