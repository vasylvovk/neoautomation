package com.gmail.vasylvovkastr;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by vasyl on 1/31/16.
 */
public class NEODySTools {

    private String pos0 = "<pre style=\"text-align:center\">";
    private String pos1 = "</pre>";

    private String baseBodyLink = "http://newton.dm.unipi.it/neodys/index.php?pc=1.1.3.0&n=";

    public void saveBodyEphem(String savePath, String numberName, String text) {

        String textEphem = text.
                split(pos0)[1].
                split(pos1)[0];

        String fullPath = savePath + numberName + ".neo";
        System.out.println(fullPath);
        File file = new File(fullPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(textEphem);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getAstList(String ref) {
        List res = new ArrayList();
        WebDriver driver = new FirefoxDriver();
        driver.get(ref);
        List<WebElement> element = driver.findElements(By.className("colorNeaLink"));
        for (WebElement item : element) {
            res.add(item.getText());
        }
        driver.close();
        return res;
    }

    public void setValueByXPath(WebDriver wb, String xp, String value) {
        WebElement element = wb.findElement(By.xpath(xp));
        element.clear();
        element.sendKeys(value);
    }

    public String getAstBody(String bodyNumber) {
        String result = "";
        String link = baseBodyLink + bodyNumber;
        WebDriver driver1 = new FirefoxDriver();
// bug with end and beginig of two monthes !!!!!
        ZonedDateTime zdt = ZonedDateTime.now();
        int year = zdt.getYear();
        int month = zdt.getMonthValue();
        int day = zdt.getDayOfMonth();
        zdt = zdt.plusDays(1);


        driver1.get(link);
        String xObservartory = ".//*[@id='mainContent2']/form/table/tbody/tr[1]/td[2]/input";
        String xBeginYear = ".//*[@id='mainContent2']/form/table/tbody/tr[2]/td[2]/input[1]";
        String xBeginMonth = ".//*[@id='mainContent2']/form/table/tbody/tr[2]/td[2]/input[2]";
        String xBeginDay = ".//*[@id='mainContent2']/form/table/tbody/tr[2]/td[2]/input[3]";
        String xBeginHouts = ".//*[@id='mainContent2']/form/table/tbody/tr[2]/td[2]/input[4]";
        String xBeginMinutes = ".//*[@id='mainContent2']/form/table/tbody/tr[2]/td[2]/input[5]";

        String xEndYear = ".//*[@id='mainContent2']/form/table/tbody/tr[3]/td[2]/input[1]";
        String xEndMonth = ".//*[@id='mainContent2']/form/table/tbody/tr[3]/td[2]/input[2]";
        String xEndDay = ".//*[@id='mainContent2']/form/table/tbody/tr[3]/td[2]/input[3]";
        String xEndHouts = ".//*[@id='mainContent2']/form/table/tbody/tr[3]/td[2]/input[4]";
        String xEndMinutes = ".//*[@id='mainContent2']/form/table/tbody/tr[3]/td[2]/input[5]";

        String xTimeInterval = ".//*[@id='mainContent2']/form/table/tbody/tr[4]/td[2]/input";
        String xTimeDimention = ".//*[@id='mainContent2']/form/table/tbody/tr[4]/td[2]/select";
        String xCompute = ".//*[@id='mainContent2']/form/table/tbody/tr[5]/td/input[1]";

        setValueByXPath(driver1, xObservartory, "089");
        setValueByXPath(driver1, xBeginYear, Integer.toString(year));
        setValueByXPath(driver1, xBeginMonth, Integer.toString(month));
        setValueByXPath(driver1, xBeginDay, Integer.toString(day));
        setValueByXPath(driver1, xBeginHouts, "15");
        setValueByXPath(driver1, xBeginMinutes, "0");

        setValueByXPath(driver1, xEndYear, Integer.toString(zdt.getYear()));
        setValueByXPath(driver1, xEndMonth, Integer.toString(zdt.getMonthValue()));
        setValueByXPath(driver1, xEndDay, Integer.toString(zdt.getDayOfMonth()));
        setValueByXPath(driver1, xEndHouts, "6");
        setValueByXPath(driver1, xEndMinutes, "0");

        setValueByXPath(driver1, xTimeInterval, Integer.toString(6));
        driver1.findElement(By.xpath(xTimeDimention)).sendKeys("minutes\n");
        driver1.findElement(By.xpath(xCompute)).click();

        result = driver1.getPageSource();

        driver1.close();

        return result;
    }
}