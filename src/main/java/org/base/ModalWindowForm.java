package org.base;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ModalWindowForm {
    public void checkModalWindowIsVisible() {
        $("div.modal-content").shouldBe(visible);
    }
    //Todo: написать метод который проверяет содержимое ModalWindow
}
