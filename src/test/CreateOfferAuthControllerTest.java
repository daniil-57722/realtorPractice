package test;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import sample.controller.CreateOfferController;

import static org.junit.jupiter.api.Assertions.*;

class CreateOfferAuthControllerTest {

    @org.junit.jupiter.api.Test
    void isFillRealtorError() {
        CreateOfferController createOfferController = new CreateOfferController();
        String res = createOfferController.isFill("mark", null, "89874440099", "house", "100", "г.Пенза");
        Assertions.assertEquals("Выберите риэлтора", res);
    }
    @org.junit.jupiter.api.Test
    void isFillFieldError() {
        CreateOfferController createOfferController = new CreateOfferController();
        String res = createOfferController.isFill("mark", "Алексей", "", "house", "100", "г.Пенза");
        Assertions.assertEquals("Заполните поля!", res);
    }
    @org.junit.jupiter.api.Test
    void isFillPriceError(){
        CreateOfferController createOfferController = new CreateOfferController();
        String res = createOfferController.isFill("mark", "Алексей", "89874440099", "house", "ghg", "г.Пенза");
        Assertions.assertEquals("Укажите цену цифрами", res);
    }
    @org.junit.jupiter.api.Test
    void isFillAllOk(){
        CreateOfferController createOfferController = new CreateOfferController();
        String res = createOfferController.isFill("mark", "Алексей", "89874440099", "house", "100", "г.Пенза");
        Assertions.assertEquals("Успех", res);
    }

}