import java.util.Scanner;

public class ResearchTask {

    Scanner scanner;
    Task task;
    Epic epic;
    Subtask subtask;
    int key;

    public ResearchTask(Task task, Epic epic, Subtask subtask) {

        scanner = new Scanner(System.in);
        this.task = task;
        this.epic = epic;
        this.subtask = subtask;
    }

    public String research() {

        String nameOfTask = null;
        String warning = null;
        System.out.println("Введите идентификатор задачи, которую надо найти:");
        try {
            key = Integer.parseInt(scanner.nextLine().trim());
            if (key <= 0) {
                throw new NumberFormatException();
            }
            if (task.findTaskByKey(key) != null) {
                nameOfTask = task.findTaskByKey(key);
            } else if (epic.findEpicByKey(key) != null) {
                nameOfTask = epic.findEpicByKey(key);
            } else if (subtask.findSubtaskByKey(key) != null) {
                nameOfTask = subtask.findSubtaskByKey(key);
            }
        } catch (NumberFormatException e) {
            warning = "Неверный формат ввода идентификатора";
        }
        if (nameOfTask != null) {
            return "Нужная задача - " + nameOfTask;
        } else if (warning != null) {
            return warning;
        } else return "Задача с таким идентификатором отсутствует";
    }
}
