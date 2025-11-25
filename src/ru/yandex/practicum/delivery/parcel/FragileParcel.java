package ru.yandex.practicum.delivery.parcel;

//ХРУПКАЯ ПОСЫЛКА

public class FragileParcel extends Parcel implements Trackable {
    private static final int FRAGILE_COST = 3;

    public FragileParcel(String description, int weight, String deliveryAddress,
                         int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка \u001B[1m" + description + "\u001B[0m обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка \u001B[1m" + description +
                "\u001B[0m изменила местоположение на \u001B[1m" + newLocation + "\u001B[0m");
    }

    @Override
    public int getBaseCost() {
        return FRAGILE_COST;
    }

}
