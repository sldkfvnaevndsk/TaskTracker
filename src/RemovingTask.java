import java.util.Scanner;

public class RemovingTask {

    Scanner scanner;
    Task task;
    Epic epic;
    Subtask subtask;
    int key;

    public RemovingTask(Task task, Epic epic, Subtask subtask) {

        scanner = new Scanner(System.in);
        this.task = task;
        this.epic = epic;
        this.subtask = subtask;
    }

    public String remove() {

        String nameOfTask = null;
        String warning = null;
        System.out.println("Введите идентификатор задачи, которую надо удалить");
        try {
            key = Integer.parseInt(scanner.nextLine().trim());
            if (key <= 0) {
                throw new NumberFormatException();
            }
            if (task.findTaskByKey(key) != null) {
                nameOfTask = task.findTaskByKey(key);
                task.removeTaskByKey(key);
            } else if (epic.findEpicByKey(key) != null) {
                nameOfTask = epic.findEpicByKey(key);
                epic.removeEpicByKey(key);
            } else if (subtask.findSubtaskByKey(key) != null) {
                nameOfTask = subtask.findSubtaskByKey(key);
                subtask.removeSubtaskByKey(key);
            }
        } catch (NumberFormatException e) {
            warning = "Неверный формат ввода идентификатора";
        }
        if (nameOfTask != null) {
            return "Задача " + nameOfTask + " с ключом " + key + " удалена";
        } else if (warning != null) {
            return warning;
        } else return "Задача с таким идентификатором отсутствует";
    }
}
