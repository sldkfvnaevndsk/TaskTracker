import java.util.Scanner;

public class WritingSubtaskByEpic {

    Scanner scanner;
    Task task;
    Epic epic;
    Subtask subtask;

    public WritingSubtaskByEpic(Task task, Epic epic, Subtask subtask) {

        scanner = new Scanner(System.in);
        this.task = task;
        this.epic = epic;
        this.subtask = subtask;
    }

    public void takeEpicKey() {

        while (true) {
            System.out.println("Введите индентификатор глобальной задачи, чьи подзадачи вы хотите вывести");
            try {
                int epicKey = Integer.parseInt(scanner.nextLine().trim());
                if (epic.findEpicByKey(epicKey) != null) {
                    String nameOfEpic = epic.findEpicByKey(epicKey);
                    System.out.println("Глобальная задача " + nameOfEpic + " имеет следующие подзадачи:");
                    subtask.printSubtaskByEpic(epicKey);
                    break;
                } else throw new NullPointerException();
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода идентификатора" + '\n');
            } catch (NullPointerException e) {
                System.out.println("Глобальная задача с таким идентификатором отсутствует" + '\n');
            }
        }
    }
}
