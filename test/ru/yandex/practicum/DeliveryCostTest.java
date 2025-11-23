package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.parcel.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {

    @Test
    public void calculatingTheCostOfStandardParcel() {
        Parcel parcel = new StandardParcel("Стул", 3, "м.Московская", 23);
        int count = parcel.calculateDeliveryCost();
        assertEquals(6, count);
    }

    @Test
    public void calculatingTheCostOfFragileParcel() {
        Parcel parcel = new FragileParcel("Телевизор", 10, "м.Московская", 23);
        int count = parcel.calculateDeliveryCost();
        assertEquals(30, count);
    }

    @Test
    public void calculatingTheCostOfPerishableParcel() {
        Parcel parcel = new PerishableParcel("Пицца", 2, "м.Московская",
                23, 1);
        int count = parcel.calculateDeliveryCost();
        assertEquals(8, count);
    }

    @Test
    public void freshnessOfPerishableParcelWhenTheExpirationDateMatches() {
        int currentDay = 24;
        PerishableParcel parcel = new PerishableParcel("Пицца", 2, "м.Московская",
                23, 1);
        assertTrue(parcel.isExpired(currentDay));
    }

    @Test
    public void freshnessOfPerishableParcelWhenTheExpirationDateDoesNotMatches() {
        int currentDay = 25;
        PerishableParcel parcel = new PerishableParcel("Пицца", 2, "м.Московская",
                23, 1);
        assertFalse(parcel.isExpired(currentDay));
    }

    @Test
    public void freshnessOfPerishableParcelUponDeliveryOnTheDayOfTheOrder() {
        int currentDay = 23;
        PerishableParcel parcel = new PerishableParcel("Пицца", 2, "м.Московская",
                23, 1);
        assertTrue(parcel.isExpired(currentDay));
    }

    @Test
    public void checkingTheAdditionOfNewParcelThatDoesNotExceedingToTheBoxLimit() {
        ParcelBox mediumBox = new ParcelBox<>(10);
        Parcel parcel = new FragileParcel("Телевизор", 7, "м.Московская", 23);
        assertTrue(mediumBox.addParcel(parcel));

    }

    @Test
    public void checkingTheAdditionOfNewParcelThatDoesExceedingToTheBoxLimit() {
        ParcelBox mediumBox = new ParcelBox<>(10);
        Parcel parcel = new FragileParcel("Телевизор", 11, "м.Московская", 23);
        assertFalse(mediumBox.addParcel(parcel));
    }

    @Test
    public void checkingTheAdditionOfNewParcelWithWeightEqualToTheBoxLimit() {
        ParcelBox mediumBox = new ParcelBox<>(10);
        Parcel parcel = new FragileParcel("Телевизор", 10, "м.Московская", 23);
        assertTrue(mediumBox.addParcel(parcel));
    }
}
