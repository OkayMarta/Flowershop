package com.flowershop.menu;

import com.flowershop.manager.BouquetManager;
import com.flowershop.menu.commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    BouquetManager bouquetManager;
    Scanner scanner;
    List<MenuItem> currentMenu; // Зберігаємо поточне меню
    List<MenuItem> mainMenu; // Зберігаємо головне меню для повернення

    public Menu() {
        bouquetManager = new BouquetManager();
        scanner = new Scanner(System.in);
    }

    // Метод для запуску меню
    public void run() {
        // Ініціалізуємо головне меню
        mainMenu = createMainMenu();
        currentMenu = mainMenu;

        while (true) {
            showMenu(); // Відображаємо поточне меню
            int choice = getChoice(); // Отримуємо вибір користувача
            handleChoice(choice); // Обробляємо вибір користувача

            // Перевіряємо, чи потрібно вийти з програми
            if (choice == mainMenu.size() && currentMenu == mainMenu) {
                // Якщо вибрано останній пункт головного меню ("Вихід")
                break; // Виходимо з циклу
            }
        }
    }

    // Створення головного меню
    List<MenuItem> createMainMenu() {
        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("Створити/редагувати букет", createBouquetEditSubMenu()));
        menu.add(new MenuItem("Робота з файлом", createFileSubMenu()));
        menu.add(new MenuItem("Вивести інформацію", createInfoSubMenu()));
        menu.add(new MenuItem("Вихід", new ExitCommand()));
        return menu;
    }

    // Створення підменю "Створити/редагувати букет"
    List<MenuItem> createBouquetEditSubMenu() {
        List<MenuItem> subMenu = new ArrayList<>();
        subMenu.add(new MenuItem("Додати квітку", createAddFlowerSubMenu()));
        subMenu.add(new MenuItem("Додати аксесуар", createAddAccessorySubMenu()));
        subMenu.add(new MenuItem("Видалити квітку", new RemoveFlowerCommand(bouquetManager)));
        subMenu.add(new MenuItem("Видалити аксесуар", new RemoveAccessoryCommand(bouquetManager)));
        subMenu.add(new MenuItem("Сортувати квіти за свіжістю", new SortFlowersCommand(bouquetManager)));
        subMenu.add(new MenuItem("Повернутися до головного меню", new ReturnToMainMenuCommand(this)));
        return subMenu;
    }

    // Створення підменю "Додати квітку"
    private List<MenuItem> createAddFlowerSubMenu() {
        List<MenuItem> subMenu = new ArrayList<>();
        subMenu.add(new MenuItem("Троянда", new AddRoseCommand(bouquetManager)));
        subMenu.add(new MenuItem("Лілія", new AddLilyCommand(bouquetManager)));
        subMenu.add(new MenuItem("Орхідея", new AddOrchidCommand(bouquetManager)));
        subMenu.add(new MenuItem("Гортензія", new AddHydrangeaCommand(bouquetManager)));
        subMenu.add(new MenuItem("Повернутися до попереднього меню", new ReturnToPreviousMenuCommand(this)));
        return subMenu;
    }

    // Створення підменю "Додати аксесуар"
    private List<MenuItem> createAddAccessorySubMenu() {
        List<MenuItem> subMenu = new ArrayList<>();
        subMenu.add(new MenuItem("Обгортка", new AddWrapperCommand(bouquetManager)));
        subMenu.add(new MenuItem("Кошик", new AddBasketCommand(bouquetManager)));
        subMenu.add(new MenuItem("Стрічка", new AddRibbonCommand(bouquetManager)));
        subMenu.add(new MenuItem("Листівка", new AddPostcardCommand(bouquetManager)));
        subMenu.add(new MenuItem("Повернутися до попереднього меню", new ReturnToPreviousMenuCommand(this))); // Додано команду повернення
        return subMenu;
    }

    // Створення підменю "Робота з файлом"
    private List<MenuItem> createFileSubMenu() {
        List<MenuItem> subMenu = new ArrayList<>();
        subMenu.add(new MenuItem("Зберегти букет у файл", new SaveBouquetCommand(bouquetManager)));
        subMenu.add(new MenuItem("Завантажити букет з файлу", new LoadBouquetCommand(bouquetManager)));
        subMenu.add(new MenuItem("Повернутися до головного меню", new ReturnToMainMenuCommand(this)));
        return subMenu;
    }

    // Створення підменю "Вивести інформацію"
    private List<MenuItem> createInfoSubMenu() {
        List<MenuItem> subMenu = new ArrayList<>();
        subMenu.add(new MenuItem("Вивести інформацію про букет", new PrintBouquetInfoCommand(bouquetManager)));
        subMenu.add(new MenuItem("Знайти квіти за довжиною стебла", new FindFlowersCommand(bouquetManager)));
        subMenu.add(new MenuItem("Повернутися до головного меню", new ReturnToMainMenuCommand(this)));
        return subMenu;
    }

    // Метод для відображення меню
    private void showMenu() {
        System.out.println("\nМеню:");
        for (int i = 0; i < currentMenu.size(); i++) {
            System.out.println((i + 1) + ". " + currentMenu.get(i).getTitle());
        }
    }

    // Метод для отримання вибору користувача
    int getChoice() {
        System.out.print("Ваш вибір: ");
        do { // Використання циклу do-while
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Очищення буфера вводу
                return choice;
            } else {
                System.out.println("Невірний формат вводу. Введіть число:");
                scanner.next(); // Очищення буфера вводу
            }
        } while (true); // Цикл повторюється до тих пір, поки не буде введено число
    }

    // Метод для обробки вибору користувача
    void handleChoice(int choice) {
        if (choice > 0 && choice <= currentMenu.size()) {
            MenuItem selectedItem = currentMenu.get(choice - 1);

            if (selectedItem.getSubMenu() != null) {
                previousMenu = currentMenu; // Зберігаємо поточне меню як попереднє
                currentMenu = selectedItem.getSubMenu(); // Перехід до підменю
            } else {
                selectedItem.executeCommand(); // Виконання команди
            }
        } else {
            System.out.println("Невірний вибір. Спробуйте ще раз.");
        }
    }

    // Метод для повернення до головного меню
    public void returnToMainMenu() {
        currentMenu = mainMenu;
    }

    List<MenuItem> previousMenu; // Змінна для збереження попереднього меню

    // Метод для повернення до попереднього меню
    public void returnToPreviousMenu() {
        currentMenu = previousMenu;
    }
}