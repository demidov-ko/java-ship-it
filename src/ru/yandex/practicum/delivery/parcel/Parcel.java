package ru.yandex.practicum.delivery.parcel;

public abstract class Parcel {
    protected String description; //краткое описание
    protected int weight; //вес
    protected String deliveryAddress; //адрес места назначения
    protected int sendDay; //день отправки
    protected int cost; //базовая стоимость 1 посылки

    public Parcel(String description, int weight, String deliveryAddress, int sendDay, int cost) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
        this.cost = cost;
    }

    //метод упаковки для всех посылок
    public void packageItem() {
        System.out.println("Посылка \u001B[1m" + description + "\u001B[0m упакована");
    }

    //метод доставки посылки
    public void deliver() {
        System.out.println("Посылка \u001B[1m" + description +
                "\u001B[0m доставлена по адресу: \u001B[1m" + deliveryAddress + "\u001B[0m");
    }

    //метод вычисления стоимости посылки
    public int calculateDeliveryCost() {
        int deliveryCost = weight * cost;
        return deliveryCost;
    }

    public int getCost() {
        return cost;
    }

    public int getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }
}
