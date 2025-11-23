package ru.yandex.practicum.delivery.parcel;

import java.util.ArrayList;
import java.util.HashMap;

                        //КОРОБКА С ПОСЫЛКАМИ

public class ParcelBox<T extends Parcel> {
    private HashMap<String, ArrayList<T>> box = new HashMap<>();
    private int maxWeightBox; //максимальный вес посылки
    private String size; //тип посылки

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
        int currentTotalWeight = 0; // изначальный вес всех посылок в коробке

        for (T aParcel : parcelsList) {
            currentTotalWeight += aParcel.getWeight(); //вес коробки после добавления посылки
        }

        if (currentTotalWeight + parcel.getWeight() <= maxWeightBox) {
            parcelsList.add(parcel);
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












//МОЙ КОД
//    public void getAllParcels() {
//        for (String size : box.keySet()) {
//            System.out.println("В коробке размером " + size + " размещены следующие посылки:");
//            ArrayList<T> parcels = box.get(size);
//            for (T parcel : parcels) {
//                System.out.println("- " + parcel.description + " (вес: " + parcel.weight + " кг)");
//            }
//        }
//    }


// МОЙ КОД
//    //метод добавления посылки в коробку
//    public void addParcel(ArrayList<? extends T> parcel) {
//        box.put(size, parcel);
//    }
//
//    //метод получения всех посылок из коробки
//    public void getAllParcels(){
//        for (String size: box.keySet()) {
//            System.out.println("В коробке размером " + size + " размещены следующие посылки: ");
//            ArrayList<T> parcels = box.get(size);
//            for (T parcel: parcels) {
//                System.out.println(parcel);
//            }
//        }
//    }

//    private static void addParcel(Parcel parcel) {
//        if (parcel.getWeight() <= smallBox.maxWaightBox) {
//            smallBox.addParcel(ArrayList.of(parcel));
//        } else if (parcel.getWeight() <= mediumBox.maxWaightBox) {
//            mediumBox.addParcel(ArrayList.of(parcel));
//        } else if (parcel.getWeight() <= extraBox.maxWaightBox) {
//            extraBox.addParcel(ArrayList.of(parcel));
//        } else {
//            System.out.println("Посылка слишком тяжёлая для доступных коробок.");
//        }
//    }
}
