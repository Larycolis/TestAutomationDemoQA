package org.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Employee {
    private final String firstName;
    private final String lastName;
    private final String age;
    private final String email;
    private final String salary;
    private final String department;
}
