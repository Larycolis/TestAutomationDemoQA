package org.base;

import org.entity.Student;
import org.helper.StudentBuilder;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ModalWindowForm {
    private static final String REGEX = "[,\\s]+";

    public ModalWindowForm checkModalWindowIsVisible() {
        $("div.modal-content").shouldBe(visible);
        return this;
    }

    public void compareActualAndExpectedStudent(Student expectedStudent) {
        Assertions.assertEquals(expectedStudent, getActualStudent());
    }

    private Student getActualStudent() {
        StudentBuilder studentBuilder = new StudentBuilder();
        List<String> actualStudentValues = $$("tbody > tr > td:nth-child(2)").texts();
        for (int i = 0; i < actualStudentValues.size(); i++) {
            if (!actualStudentValues.get(0).isEmpty()) {
                studentBuilder.firstName(getSplittedStringValue(actualStudentValues.get(0), 0));
                studentBuilder.lastName(getSplittedStringValue(actualStudentValues.get(0), 1));
            }
            if (!actualStudentValues.get(1).isEmpty()) {
                studentBuilder.email(actualStudentValues.get(1));
            }
            if (!actualStudentValues.get(2).isEmpty()) {
                studentBuilder.gender(getGender(actualStudentValues.get(2)));
            }
            if (!actualStudentValues.get(3).isEmpty()) {
                studentBuilder.mobileNumber(actualStudentValues.get(3));
            }
            if (!actualStudentValues.get(4).isEmpty()) {
                studentBuilder.yearOfBirth(getSplittedIntValue(actualStudentValues.get(4), 2));
                studentBuilder.monthOfBirth(getSplittedStringValue(actualStudentValues.get(4), 1));
                studentBuilder.dayOfBirth(getSplittedIntValue(actualStudentValues.get(4), 0));
            }
            if (!actualStudentValues.get(5).isEmpty()) {
                studentBuilder.subject(actualStudentValues.get(5));
            }
            if (!actualStudentValues.get(6).isEmpty()) {
                studentBuilder.hobby(getHobby(actualStudentValues.get(6)));
            }
            //if (!actualStudentValues.get(7).isEmpty() && actualStudentValues.get(7) != null)
            if (!actualStudentValues.get(8).isEmpty()) {
                studentBuilder.currentAddress(actualStudentValues.get(8));
            }
            if (!actualStudentValues.get(9).isEmpty()) {
                studentBuilder.state(getSplittedStringValue(actualStudentValues.get(9), 0));
                studentBuilder.city(getSplittedStringValue(actualStudentValues.get(9), 1));
            }
        }
        return studentBuilder.build();
    }

    private String getSplittedStringValue(String values, int index) {
        return values.split(REGEX)[index];
    }

    private int getSplittedIntValue(String dateValues, int index) {
        return Integer.parseInt(dateValues.split(REGEX)[index]);
    }

    private int getGender(String genderValue) {
        return switch (genderValue) {
            case "Other" -> 3;
            case "Female" -> 2;
            default -> 1;
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
}
