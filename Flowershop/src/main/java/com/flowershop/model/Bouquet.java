package com.flowershop.model;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bouquet {
    private List<Flower> flowers;
    private List<Accessory> accessories;
    private LocalDateTime creationDate;

    // Конструктор за замовчуванням
    public Bouquet() {
        this.flowers = new ArrayList<>();
        this.accessories = new ArrayList<>();
        this.creationDate = LocalDateTime.now();
    }

    //  Метод для отримання списку аксесуарів
    public List<Accessory> getAccessories() {
        return accessories;
    }

    // Метод для додавання квітів до букету
    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    // Метод для додавання аксесуару до букету
    public void addAccessory(Accessory accessory) {
        // Перевіряємо, чи є вже аксесуар такого типу в букеті
        if (accessories.stream().anyMatch(a -> a.getClass() == accessory.getClass())) {
            System.out.println("Аксесуар такого типу вже додано до букету.");
            return;
        }

        accessories.add(accessory);
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    // Метод для отримання інформації про букет
    public String getBouquetInfo() {
        if (flowers.isEmpty() && accessories.isEmpty()) {
            return "Букету не створено.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Інформація про букет:\n");
        sb.append("Квіти:\n");
        for (Flower flower : flowers) {
            sb.append("- ").append(flower.getName()).append(", ")
                    .append(flower.getColor()).append(", ")
                    .append("свіжість: ").append(flower.getFreshnessLevel()).append(", ")
                    .append("довжина стебла: ").append(flower.getStemLength()).append(" см, ")
                    .append("розмір квітки: ").append(flower.getFlowerSize()).append(" см, ")
                    .append("ціна: ").append(flower.getPrice()).append(" грн, ")
                    .append("країна походження: ").append(flower.getOriginCountry()).append(", ")
                    .append(flower.getUniqueCharacteristic()).append("\n");
        }
        sb.append("Аксесуари:\n");
        for (Accessory accessory : accessories) {
            String className = accessory.getClass().getSimpleName();

            className = switch (className) {
                case "Wrapper" -> "Обгортка";
                case "Basket" -> "Кошик";
                case "Ribbon" -> "Стрічка";
                case "Postcard" -> "Листівка";
                default -> className;
            };

            sb.append("- ").append(className).append(", ")
                    .append("матеріал: ").append(accessory.getMaterial()).append(", ")
                    .append("колір: ").append(accessory.getColor()).append(", ")
                    .append("ціна: ").append(accessory.getPrice()).append(" грн, ")
                    .append(accessory.getUniqueCharacteristic()).append("\n");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        sb.append("Дата створення: ").append(creationDate.format(formatter)).append("\n");
        sb.append("Загальна вартість: ").append(calculateTotalPrice()).append(" грн\n");
        return sb.toString();
    }

    // Метод для перевірки, чи є букет порожнім
    public boolean isEmpty() {
        return flowers.isEmpty() && accessories.isEmpty();
    }

    // Метод для видалення квітів з букету
    public void removeFlower(Flower flower) {
        flowers.remove(flower);
    }

    // Метод для видалення аксесуарів з букету
    public void removeAccessory(Accessory accessory) {
        accessories.remove(accessory);
    }

    // Метод для сортування квітів за свіжістю (від найсвіжішого до найменш свіжого)
    public void sortFlowersByFreshness() {
        flowers.sort((flower1, flower2) -> Integer.compare(flower2.getFreshnessLevel(), flower1.getFreshnessLevel())); //лямбда-вираз для порівняння квітів за свіжістю
    }

    // Метод для пошуку квітів за довжиною стебла
    public List<Flower> findFlowersByStemLength(double minLength, double maxLength) {
        List<Flower> foundFlowers = new ArrayList<>();
        for (Flower flower : flowers) {
            if (flower.getStemLength() >= minLength && flower.getStemLength() <= maxLength) {
                foundFlowers.add(flower);
            }
        }
        return foundFlowers;
    }

    // Метод для обчислення загальної вартості букету
    public double calculateTotalPrice() {
        double totalPrice = 0;

        for (Flower flower : flowers) {
            totalPrice += flower.getPrice();
        }

        for (Accessory accessory : accessories) {
            totalPrice += accessory.getPrice();
        }

        return totalPrice;
    }

    // Метод для отримання дати створення букету
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    // Метод для перевірки наявності аксесуару заданого типу
    public boolean hasAccessoryOfType(Class<? extends Accessory> accessoryClass) {
        return accessories.stream().anyMatch(a -> a.getClass() == accessoryClass);
    }

    // Метод для видалення аксесуару заданого типу
    public void removeAccessoryOfType(Class<? extends Accessory> accessoryClass) {
        accessories.removeIf(a -> a.getClass() == accessoryClass);
    }
}