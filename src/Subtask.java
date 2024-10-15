import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Subtask {

    String subtaskName;
    String subtaskShortDescription;
    String subtaskStatusNew = "NEW";
    String subtaskStatusInProgress = "IN_PROGRESS";
    String subtaskStatusDone = "DONE";
    String epicKey;
    int subtaskKey;

    Scanner scanner;
    ArrayList<String> subtaskInfo;
    ArrayList<String> subtaskHistory;
    HashMap<Integer, ArrayList<String>> subtask;
    HashMap<Integer, ArrayList<String>> subtaskClone;
    Epic epic;

    public Subtask() {
        scanner = new Scanner(System.in);
        subtask = new HashMap<>();
        subtaskClone = new HashMap<>();
        subtaskHistory = new ArrayList<>();
    }

    public void createSubtask(int epicKey) {
        this.epicKey = String.valueOf(epicKey);
        subtaskKey = epicKey;
        boolean isEnd;
        while (true) {
            subtaskKey++;
            subtaskInfo = new ArrayList<>();
            System.out.println("Введите название подзадачи:");
            subtaskName = scanner.nextLine().trim();
            subtaskInfo.add(subtaskName);
            System.out.println("Введите краткое описание подзадачи:");
            subtaskShortDescription = scanner.nextLine().trim();
            subtaskInfo.add(subtaskShortDescription);
            subtaskInfo.add(subtaskStatusNew);
            subtaskInfo.add(this.epicKey);
            subtask.put(subtaskKey, subtaskInfo);
            isEnd = endCreatingSubtask();
            if (isEnd)
                break;
        }
    }

    public boolean endCreatingSubtask() {
        boolean isEnd;
        while (true) {
            printMenu();
            try {
                int choosingKey = Integer.parseInt(scanner.nextLine().trim());
                if (choosingKey == 1) {
                    isEnd = false;
                    break;
                } else if (choosingKey == 2) {
                    isEnd = true;
                    break;
                } else throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Такой команды не существует");
            }
        }
        return isEnd;
    }

    public void clearSubtaskAll() {
        if (subtask.isEmpty()) {
            System.out.println("Список подзадач пуст" + '\n');
        } else {
            subtask.clear();
            System.out.println("Список подзадач очищен" + '\n');
        }
    }

    public void printSubtaskAll() {
        try {
            if (subtask.isEmpty()) {
                System.out.println("Список подзадач пуст" + '\n');
            } else {
                for (Integer i : subtask.keySet()) {
                    System.out.println("Название подзадачи: " + subtask.get(i).get(0) + ". Краткое описание подзадачи: "
                            + subtask.get(i).get(1) + ". Подзадача имеет идентификатор " + i + " и статус "
                            + subtask.get(i).get(2) + ". Подзадача относится к глобальной задаче с идентификатором "
                            + subtask.get(i).get(3));
                }
                System.out.print('\n');
            }
        } catch (NullPointerException e) {
            System.out.println("Список подзадач пуст" + '\n');
        }
    }

    public void printSubtaskByEpic(int epicKey) {
        String secondKey = String.valueOf(epicKey);
        for (Integer i : subtask.keySet()) {
            if (subtask.get(i).contains(secondKey)) {
                System.out.println("Название подзадачи: " + subtask.get(i).get(0) + ". Краткое описание подзадачи: "
                        + subtask.get(i).get(1) + ". Подзадача имеет идентификатор " + i + " и статус "
                        + subtask.get(i).get(2));
            }
        }
        System.out.print('\n');
    }

    public String findSubtaskByKey(int key) {
        if (subtask.containsKey(key)) {
            return subtask.get(key).get(0);
        }
        else return null;
    }

    public void removeSubtaskByKey(int key) {
        subtask.remove(key);
    }

    public void removeSubtaskByEpic(int epicKey) {
        subtaskClone.putAll(subtask);
        String secondKey = String.valueOf(epicKey);
        for (Integer i : subtask.keySet()) {
            if (subtask.get(i).contains(secondKey)) {
                subtaskClone.remove(i);
            }
        }
        subtask.clear();
        subtask.putAll(subtaskClone);
    }

    public void printMenu() {
        System.out.println("Есть ещё подзадачи?");
        System.out.println("Введите 1, если да");
        System.out.println("Введите 2, если нет");
    }

    public void updateNameOfSubtask(int subtaskKey) {
        System.out.println("Введите новое название подзадачи");
        String newName = scanner.nextLine().trim();
        subtask.get(subtaskKey).remove(0);
        subtask.get(subtaskKey).add(0, newName);
        System.out.println("Название подзадачи обновлено" + '\n');
    }

    public void updateShortDescriptionOfSubtask(int subtaskKey) {
        System.out.println("Введите новое короткое описание подзадачи");
        String newShortDescription = scanner.nextLine().trim();
        subtask.get(subtaskKey).remove(1);
        subtask.get(subtaskKey).add(1, newShortDescription);
        System.out.println("Короткое описание подзадачи обновлено" + '\n');
    }

    public void updateStatusOfSubtask(int subtaskKey, Epic epic) {
        if (subtask.get(subtaskKey).contains(subtaskStatusNew)) {
            subtask.get(subtaskKey).remove(2);
            subtask.get(subtaskKey).add(2, subtaskStatusInProgress);
            System.out.println("Статус подзадачи обновлён" + '\n');
        } else if (subtask.get(subtaskKey).contains(subtaskStatusInProgress)) {
            subtask.get(subtaskKey).remove(2);
            subtask.get(subtaskKey).add(2, subtaskStatusDone);
            System.out.println("Статус подзадачи обновлён" + '\n');
        } else if (subtask.get(subtaskKey).contains(subtaskStatusDone)) {
            System.out.println("Подзадача уже завершена" + '\n');
        }
        this.epic = epic;
        String epicKey = subtask.get(subtaskKey).get(3);
        int counterStatusFull = 0;
        int counterStatusNew = 0;
        int counterStatusDone = 0;
        for (Integer i : subtask.keySet()) {
            if (subtask.get(i).contains(subtaskStatusNew) && subtask.get(i).contains(epicKey)) {
                counterStatusFull++;
                counterStatusNew++;
            } else if (subtask.get(i).contains(subtaskStatusDone) && subtask.get(i).contains(epicKey)) {
                counterStatusFull++;
                counterStatusDone++;
            }
        }
        int epicKeyTrans = Integer.parseInt(epicKey);
        epic.updateStatusOfEpic(counterStatusFull, counterStatusNew, counterStatusDone, epicKeyTrans);
    }

    public void fillHistory() {
        if (subtaskHistory.size() < 10) {
            subtaskHistory.add("zero");
        } else if (subtaskHistory.size() == 10) {
            subtaskHistory.remove(0);
            subtaskHistory.add("zero");
        }
    }

    public void fillHistory(int key) {
        if (subtaskHistory.size() < 10) {
            subtaskHistory.add(subtask.get(key).get(0));
        } else if (subtaskHistory.size() == 10) {
            subtaskHistory.remove(0);
            subtaskHistory.add(subtask.get(key).get(0));
        }
    }
}
