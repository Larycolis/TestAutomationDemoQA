package ui.elements;

import com.github.javafaker.Faker;
import org.base.BaseTest;
import org.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebTablesTests extends BaseTest {
    private final Faker faker = new Faker();
    private static final String XPATH_ELEMENTS = "//div[@class='rt-tbody']/div/div[not(contains(@class, 'pad'))]";

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/webtables");
    }

    @Test
    void addTableLineAndCheckItInTableTest() {
        WebDriver driver = getDriver();
        Employee employee = generateEmployee();
        driver.findElement(By.id("addNewRecordButton")).click();
        addEmployee(employee);
        Assertions.assertEquals(employee, getEmployee());
    }

    @Test
    void editTableLineAndCheckChanges() {
        WebDriver driver = getDriver();
        Employee employee = generateEmployee();
        Employee editedEmployee = generateEmployee();
        driver.findElement(By.id("addNewRecordButton")).click();
        addEmployee(employee);
        int fullElementsCount = getFullElementsCount();
        driver.findElement(By.cssSelector("div.rt-tbody > div:nth-child(" + fullElementsCount + ") div > div > div > span#edit-record-" + fullElementsCount)).click();
        addEmployee(editedEmployee);
        Assertions.assertEquals(editedEmployee, getEmployee());
    }

    @Test
    void deleteTableLineAndCheckChanges() {
        WebDriver driver = getDriver();
        Employee employee = generateEmployee();
        driver.findElement(By.id("addNewRecordButton")).click();
        addEmployee(employee);
        int fullElementsCount = getFullElementsCount();
        driver.findElement(By.cssSelector("div.rt-tbody > div:nth-child(" + fullElementsCount + ") div > div > div > span#delete-record-" + fullElementsCount)).click();
        int fullElementCountAfterDeleteLine = getFullElementsCount();
        List<String> firstNamesAfterDelete = getFirstNames(fullElementCountAfterDeleteLine);
        Assertions.assertEquals(fullElementCountAfterDeleteLine, fullElementsCount - 1);
        Assertions.assertFalse(firstNamesAfterDelete.contains(employee.getFirstName()));
    }

    //over testing
    @Test
    void addTableLinesAndCheckLestLineInTableTest() {
        WebDriver driver = getDriver();
        int employeeCount = faker.number().numberBetween(1, 7);
        List<Employee> listOfEmployee = addEmployeeToList(employeeCount);
        for (Employee tmp : listOfEmployee) {
            driver.findElement(By.id("addNewRecordButton")).click();
            addEmployee(tmp);
        }
        int fullElementsCount = getSizeOfElementsList();
        List<String> actualEmployeeValues = driver.findElements(By.cssSelector("div.rt-tbody > div:nth-child(" + fullElementsCount + ") div > div")).stream()
                .map(WebElement::getText).toList();
        Employee actualEmployee = new Employee(actualEmployeeValues.get(0), actualEmployeeValues.get(1),
                actualEmployeeValues.get(2), actualEmployeeValues.get(3),
                actualEmployeeValues.get(4), actualEmployeeValues.get(5));
        Assertions.assertEquals(listOfEmployee.get(employeeCount - 1), actualEmployee);
    }

    private Employee generateEmployee() {
        return new Employee(faker.name().firstName(), faker.name().lastName(),
                Integer.toString(faker.number().numberBetween(1, 99)), faker.internet().emailAddress(),
                Integer.toString(faker.number().numberBetween(1, 15000)), faker.job().field());
    }

    private void addEmployee(Employee employee) {
        WebDriver driver = getDriver();
        clearAndType(By.id("firstName"), employee.getFirstName());
        clearAndType(By.id("lastName"), employee.getLastName());
        clearAndType(By.id("userEmail"), employee.getEmail());
        clearAndType(By.id("age"), employee.getAge());
        clearAndType(By.id("salary"), employee.getSalary());
        clearAndType(By.id("department"), employee.getDepartment());
        driver.findElement(By.id("submit")).click();
    }

    private void clearAndType(By locator, String value) {
        WebDriver driver = getDriver();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    private Employee getEmployee() {
        WebDriver driver = getDriver();
        int fullElementsCount = getSizeOfElementsList();
        List<String> actualEmployeeValues = driver.findElements(By.cssSelector("div.rt-tbody > div:nth-child(" + fullElementsCount + ") div > div")).stream()
                .map(WebElement::getText).toList();
        return new Employee(actualEmployeeValues.get(0), actualEmployeeValues.get(1), actualEmployeeValues.get(2), actualEmployeeValues.get(3), actualEmployeeValues.get(4), actualEmployeeValues.get(5));
    }

    private int getFullElementsCount() {
        return getSizeOfElementsList();
    }

    private int getSizeOfElementsList() {
        WebDriver driver = getDriver();
        return driver.findElements(By.xpath(XPATH_ELEMENTS)).size();
    }

    private List<String> getFirstNames(int fullElementCountAfterDeleteLine) {
        WebDriver driver = getDriver();
        List<String> firstNamesAfterDelete = new ArrayList<>();
        for (int i = 1; i < fullElementCountAfterDeleteLine + 1; i++) {
            firstNamesAfterDelete.add(driver.findElement(By.cssSelector("div.rt-tbody > div:nth-child(" + i + ") div > div")).getText());
        }
        return firstNamesAfterDelete;
    }

    private List<Employee> addEmployeeToList(int employeeCount) {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < employeeCount; i++) {
            list.add(generateEmployee());
        }
        return list;
    }
}
