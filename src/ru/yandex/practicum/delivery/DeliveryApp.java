package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.parcel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackablesParcels = new ArrayList<>();

    private static ParcelBox smallBox = new ParcelBox<>(5);
    private static ParcelBox mediumBox = new ParcelBox<>(10);
    private static ParcelBox largeBox = new ParcelBox<>(20);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    updateTrackingStatus();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    System.out.println("Работа программы завершена");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Обновить местоположение всех посылок, поддерживающих трекинг");
        System.out.println("5 - Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Типы посылок: 1.Стандартная, 2.Хрупкая, 3.Скоропортящаяся");
        System.out.print("Введите тип посылки (1, 2 или 3): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите краткое описание посылки: ");
        String description = scanner.nextLine();

        System.out.print("Введите вес : ");
        int weight = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите адрес доставки: ");
        String deliveryAddress = scanner.nextLine();

        System.out.print("Введите день отправки посылки: ");
        int sendDay = scanner.nextInt();
        scanner.nextLine();

        Parcel parcel = null;

        switch (type) {
            case 1:
                parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                break;
            case 2:
                parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                trackablesParcels.add((Trackable) parcel);
            case 3:
                System.out.print("Введите срок годности (дни): ");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();
                parcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                break;
            default:
                System.out.println("Неверный тип посылки.");
                return;
        }
        if (parcel != null) {
            allParcels.add(parcel);
        }
        // Добавляем посылку в подходящую коробку по весу
        if (weight <= 5) {
            smallBox.addParcel(parcel);
        } else if (weight <= 10) {
            mediumBox.addParcel(parcel);
        } else {
            largeBox.addParcel(parcel);
        }
    }

    //метод обновления местоположения посылки
    private static void updateTrackingStatus() {
        System.out.print("Введите новое местоположение: ");
        String newLocation = scanner.nextLine();
        for (Trackable parcel : trackablesParcels) {
            if (parcel instanceof FragileParcel) {
                parcel.reportStatus(newLocation);
            } else {
                System.out.println("К данному виду посылки трекинг еще не предусмотрен");
            }
        }
    }

    //метод оправки всех посылок
    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            if (parcel instanceof PerishableParcel) {
                PerishableParcel perishableParcel = (PerishableParcel) parcel;
                System.out.println("Введите предполагаемое число доставки посылки: ");
                int currentDay = scanner.nextInt();
                scanner.nextLine();
                if (perishableParcel.isExpired(currentDay)) {
                    System.out.println("Посылка \u001B[1m" + perishableParcel.getDescription() +
                            "\u001B[0m будет испорчена.");
                } else {
                    parcel.deliver();
                }
            } else {
                parcel.deliver();
            }
        }
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для отправки!");
        }
    }

    //метод подсчета всех доставок и вывод на экран
    private static void calculateCosts() {
        int allPrice = 0;
        for (Parcel parcel : allParcels) {
            allPrice += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок: " + allPrice + " рублей.");
    }

    //метод вывод посылок в определенном типе коробки
    private static void showBoxContents() {
        System.out.print("Введите тип коробки (S - small, M - medium, L - large): ");
        String boxType = scanner.nextLine().toUpperCase();

        ParcelBox<?> selectedBox = null;

        // определяем, какая коробка соответствует введённому типу
        if (boxType.equals("S")) {
            selectedBox = smallBox;
        } else if (boxType.equals("M")) {
            selectedBox = mediumBox;
        } else if (boxType.equals("L")) {
            selectedBox = largeBox;
        } else {
            System.out.println("Неизвестный тип коробки.");
            return;
        }
        selectedBox.getAllParcels();
    }
}

