package org.base;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ModalWindowForm {
    public ModalWindowForm checkModalWindowIsVisible() {
        $("div.modal-content").shouldBe(visible);
        return this;
    }
    //Todo: написать метод который проверяет содержимое ModalWindow
}
