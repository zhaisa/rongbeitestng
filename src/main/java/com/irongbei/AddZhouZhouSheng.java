package com.irongbei;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.irongbeipages.LoginPage;
import config.RBConfig;

public class AddZhouZhouSheng extends RBConfig {
	@Parameters({ "zq", "danw", "jiange", "year", "month", "date", "way", "env","prefix","bianhao" })
	// @Test(dataProvider = "getdata")
	@Test
	public void addZhouZhouSheng(String zq, String money, int mylength, int myyear, int mymonth, int myday, String way,
			String env,String prefix,String bianhao) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");// 这一步必不可少
		WebDriver dr = new ChromeDriver();
		Calendar cc = new GregorianCalendar();
		cc.set(myyear, mymonth, myday);
		MyEnviment me = new MyEnviment();
		String befororback = env + "admin";
		String url = me.getEvi(env, befororback);
		dr.get(url + "/admin/login");
		LoginPage lp = new LoginPage(dr);
		lp.login("研发专用管理员", "123456");
		dr.findElement(By.linkText("项目管理")).click();
		Thread.sleep(1000);
		dr.findElement(By.partialLinkText("添加项目")).click();
		Thread.sleep(2000);
		dr.findElement(By.id("Check1")).click();
		dr.findElement(By.id("pro_bottom_confirm")).click();
		dr.navigate().to(url + "/admin/Project/editProject?pjType=weekrise");
//		String time = new SimpleDateFormat("HHmmss").format(cc.getTime());


	String prefix1=prefix;
			
		
		String name = "房抵贷1912" + prefix1;
		System.out.println(name);
		dr.findElement(By.name("project_name")).sendKeys(name);
		
		dr.findElement(By.name("project_num")).sendKeys(bianhao);
		List<WebElement> list11 = dr.findElements(By.name("is_lock"));

		list11.get(0).click();
		Select ss1 = new Select(dr.findElement(By.id("xuanzh")));// 项目类型
//1企业贷3房抵贷4 消费贷5车抵贷11汽车消费金融
		List<WebElement> list2 = ss1.getOptions();
		ss1.selectByVisibleText(list2.get(1).getText());
		Select ss2 = new Select(dr.findElement(By.name("real_payment")));// 还款方式
		ss2.selectByValue(way);
		// List<WebElement> list2 = ss2.getOptions();
		dr.findElement(By.xpath("//*[@id=\"right-box\"]/div[2]/div[3]/div[12]/span/span[1]/span/span[2]")).click();
		// *[@id="right-box"]/div[2]/div[3]/div[11]/span/span[1]/span/span[2]
		// *[@id="right-box"]/div[2]/div[3]/div[12]/span/span[1]/span/span[2]/b
		Thread.sleep(2000);
		WebElement we = dr.findElement(By.className("select2-search__field"));

		Actions action = new Actions(dr);
		action.moveToElement(we).click();
		if (env.equals("dev")) {
			action.sendKeys("贝塔06");// 水火不容18101169572 //汪汪迁移企业测试有限公司
		} else {
			action.sendKeys(this.jiekuanren);
		}

		action.moveToElement(we).perform();

		Thread.sleep(3000);
		action.sendKeys(Keys.DOWN);
		action.sendKeys(Keys.ENTER);
		action.perform();
		Select s3 = new Select(dr.findElement(By.name("contract_type")));

		s3.selectByValue("31");// 直融——车贷(消费金融)——等额本息

		Select s4 = new Select(dr.findElement(By.id("template_id")));
		// s4.selectByVisibleText("模板6");//选择模板6
		s4.selectByValue("295");// 房抵贷
		dr.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		List<WebElement> list3 = dr.findElements(By.xpath("//input[@class='tc-name text-style']"));
		list3.get(0).sendKeys("东风北桥");
		list3.get(1).sendKeys("三室两厅");
		list3.get(2).sendKeys("160平米");
		list3.get(3).sendKeys("3000000元");
		dr.findElement(By.linkText("确定")).click();
		dr.findElement(By.name("cycle")).sendKeys(zq);// 还款周期月
		dr.findElement(By.id("p_sum")).sendKeys(money);// 输入金额1万
		Thread.sleep(1000);
		// dr.findElement(By.id("rate")).sendKeys("8");// 年利率8%
		dr.findElement(By.id("cre_rate")).clear();
		dr.findElement(By.id("cre_rate")).sendKeys("15");
		JavascriptExecutor jse = (JavascriptExecutor) dr;
		Boolean loaded;
		try {
			loaded = (Boolean) jse.executeScript("return " + "jQuery()!=null");
		} catch (WebDriverException e) {
			loaded = false;
		}
		String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(cc.getTime());// 对日期进行格式化
		System.out.println(startDate);
		String changereadonly = "$('input[name=online_time]').attr(\"readonly\",false)";
		((JavascriptExecutor)dr).executeScript(changereadonly);
		Thread.sleep(1000);
		 JavascriptExecutor js= (JavascriptExecutor) dr;
         //输入时间
         js.executeScript("arguments[0].value=\""+startDate+"\"",dr.findElement(By.name("online_time")));
//		dr.findElement(By.name("online_time")).clear();
//		dr.findElement(By.name("online_time")).sendKeys(startDate);
	Thread.sleep(1000);
		dr.switchTo().defaultContent();
		cc.add(Calendar.MONTH, mylength);
		cc.add(Calendar.DATE, 1);
		Date dt = cc.getTime();// date就是你需要的时间

		String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dt);
		System.out.println(endDate);
		String changereadonly1 = "$('input[name=end_time]').attr(\"readonly\",false)";
		((JavascriptExecutor) dr).executeScript(changereadonly1);
		dr.findElement(By.name("end_time")).clear();
		dr.findElement(By.name("end_time")).sendKeys(endDate);
		dr.findElement(By.name("credit_number")).sendKeys(startDate);
		dr.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		dr.switchTo().defaultContent();

		// dr.findElement(By.xpath("//id[@value='']")).click();
		// dr.findElement(By.id("wrap")).findElement(By.className("qyzj-bm-btn")).click();
		// dr.findElement(By.xpath("//*[@id=\"sub\"]")).click();
		/*
		 * 无法通过页面获取点击状态，通过jquery获取到指定位置，点击
		 */
		String changereadonly2 = "$('#sub').click()";
		((JavascriptExecutor) dr).executeScript(changereadonly2);

		// dr.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(1000);
		dr.switchTo().alert().accept();
		Thread.sleep(2000);
		dr.switchTo().alert().accept();
		  dr.navigate().to(url+"/admin/Project/index");
	        Thread.sleep(3000);
	        dr.navigate().refresh();
	        Thread.sleep(1000);
//	        ReadFromTable rft=new ReadFromTable();
//	        rft.readFromTable(dr, "/html/body/div[3]/div[2]/div[2]/div[2]/table/tbody", name);
//	        Thread.sleep(2000);
//		// assertEaquls("添加成功";dr.findElement(By));
//		Thread.sleep(3000);

		dr.close();
		dr.quit();

	}

	@DataProvider(name = "getdata")
	public Object[][] getdata() {
		// String zq,String money,int mylength, int myyear, int mymonth, int myday
		Object[][] value = { { "6", "1", 6, 2019, 0, 29 } };
		return value;
	}
}
