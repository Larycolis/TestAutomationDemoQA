package org.page.elements;

import io.qameta.allure.Step;
import org.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class WebTablesPage {
    private final WebDriver driver;
    @FindBy(id = "addNewRecordButton")
    private WebElement addNewRecordButton;
    @FindBy(id = "submit")
    private WebElement submit;

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Click add button")
    public void clickAddButton() {
        clickJavascriptExecutor12(addNewRecordButton);
    }

    @Step("Fill the table fields with valid data and submit")
    public void addEmployee(Employee employee) {
        clearAndType(By.id("firstName"), employee.getFirstName());
        clearAndType(By.id("lastName"), employee.getLastName());
        clearAndType(By.id("userEmail"), employee.getEmail());
        clearAndType(By.id("age"), employee.getAge());
        clearAndType(By.id("salary"), employee.getSalary());
        clearAndType(By.id("department"), employee.getDepartment());
        clickJavascriptExecutor12(submit);
    }

    @Step("Checking compliance of expected and actual employee")
    public void assertExpAndActEmployee(Employee employee) {
        Assertions.assertEquals(employee, getEmployee());
    }

    @Step("Click on the edit button on the created employee")
    public void clickEditButtonOnCreatedEmployee(int fullElementsCount) {
        driver.findElement(By.cssSelector("div.rt-tbody > div:nth-child(" + fullElementsCount + ") div > div > div > span#edit-record-" + fullElementsCount)).click();
    }

    @Step("Click on the delete button on the created employee")
    public void clickDeleteButtonOnCreatedEmployee(int fullElementsCount) {
        driver.findElement(By.cssSelector("div.rt-tbody > div:nth-child(" + fullElementsCount + ") div > div > div > span#delete-record-" + fullElementsCount)).click();
    }

    @Step("Get size of elements list")
    public int getFullElementsCount() {
        return getSizeOfElementsList();
    }

    @Step("Get employee names from table")
    public List<String> getFirstNames(int fullElementCountAfterDeleteLine) {
        List<String> firstNamesAfterDelete = new ArrayList<>();
        for (int i = 1; i < fullElementCountAfterDeleteLine + 1; i++) {
            firstNamesAfterDelete.add(driver.findElement(By.cssSelector("div.rt-tbody > div:nth-child(" + i + ") div > div")).getText());
        }
        return firstNamesAfterDelete;
    }

    @Step("Checking the list size before and after deleting an employee")
    public void assertSizeOfListBeforeAndAfterDeleted(int fullElementCountAfterDeleteLine, int fullElementsCount) {
        Assertions.assertEquals(fullElementCountAfterDeleteLine, fullElementsCount - 1);
    }

    @Step("Check availability of employee name")
    public void assertNameOfEmployeeIsUnavailable(List<String> firstNamesAfterDelete, Employee employee) {
        Assertions.assertFalse(firstNamesAfterDelete.contains(employee.getFirstName()));
    }

    private Employee getEmployee() {
        int fullElementsCount = getSizeOfElementsList();
        List<String> actualEmployeeValues = driver.findElements(By.cssSelector("div.rt-tbody > div:nth-child(" + fullElementsCount + ") div > div")).stream()
                .map(WebElement::getText).toList();
        return new Employee(actualEmployeeValues.get(0), actualEmployeeValues.get(1), actualEmployeeValues.get(2), actualEmployeeValues.get(3), actualEmployeeValues.get(4), actualEmployeeValues.get(5));
    }

    private int getSizeOfElementsList() {
        return driver.findElements(By.xpath("//div[@class='rt-tbody']/div/div[not(contains(@class, 'pad'))]")).size();
    }

    private void clearAndType(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    private void clickJavascriptExecutor12(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }
}
