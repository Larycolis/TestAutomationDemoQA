package ui.elements;

import io.qameta.allure.Step;
import org.base.BaseTest;
import org.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.page.elements.WebTablesPage;

import static org.util.FakerUtil.*;

public class WebTablesTests extends BaseTest {
    private WebTablesPage webTablesPage;

    @BeforeEach
    void setUp() {
        webTablesPage = new WebTablesPage(getDriver());
        getDriver().get("https://demoqa.com/webtables");
    }

    @Test
    @DisplayName("Add the employee to the table")
    void addTableLineAndCheckItInTableTest() {
        Employee employee = generateEmployee();
        webTablesPage.clickAddButton();
        webTablesPage.addEmployee(employee);
        webTablesPage.assertExpAndActEmployee(employee);
    }

    @Test
    @DisplayName("Add the employee to the table and change its fields")
    void editTableLineAndCheckChanges() {
        Employee employee = generateEmployee();
        Employee editedEmployee = generateEmployee();
        webTablesPage.clickAddButton();
        webTablesPage.addEmployee(employee);
        webTablesPage.clickEditButtonOnCreatedEmployee();
        webTablesPage.addEmployee(editedEmployee);
        webTablesPage.assertExpAndActEmployee(editedEmployee);
    }

    @Test
    @DisplayName("Add an employee to the table and remove him from the table")
    void deleteTableLineAndCheckChanges() {
        Employee employee = generateEmployee();
        webTablesPage.clickAddButton();
        webTablesPage.addEmployee(employee);
        webTablesPage.clickDeleteButtonOnCreatedEmployee();
        webTablesPage.assertSizeAfterDeleted();
        webTablesPage.assertNameOfEmployeeIsUnavailable(employee);
    }

    @Step("Generate an employee using required fields")
    private Employee generateEmployee() {
        return new Employee(getFirstName(), getLastName(), getAge().toString(), getEmailAddress(), getSalary().toString(), getJob());
    }
}
