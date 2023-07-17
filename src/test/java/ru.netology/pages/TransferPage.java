package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement transferButton = $("[data-text-id='action-transfer']");
    private final SelenideElement amountInputNew = $("[data-text-id='amount'] input");
    private final SelenideElement fromInput = $("[data-text-id='from'] input");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMassage = $("[data-text-id = 'error-message']");

    public TransferPage() {

        transferHead.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return  new DashboardPage();
    }
    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInputNew.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMassage(String expectedText) {
        errorMassage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}