import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Epic {

    String epicName;
    String epicShortDescription;
    String epicStatusNew = "NEW";
    String epicStatusInProgress = "IN_PROGRESS";
    String epicStatusDone = "DONE";

    Scanner scanner;
    Subtask subtask;
    ArrayList<String> epicInfo;
    HashMap<Integer, ArrayList<String>> epic;

    public Epic(Subtask subtask) {

        scanner = new Scanner(System.in);
        this.subtask = subtask;
        epic = new HashMap<>();
    }

    public void createEpic(int taskKey) {

        epicInfo = new ArrayList<>();
        System.out.println("Введите название глобальной задачи:");
        epicName = scanner.nextLine().trim();
        epicInfo.add(epicName);
        System.out.println("Введите краткое описание глобальной задачи:");
        epicShortDescription = scanner.nextLine().trim();
        epicInfo.add(epicShortDescription);
        epicInfo.add(epicStatusNew);
        epic.put(taskKey, epicInfo);
        subtask.createSubtask(taskKey);
    }

    public void clearEpicAll() {

        try {
            if (epic.isEmpty()) {
                System.out.println("Список глобальных задач пуст" + '\n');
            } else {
                epic.clear();
                System.out.println("Список глобальных задач пуст" + '\n');
            }
        } catch (NullPointerException e) {
            System.out.println("Список глобальных задач пуст" + '\n');
        }
    }

    public void printEpicAll() {

        try {
            if (epic.isEmpty()) {
                System.out.println("Список глобальных задач пуст" + '\n');
            } else {
                for (Integer i : epic.keySet()) {
                    System.out.println("Название глобальной задачи: " + epic.get(i).get(0)
                            + ". Краткое описание глобальной задачи: " + epic.get(i).get(1)
                            + ". Глобальная задача имеет идентификатор " + i + " и статус " + epic.get(i).get(2));
                }
                System.out.print('\n');
            }

        } catch (NullPointerException e) {
            System.out.println("Список глобальных задач пуст" + '\n');
        }
    }

    public String findEpicByKey(int key) {

        if (epic.containsKey(key)) {
            return epic.get(key).get(0);
        }
        else return null;
    }

    public void removeEpicByKey(int key) {

        epic.remove(key);
        subtask.removeSubtaskByEpic(key);
    }

    public void updateNameOfEpic(int epicKey) {

        System.out.println("Введите новое название задачи");
        String newName = scanner.nextLine().trim();
        epic.get(epicKey).remove(0);
        epic.get(epicKey).add(0, newName);
        System.out.println("Название задачи обновлено" + '\n');
    }

    public void updateShortDescriptionOfEpic(int epicKey) {

        System.out.println("Введите новое короткое описание задачи");
        String newShortDescription = scanner.nextLine().trim();
        epic.get(epicKey).remove(1);
        epic.get(epicKey).add(1, newShortDescription);
        System.out.println("Короткое описание задачи обновлено" + '\n');
    }

    public void updateStatusOfEpic(int counterStatusFull, int counterStatusNew, int counterStatusInProgress,
                                   int counterStatusDone, int epicKey) {

        if (counterStatusNew != counterStatusFull && epic.get(epicKey).contains(epicStatusNew)) {
            epic.get(epicKey).remove(2);
            epic.get(epicKey).add(2, epicStatusInProgress);
            System.out.println("Статус глобальной задачи обновлён" + '\n');
        } else if (counterStatusDone == counterStatusFull && epic.get(epicKey).contains(epicStatusInProgress)) {
            epic.get(epicKey).remove(2);
            epic.get(epicKey).add(2, epicStatusDone);
            System.out.println("Статус глобальной задачи обновлён" + '\n');
        } else if (counterStatusDone == counterStatusFull && epic.get(epicKey).contains(epicStatusDone)) {
            System.out.println("Глобальная задача уже завершена" + '\n');
        }
    }
}
