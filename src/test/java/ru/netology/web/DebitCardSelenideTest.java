package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class DebitCardSelenideTest {
    @BeforeEach
    void setupUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSendFormSelenide() {
        $("[data-test-id=name] input").setValue("Александр Мужев-Петров");
        $("[data-test-id=phone] input").setValue("+79120009999");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldValidateNameFormSelenide() {
        $("[data-test-id=name] input").setValue("Aleksandr9148$%!@#%");
        $("[data-test-id=phone] input").setValue("+79120009999");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldNoNameFormSelenide() {
        $("[data-test-id=phone] input").setValue("+79120009999");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldValidatePhoneFormSelenide() {
        $("[data-test-id=name] input").setValue("Александр Мужев-Петров");
        $("[data-test-id=phone] input").setValue("+7912000");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldNoPhoneFormSelenide() {
        $("[data-test-id=name] input").setValue("Александр Мужев-Петров");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldIsSelectedCheckedCheckBoxSelenide() {
        $("[data-test-id=name] input").setValue("Александр Мужев-Петров");
        $("[data-test-id=phone] input").setValue("+79120009999");
        $(".checkbox__box").click();
        $(".checkbox_checked").isSelected();
    }

    @Test
    public void shouldUnCheckedCheckBoxSelenide() {
        $("[data-test-id=name] input").setValue("Александр Мужев-Петров");
        $("[data-test-id=phone] input").setValue("+79120009999");
        boolean checkbox = $(".checkbox__box").isSelected();
        Assertions.assertFalse(checkbox);
    }
}
