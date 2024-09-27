import java.util.Scanner;

public class UpdateTask {

    Scanner scanner;
    Task task;
    Epic epic;
    Subtask subtask;

    public UpdateTask(Task task, Epic epic, Subtask subtask) {

        scanner = new Scanner(System.in);
        this.task = task;
        this.epic = epic;
        this.subtask = subtask;
    }

    public void chooseTask() {

        while (true) {
            System.out.println("Введите идентификатор задачу, которую вы хотите обновить");
            try {
                int taskKey = Integer.parseInt(scanner.nextLine().trim());
                if (taskKey <= 0) {
                    throw new NumberFormatException();
                }
                if (task.findTaskByKey(taskKey) != null) {
                    while (true) {
                        printTaskMenu();
                        try {
                            int updateKey = Integer.parseInt(scanner.nextLine().trim());
                            if (updateKey == 1) {
                                task.updateNameOfTask(taskKey);
                                break;
                            } else if (updateKey == 2) {
                                task.updateShortDescriptionOfTask(taskKey);
                                break;
                            } else if (updateKey == 3) {
                                task.updateStatusOfTask(taskKey);
                                break;
                            } else System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
                        } catch (NumberFormatException e) {
                            System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
                        }
                    }
                    break;
                } else if (epic.findEpicByKey(taskKey) != null) {
                    while (true) {
                        printEpicMenu();
                        try {
                            int updateKey = Integer.parseInt(scanner.nextLine().trim());
                            if (updateKey == 1) {
                                epic.updateNameOfEpic(taskKey);
                                break;
                            } else if (updateKey == 2) {
                                epic.updateShortDescriptionOfEpic(taskKey);
                                break;
                            } else System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
                        } catch (NumberFormatException e) {
                            System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
                        }
                    }
                    break;
                } else if (subtask.findSubtaskByKey(taskKey) != null) {
                    while (true) {
                        printSubtaskMenu();
                        try {
                            int updateKey = Integer.parseInt(scanner.nextLine().trim());
                            if (updateKey == 1) {
                                subtask.updateNameOfSubtask(taskKey);
                                break;
                            } else if (updateKey == 2) {
                                subtask.updateShortDescriptionOfSubtask(taskKey);
                                break;
                            } else if (updateKey == 3) {
                                subtask.updateStatusOfSubtask(taskKey, epic);
                                break;
                            } else System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
                        } catch (NumberFormatException e) {
                            System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
                        }
                    }
                    break;
                } else System.out.println("Задача с таким идентификатором отсутствует");
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода идентификатора" + '\n');
            }
        }
    }

    public void printTaskMenu() {

        System.out.println("По введённому идентификатору определилась стандартная задача");
        System.out.println("Выберите, что вы хотите обновить");
        System.out.println("Введите 1, если хотите обновить название задачи");
        System.out.println("Введите 2, если хотите обновить краткое описание задачи");
        System.out.println("Введите 3, если хотите обновить статус задачи");
    }

    public void printEpicMenu() {

        System.out.println("По введённому идентификатору определилась глобальная задача");
        System.out.println("Выберите, что вы хотите обновить");
        System.out.println("Введите 1, если хотите обновить название задачи");
        System.out.println("Введите 2, если хотите обновить краткое описание задачи");
        System.out.println("Для глобальных задач обновление статуса невозможно");
    }

    public void printSubtaskMenu() {

        System.out.println("По введённому идентификатору определилась подзадача");
        System.out.println("Выберите, что вы хотите обновить");
        System.out.println("Введите 1, если хотите обновить название подзадачи");
        System.out.println("Введите 2, если хотите обновить краткое описание подзадачи");
        System.out.println("Введите 3, если хотите обновить статус подзадачи");
    }
}
