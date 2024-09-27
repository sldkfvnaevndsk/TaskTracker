import java.util.Scanner;

public class CreatingTaskAndEpic {

    Scanner scanner;
    Task task;
    Epic epic;
    Subtask subtask;
    int menuKey;
    int taskKey;

    public CreatingTaskAndEpic(Task task, Epic epic, Subtask subtask) {

        scanner = new Scanner(System.in);
        this.task = task;
        this.epic = epic;
        this.subtask = subtask;
        taskKey = 0;
    }

    public void in() {

        while (true) {
            printMenu();
            try {
                menuKey = Integer.parseInt(scanner.nextLine().trim());
                if (menuKey == 1) {
                    task.createTask(++taskKey);
                } else if (menuKey == 2) {
                    epic.createEpic(++taskKey);
                    taskKey = subtask.subtaskKey;
                } else if (menuKey == 0) {
                    System.out.println("Возвращаемся назад ..." + '\n');
                    break;
                } else System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
            } catch (NumberFormatException e) {
                System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
            }
        }
    }

    public void printMenu() {
        System.out.println("Какую задачу вы хотите создать?");
        System.out.println("Введите 1, чтобы создать стандартную задачу");
        System.out.println("Введите 2, чтобы создать глобальную задачу");
        System.out.println("Введите 0, чтобы вернуть назад");
    }
}
