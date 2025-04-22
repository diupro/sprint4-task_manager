package org.diupro;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static int id = 0;
    private final HashMap<Integer, Task> hmTasks;
    private final HashMap<Integer, Epic> hmEpics;
    private final HashMap<Integer, SubTask> hmSubTasks;

    public TaskManager() {
        this.hmTasks = new HashMap<>();
        this.hmEpics = new HashMap<>();
        this.hmSubTasks = new HashMap<>();
    }

    public static int getId() {
        return ++id;
    }

    public void addTask(String name, String description) {
        Task task = new Task(name, description);
        this.hmTasks.put(task.getId(), task);
    }

    public void addEpic(String name, String description) {
        Epic epic = new Epic(name, description);
        this.hmEpics.put(epic.getId(), epic);
    }

    public void addSubTask(String name, String description, int epic_id) {
        SubTask subTask = new SubTask(name, description, epic_id);
        this.hmSubTasks.put(subTask.getId(), subTask);
    }

    public void changeTask(int num, String newDescription, String newStatus) {
        if (num == 0) return;
        if (newStatus.isEmpty()) return;
        if (this.hmTasks.containsKey(num)) {
            Task task = this.hmTasks.get(num);
            task.setDescription(newDescription);
            task.setStatus(findStatus(newStatus));
            this.hmTasks.put(num, task);
            System.out.println("Задача изменена: " + task); // .toString());
        }
    }

    public void changeEpic(int num, String newDescription) {
        if (num == 0) return;
        if (this.hmEpics.containsKey(num)) {
            Epic epic = this.hmEpics.get(num);
            epic.setDescription(newDescription);
            epic.setStatus(defineEpicStatus(epic));
            this.hmEpics.put(num, epic);
            System.out.println("Эпик изменен: " + epic); // .toString());
        }
    }

    public void changeSubTask(int num, String newDescription, String newStatus) {
        if (num == 0) return;
        if (newStatus.isEmpty()) return;
        if (this.hmSubTasks.containsKey(num)) {
            SubTask subTask = this.hmSubTasks.get(num);
            subTask.setDescription(newDescription);
            subTask.setStatus(findStatus(newStatus));
            this.hmSubTasks.put(num, subTask);
            System.out.println("Подзадача изменена: " + subTask); // .toString());

            // изменить статус эпика
            Epic epic = this.hmEpics.get(subTask.getEpic_id());
            changeEpic(epic.getId(), epic.getDescription());
        }
    }

    public Statuses defineEpicStatus(Epic epic) {
        boolean isNew = false;
        boolean isProgress = false;
        boolean isFined = false;

        // проверить все подзадачи на статусы
        if (this.hmSubTasks.isEmpty()) return Statuses.NEW;
        for (SubTask subTask : hmSubTasks.values()) {
            if (epic.getId() == subTask.getEpic_id()) {
                isFined = true;
                if (subTask.getStatus() == Statuses.NEW) {
                    isNew = true;
                    break;
                } else if (subTask.getStatus() == Statuses.IN_PROGRESS) {
                    isProgress = true;
                }
            }
        }
        if (!isFined) { return Statuses.NEW; }

        if (isNew) {
            return Statuses.NEW;
        } else if (isProgress) {
            return Statuses.IN_PROGRESS;
        } else {
            return Statuses.DONE;
        }
    }

    public void deleteTask(int num) {
        if (num == 0) return;
        if (this.hmTasks.containsKey(num)) {
            this.hmTasks.remove(num);
            System.out.println("Задача удалена");
        } else {
            System.out.println("Не найдена Задача " + num);
        }
    }

    public void deleteEpic(int num) {
        if (num == 0) return;
        ArrayList<Integer> arrSubTasksForDel = new ArrayList<>();
        if (this.hmEpics.containsKey(num)) {
            for (SubTask subTask : hmSubTasks.values()) {
                if (num == subTask.getEpic_id()) {
                    arrSubTasksForDel.add(subTask.getId());
                }
            }
        } else {
            System.out.println("Не найден Эпик " + num);
        }
        for (Integer index : arrSubTasksForDel) {
            this.hmSubTasks.remove(index);
        }
        this.hmEpics.remove(num);
    }

    public void deleteSubTask(int num) {
        if (num == 0) return;
        if (this.hmSubTasks.containsKey(num)) {
            SubTask subTask = this.hmSubTasks.get(num);
            Epic epic = this.hmEpics.get(subTask.getEpic_id());

            this.hmSubTasks.remove(num); // удалить подзадачу
            System.out.println("Подзадача удалена");

            changeEpic(epic.getId(), epic.getDescription()); // изменить статус эпика

        } else {
            System.out.println("Не найдена Подзадача " + num);
        }
    }

    public static Statuses findStatus(String newStatus) {
        return switch (newStatus) {
            case "NEW" -> Statuses.NEW;
            case "IN_PROGRESS" -> Statuses.IN_PROGRESS;
            case "DONE" -> Statuses.DONE;
            default -> null;
        };
    }

    public void printTasks() {
        if (this.hmTasks.isEmpty()) {
            System.out.println("Список задач пустой");
            return;
        }
        for (Integer k : hmTasks.keySet()) {
            System.out.println(hmTasks.get(k).toString());
        }
    }

    public void printEpics() {
        if (this.hmEpics.isEmpty()) {
            System.out.println("Список эпиков пустой");
            return;
        }
        for (Integer k : hmEpics.keySet()) {
            Epic epic = this.hmEpics.get(k);
            System.out.println(epic.toString());

            if (!hmSubTasks.isEmpty()) {
                for (SubTask valueSubTask : hmSubTasks.values()) {
                    if (epic.getId() == valueSubTask.getEpic_id()) {
                        System.out.println(" -- " + valueSubTask); // .toString());
                    }
                }
            }
        }
    }

    public void printAllTasks() {
        printTasks();
        printEpics();
    }

    public void clearAllTasks() {
        this.hmTasks.clear();
        this.hmEpics.clear();
        this.hmSubTasks.clear();
        System.out.println("Список задач очищен");
    }

}
