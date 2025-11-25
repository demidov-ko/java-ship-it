package ru.yandex.practicum.delivery.parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//КОРОБКА С ПОСЫЛКАМИ

public class ParcelBox<T extends Parcel> {
    private Map<String, ArrayList<T>> box = new HashMap<>();
    private int maxWeightBox; //максимальный вес посылки
    private String size; //тип посылки
    private int currentWeight = 0; //текущий вес всех посылок

    public ParcelBox(int maxWeightBox) {
        this.maxWeightBox = maxWeightBox;

        if (maxWeightBox <= 5) {
            this.size = "Small";
        } else if (maxWeightBox <= 10) {
            this.size = "Medium";
        } else if (maxWeightBox <= 20 && maxWeightBox > 10) {
            this.size = "Large";
        }
        box.put(size, new ArrayList<>());
    }

    //метод добавления новой посылки
    public boolean addParcel(T parcel) {
        ArrayList<T> parcelsList = box.get(size);

        if (currentWeight + parcel.getWeight() <= maxWeightBox) {
            parcelsList.add(parcel);
            currentWeight += parcel.getWeight();
            System.out.println("Посылка \u001B[1m" + parcel.description +
                    "\u001B[0m добавлена в коробку \u001B[1m" + size + "\u001B[0m");
            return true;
        } else {
            System.out.println("Недостаточно места в коробке \u001B[1m" + size +
                    "\u001B[0m для посылки \u001B[1m" + parcel.description + "\u001B[0m");
            return false;
        }
    }

    //метод печати всех посылок
    public void getAllParcels() {
        for (String size : box.keySet()) {
            ArrayList<T> parcels = box.get(size);
            if (parcels.isEmpty()) {
                System.out.println("В коробке размером \u001B[1m" + size + "\u001B[0m нет посылок.");
            } else {
                System.out.println("В коробке размером \u001B[1m" + size + "\u001B[0m размещены следующие посылки:");
                for (T parcel : parcels) {
                    System.out.println("- \u001B[1m" + parcel.description +
                            "\u001B[0m (вес: " + parcel.weight + " кг, стоимость: " +
                            parcel.calculateDeliveryCost() + " руб.)");
                }
            }
        }
    }
}
