package org.entity;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final String age;
    private final String email;
    private final String salary;
    private final String department;

    public Employee(String firstName, String lastName, String age, String email, String salary, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.salary = salary;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!firstName.equals(employee.firstName)) return false;
        if (!lastName.equals(employee.lastName)) return false;
        if (!age.equals(employee.age)) return false;
        if (!email.equals(employee.email)) return false;
        if (!salary.equals(employee.salary)) return false;
        return department.equals(employee.department);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + salary.hashCode();
        result = 31 * result + department.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return firstName + ", " + lastName + ", " + age + ", " + email + ", " + salary + ", " + department;
    }
}
