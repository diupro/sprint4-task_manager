package org.diupro;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        autoEddTasks(taskManager); // автозаполнение задач в список для удобства и экономии времени
        while (true) {

            printMenu();

            int command = scanner.nextInt();
            if (command == 0) {
                break;
            } else if (command == 1) {
                menuAddTasks(taskManager); // Добавить задачу
            } else if (command == 2) {
                menuChangeTasks(taskManager); // Изменить задачу
            } else if (command == 3) {
                menuDelTasks(taskManager); // Удалить задачу
            } else if (command == 4) {
                taskManager.printAllTasks(); // Вывести список всех задач
            } else if (command == 5) {
                taskManager.clearAllTasks(); // Удалить все задачи
            } else {
                System.out.println("Не известная команда");
            }
        }
    }

    private static void menuAddTasks(TaskManager taskManager) {

        while (true) {

            System.out.println("Выберите вид задачи: 1 - Задача, 2 - Эпик, 3 - Подзадача, 4 - Вывести список задач, 5 - Удалить все задачи, 0 - Выход");

            int command = scanner.nextInt();
            if (command == 0) {
                break;
            } else if (command == 1) { // Создать задачу
                System.out.println("Введите название задачи");
                String name = scanner.next();

                System.out.println("Введите описание задачи");
                String description = scanner.next();

                taskManager.addTask(name, description);

            } else if (command == 2) { // создать эпик
                System.out.println("Введите название Эпика");
                String name = scanner.next();

                System.out.println("Введите описание Эпика");
                String description = scanner.next();

                taskManager.addEpic(name, description);

            } else if (command == 3) { // создать подзадачу
                System.out.println("Введите название подзадачи");
                String name = scanner.next();

                System.out.println("Введите описание подзадачи");
                String description = scanner.next();

                System.out.println("Введите номер Эпика для подзадачи");
                int epic_id = scanner.nextInt();

                taskManager.addSubTask(name, description, epic_id);

            } else if (command == 4) {
                taskManager.printTasks(); // вывести список всех задач
            } else if (command == 5) {
                taskManager.clearAllTasks(); // удалить все задачи
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }

    private static void menuChangeTasks(TaskManager taskManager) {

        while (true) {
            System.out.println("Выберите вид задачи для изменения: 1 - Задача, 2 - Эпик, 3 - Подзадача, 0 - Выход");

            int command = scanner.nextInt();
            if (command == 0) {
                break;
            } else if (command == 1) {
                // Задача
                System.out.println("Введите номер задачи для изменения");
                int numInput = scanner.nextInt();

                System.out.println("Введен новое описание задачи");
                String newDescription = scanner.next();

                System.out.println("Введен новый статус задачи: NEW, IN_PROGRESS, DONE");
                String newStatus = scanner.next().toUpperCase(); // Строка в ВЕРХНЕМ РЕГИСТРЕ до первого пробела

                taskManager.changeTask(numInput, newDescription, newStatus); // изменить задачу

            } else if (command == 2) {
                // Эпик
                System.out.println("Введите номер Эпика для изменения");
                int numInput = scanner.nextInt();

                System.out.println("Введен новое описание Эпика");
                String newDescription = scanner.next();

                taskManager.changeEpic(numInput, newDescription);

            } else if (command == 3) {
                // Подзадача
                System.out.println("Введите номер подзадачи для изменения");
                int numInput = scanner.nextInt();

                System.out.println("Введен новое описание подзадачи");
                String newDescription = scanner.next();

                System.out.println("Введен новый статус подзадачи: NEW, IN_PROGRESS, DONE");
                String newStatus = scanner.next().toUpperCase(); // Строка в ВЕРХНЕМ РЕГИСТРЕ до первого пробела

                taskManager.changeSubTask(numInput, newDescription, newStatus);

            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }

    private static void menuDelTasks(TaskManager taskManager) {
        while (true) {
            System.out.println("Выберите вид задачи для удаления: 1 - Задача, 2 - Эпик, 3 - Подзадача, 0 - Выход");

            int command = scanner.nextInt();
            if (command == 0) {
                break;
            } else if (command == 1) {
                // Задача
                System.out.println("Введите номер задачи для удаления");
                int numInput = scanner.nextInt();
                taskManager.deleteTask(numInput);

            } else if (command == 2) {
                // Эпик
                System.out.println("Введите номер Эпика для удаления");
                int numInput = scanner.nextInt();
                taskManager.deleteEpic(numInput);

            } else if (command == 3) {
                // Подзадача
                System.out.println("Введите номер подзадачи для удаления");
                int numInput = scanner.nextInt();
                taskManager.deleteSubTask(numInput);

            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }

    private static void autoEddTasks(TaskManager taskManager) {

        taskManager.addTask("Задача1", "Описание задачи1");
        taskManager.addTask("Задача2", "Описание задачи2");
        taskManager.addTask("Задача3", "Описание задачи3");

        taskManager.addEpic("Эпик1", "Описание эпика1");
        taskManager.addEpic("Эпик2", "Описание эпика2"); // 5
        taskManager.addEpic("Эпик3", "Описание эпика3"); // 6

        taskManager.addSubTask("Подзадача1", "Описание подзадачи1", 5); // 7
        taskManager.addSubTask("Подзадача2", "Описание подзадачи2", 5); // 8

        taskManager.addSubTask("Подзадача3", "Описание подзадачи3", 6); // 8
        taskManager.addSubTask("Подзадача4", "Описание подзадачи4", 6); // 8

    }

    private static void printMenu() {
        System.out.println("Какую операцию хотите ввести?");
        System.out.println("1 - Добавить задачу");
        System.out.println("2 - Изменить задачу");
        System.out.println("3 - Удалить задачу");
        System.out.println("4 - Вывести список всех задач");
        System.out.println("5 - Удалить все задачи");
        System.out.println("0 - Выход");
    }
}
