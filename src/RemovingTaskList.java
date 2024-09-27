import java.util.Scanner;

public class RemovingTaskList {

    Scanner scanner;
    Task task;
    Epic epic;
    Subtask subtask;
    int menuKey;

    public RemovingTaskList(Task task, Epic epic, Subtask subtask) {

        scanner = new Scanner(System.in);
        this.task = task;
        this.epic = epic;
        this.subtask = subtask;
    }

    public void acceptChoice() {

        while (true) {
            printMenu();
            try {
                menuKey = Integer.parseInt(scanner.nextLine().trim());
                if (menuKey == 1) {
                    takePause();
                    task.clearTaskAll();
                    takePause();
                    epic.clearEpicAll();
                    takePause();
                    subtask.clearSubtaskAll();
                    takePause();
                    System.out.println("Все задачи успешно удалены" + '\n');
                    break;
                } else if (menuKey == 2) {
                    break;
                } else System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
            } catch (NumberFormatException e) {
                System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
            }
        }
    }

    public void printMenu() {

        System.out.println("Вы уверены, что хотите удалить все задачи?");
        System.out.println("Введите 1, если подтверждаете удаление всех задач");
        System.out.println("Введите 2, если передумали удалять все задачи");
    }

    public void takePause() {

        int counter = 0;
        while (counter != 1_500_000_000) {
            counter++;
        }
    }
}
