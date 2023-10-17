package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebFormVerificationTest {
    /*
    В поле фамилии и имени разрешены только русские буквы, дефисы и пробелы.
    В поле телефона — только 11 цифр, символ + на первом месте.
    Флажок согласия должен быть выставлен.
     */
    @Test
    void shouldTest1() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid").shouldHave(exactText("Фамилия и имя\n" +
                "Поле обязательно для заполнения"));
   }

    @Test
    void shouldTest2() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue(" ");
        $("[data-test-id=phone] input").setValue("+12345678901");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid").shouldHave(exactText("Фамилия и имя\n" +
                "Поле обязательно для заполнения"));
    }

    @Test
    void shouldTest3() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("gg");
        $("[data-test-id=phone] input").setValue("+09270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid").shouldHave(exactText("Фамилия и имя\n" +
                "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTest4() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("123");
        $("[data-test-id=phone] input").setValue("+00000000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid").shouldHave(exactText("Фамилия и имя\n" +
                "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTest5() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ы");
        $("[data-test-id=phone] input").setValue("+2222222222");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(exactText("Мобильный телефон\n" +
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTest6() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ы");
        $("[data-test-id=phone] input").setValue("+");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(exactText("Мобильный телефон\n" +
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTest7() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("авы");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(exactText("Мобильный телефон\n" +
                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTest8() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(exactText("Мобильный телефон\n" +
                "Поле обязательно для заполнения"));
    }
    @Test
    void shouldTest9() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("+79012345678");
//        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

}
