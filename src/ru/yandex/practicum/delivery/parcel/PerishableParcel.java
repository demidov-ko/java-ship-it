package ru.yandex.practicum.delivery.parcel;

//СКОРОПОРТЯЩАЯСЯ ПОСЫЛКА

public class PerishableParcel extends Parcel {
    private static final int PERISHABLE_COST = 4;
    private final int timeToLive; //срок в днях, за который посылка не испортится

    public PerishableParcel(String description, int weight, String deliveryAddress,
                            int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    //метод проверки срока годности
    public boolean isExpired(int currentDay) {
        if ((currentDay - sendDay) >= timeToLive) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getBaseCost() {
        return PERISHABLE_COST;
    }
}
