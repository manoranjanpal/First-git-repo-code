# First-git-repo-code
First code to push to git repo
package admin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Batch_Tc3 {
	private static Logger log=Logger.getLogger( Batch_Tc3.class);
	FirefoxDriver driver;
	private XSSFWorkbook wo;
	@BeforeTest
	public void open()
	{
		DOMConfigurator.configure("log4j.xml");
		driver=new FirefoxDriver();
		log.info("Open Browser Succesfully");
		driver.get("http://akuteegapadu.com/student/StudentWeb/login.php");
		log.info("Navigate to student login succesfully");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	@Test(priority=0)
	public void login()
	{
		driver.findElement(By.name("uname")).sendKeys("siridhar10@gmail.com");
		log.info("Username Inbox Working Successfully");
		driver.findElement(By.name("pwd")).sendKeys("123456");
		log.info("Password Inbox Working Successfully");
		driver.findElement(By.name("submit")).click();
		log.info("Login Successfully");
		
	}
	@Test(priority=1)
	public void student()
	{
		driver.findElement(By.linkText("Students")).click();
		log.info("Click Student succ");
		driver.findElement(By.linkText("Create Students")).click();	
		log.info("Click on Create Student succ");
		
			
	}
	@Test(priority=2)
	public void BatchUploades() throws IOException
	{
		FileInputStream Fp=new FileInputStream("C:\\Users\\vivek\\Documents\\EDMODO PROJECT\\20sep\\REQ_1_Automation Cases.xlsx");
		log.info("Open file succ");
		wo = new XSSFWorkbook(Fp);
		log.info("Open workbook succ");
		XSSFSheet ws=wo.getSheet("Batch");
		log.info("Sheet open succ");
		Row r;
		WebElement batch=driver.findElement(By.name("batch_name"));
		List<WebElement> batchlist=batch.findElements(By.tagName("option"));
		log.info("Batch are"+batchlist.size());
		for (int i = 1; i < batchlist.size(); i++) {
			r=ws.createRow(i);
			r.createCell(0).setCellValue(batchlist.get(i).getText());
		}
		log.info("insert batch dropdown value succ");
		 FileOutputStream fo=new FileOutputStream("C:\\Users\\vivek\\Documents\\EDMODO PROJECT\\20sep\\REQ_1_Automation Cases.xlsx");
         wo.write(fo);
         log.info("save batch succ");
		
		}
	@Test(priority=3)
	public void BatchTableUploades() throws IOException
	{
		FileInputStream Fp=new FileInputStream("C:\\Users\\vivek\\Documents\\EDMODO PROJECT\\20sep\\REQ_1_Automation Cases.xlsx");
		log.info("Open file succ");
		wo = new XSSFWorkbook(Fp);
		log.info("Open workbook succ");
		XSSFSheet ws=wo.getSheet("Batch");
		log.info("Sheet open succ");
		Row r;

	    	driver.findElement(By.linkText("Configuration")).click();
	    	log.info("click on Configuration succ");
			driver.findElement(By.linkText("Batches")).click();
			log.info("click on Batches succ");
            WebElement tablebatch=driver.findElement(By.tagName("table"));
            List<WebElement> tr=tablebatch.findElements(By.tagName("tr"));
            
            for (int i = 1; i < tr.size(); i++) {
			String test=driver.findElement(By.xpath("html/body/div[1]/div[1]/section[2]/div/table/tbody/tr["+(i+1)+"]/td[3]")).getText();
            r=ws.getRow(i);
            r.createCell(1).setCellValue(test);
            if (r.getCell(0).getStringCellValue().equals(test)) {
				r.createCell(2).setCellValue("pass");
			}else {
				r.createCell(2).setCellValue("fail");
			}
		    
		    }
            log.info("insert table batch succ");
            FileOutputStream fo=new FileOutputStream("C:\\Users\\vivek\\Documents\\EDMODO PROJECT\\20sep\\REQ_1_Automation Cases.xlsx");
            wo.write(fo);
            log.info("save batch succ");
		}
	
}
