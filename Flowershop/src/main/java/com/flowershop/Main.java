package com.flowershop;

import com.flowershop.logging.MailHandler;
import com.flowershop.menu.Menu;
import java.util.logging.*;

public class Main {

    // Ініціалізація логера для Main класу
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            // Налаштування логера для запису у файл
            FileHandler fileHandler = new FileHandler("flowershop.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Додавання MailHandler для відправки критичних помилок на email
            MailHandler mailHandler = new MailHandler();
            logger.addHandler(mailHandler);

            // Запуск програми журналу
            logger.info("Програма запущена");

            // Створення об'єкту меню та запуск його
            Menu menu = new Menu();
            menu.run();

            // Логування, що програма завершила роботу
            logger.info("Програму завершено");

        } catch (Exception e) {
            // Логування помилок на рівні SEVERE
            logger.log(Level.SEVERE, "У додатку сталася неочікувана помилка: " + e.getMessage(), e);
        }
    }
}