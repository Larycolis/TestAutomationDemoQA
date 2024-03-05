package org.base;

import org.entity.Student;
import org.helper.StudentBuilder;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ModalWindowForm {
    public ModalWindowForm checkModalWindowIsVisible() {
        $("div.modal-content").shouldBe(visible);
        return this;
    }

    //Todo: написать метод который проверяет содержимое ModalWindow, сравнение через equals
    public void compareActualAndExpectedStudent(Student expectedStudent) {
        Assertions.assertEquals(expectedStudent, getActualStudent());
    }

    private Student getActualStudent() {
        List<String> actualStudentValues = $$("tbody > tr > td:nth-child(2)").texts();
        return new StudentBuilder()
                .firstName(getSplittedName(actualStudentValues.get(0), 0))
                .lastName(getSplittedName(actualStudentValues.get(0), 1))
                .email(checkNotNull(actualStudentValues.get(1)))
                .gender(getGender(actualStudentValues.get(2)))
                .mobileNumber(actualStudentValues.get(3))
                .yearOfBirth(getYear(actualStudentValues.get(4)))
                .monthOfBirth(getMonth(actualStudentValues.get(4)))
                .dayOfBirth(getDay(actualStudentValues.get(4)))
                .subject(checkNotNull(actualStudentValues.get(5)))
                .hobby(getHobby(actualStudentValues.get(6)))
                .currentAddress(checkNotNull(actualStudentValues.get(7)))
                .state(checkNotNull(actualStudentValues.get(8)))
                .city(checkNotNull(actualStudentValues.get(9)))
                .build();
    }

    private String getSplittedName(String studentName, int index) {
        String[] parts = studentName.split(" ");
        return parts[index];
    }

    private int getGender(String genderValue) {
        return switch (genderValue) {
            case "Other" -> 3;
            case "Female" -> 2;
            default -> 1;
        };
    }

    private int getDay(String dateValue) {
        String[] parts = dateValue.split("[,\\s]+");
        return Integer.parseInt(parts[0]);
    }

    private int getMonth(String dateValue) {
        String[] parts = dateValue.split("[,\\s]+");
        return switch (parts[1]) {
            case "January" -> 1;
            case "February" -> 2;
            case "March" -> 3;
            case "April" -> 4;
            case "May" -> 5;
            case "Jun" -> 6;
            case "July" -> 7;
            case "August" -> 8;
            case "September" -> 9;
            case "October" -> 10;
            case "November" -> 11;
            case "December" -> 12;
            default -> 0;
        };
    }

    private int getYear(String dateValue) {
        String[] parts = dateValue.split("[,\\s]+");
        return switch (parts[2]) {
            case "1900" -> 1;
            case "1991" -> 92;
            case "2100" -> 201;
            default -> 0;
        };
    }

    private int getHobby(String hobbyValue) {
        return switch (hobbyValue) {
            case "Sports" -> 1;
            case "Reading" -> 2;
            case "Music" -> 3;
            default -> 0;
        };
    }

    private String checkNotNull(String value) {
        if (value.isEmpty()) {
            return null;
        } else {
            return value;
        }
    }
}
