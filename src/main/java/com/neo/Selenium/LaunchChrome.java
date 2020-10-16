package com.neo.Selenium;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/15
 */

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchChrome {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start selenium");
        // 设置chrome的安装路径  
        //System.setProperty("webdriver.chrome.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        // 设置chromedriver路径  
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        // 初始化Chrome浏览器实例  
        WebDriver driver = new ChromeDriver();
        // 最大化窗口    
        driver.manage().window().maximize();
        // 设置隐性等待时间    
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        // 打开一个站点  
        driver.get("http://www.baidu.com/");
        WebElement kw = driver.findElement(By.id("kw"));
        kw.sendKeys("自动化测试");
        Thread.sleep(10000);
        // 获取当前页面的标题   #kw
        System.out.println("当前打开页面的标题是：" + driver.getTitle());
        // 关闭浏览器  
        driver.close();
        System.out.println("end selenium");
    }

}

