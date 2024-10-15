import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Task {

    String taskName;
    String taskShortDescription;
    String taskStatusNew = "NEW";
    String taskStatusInProgress = "IN_PROGRESS";
    String taskStatusDone = "DONE";

    Scanner scanner;
    ArrayList<String> taskInfo;
    ArrayList<String> taskHistory;
    HashMap<Integer, ArrayList<String>> task;

    public Task() {
        scanner = new Scanner(System.in);
        task = new HashMap<>();
        taskHistory = new ArrayList<>();
    }

    public void createTask(int taskKey) {
        taskInfo = new ArrayList<>();
        System.out.println("Введите название задачи:");
        taskName = scanner.nextLine().trim();
        taskInfo.add(taskName);
        System.out.println("Введите краткое описание задачи:");
        taskShortDescription = scanner.nextLine().trim();
        taskInfo.add(taskShortDescription);
        taskInfo.add(taskStatusNew);
        task.put(taskKey, taskInfo);
    }

    public void clearTaskAll() {
        if (task.isEmpty()) {
            System.out.println("Список стандартных задач пуст" + '\n');
        } else {
            task.clear();
            System.out.println("Список стандартных задач очищен" + '\n');
        }
    }

    public void printTaskAll() {
        try {
            if (task.isEmpty()) {
                System.out.println("Список стандартных задач пуст" + '\n');
            } else {
                for (Integer i : task.keySet()) {
                    System.out.println("Название стандартной задачи: " + task.get(i).get(0)
                            + ". Краткое описание стандартной задачи: " + task.get(i).get(1)
                            + ". Стандартная задача имеет идентификатор " + i + " и статус " + task.get(i).get(2));
                }
                System.out.print('\n');
            }
        } catch (NullPointerException e) {
            System.out.println("Список стандартных задач пуст" + '\n');
        }
    }

    public String findTaskByKey(int key) {
        if (task.containsKey(key)) {
            return task.get(key).get(0);
        }
        else return null;
    }

    public void removeTaskByKey(int key) {
        task.remove(key);
    }

    public void updateNameOfTask(int taskKey) {
        System.out.println("Введите новое название задачи");
        String newName = scanner.nextLine().trim();
        task.get(taskKey).remove(0);
        task.get(taskKey).add(0, newName);
        System.out.println("Название задачи обновлено" + '\n');
    }

    public void updateShortDescriptionOfTask(int taskKey) {
        System.out.println("Введите новое короткое описание задачи");
        String newShortDescription = scanner.nextLine().trim();
        task.get(taskKey).remove(1);
        task.get(taskKey).add(1, newShortDescription);
        System.out.println("Короткое описание задачи обновлено" + '\n');
    }

    public void updateStatusOfTask(int taskKey) {
        if (task.get(taskKey).contains(taskStatusNew)) {
            task.get(taskKey).remove(2);
            task.get(taskKey).add(2, taskStatusInProgress);
            System.out.println("Статус задачи обновлён" + '\n');
        } else if (task.get(taskKey).contains(taskStatusInProgress)) {
            task.get(taskKey).remove(2);
            task.get(taskKey).add(2, taskStatusDone);
            System.out.println("Статус задачи обновлён" + '\n');
        } else if (task.get(taskKey).contains(taskStatusDone)) {
            System.out.println("Задача уже завершена" + '\n');
        }
    }

    public void fillHistory() {
        if (taskHistory.size() < 10) {
            taskHistory.add("zero");
        } else if (taskHistory.size() == 10) {
            taskHistory.remove(0);
            taskHistory.add("zero");
        }
    }

    public void fillHistory(int key) {
        if (taskHistory.size() < 10) {
            taskHistory.add(task.get(key).get(0));
        } else if (taskHistory.size() == 10) {
            taskHistory.remove(0);
            taskHistory.add(task.get(key).get(0));
        }
    }
}
