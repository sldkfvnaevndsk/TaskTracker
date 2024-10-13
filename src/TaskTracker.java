import java.util.Scanner;

public class TaskTracker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Task task = new Task();
        Subtask subtask = new Subtask();
        Epic epic = new Epic(subtask);
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager(task, epic, subtask);

        while (true) {
            printMenu();
            try {
                int menuKey = Integer.parseInt(scanner.nextLine().trim());
                switch (menuKey) {
                    case 1 -> inMemoryTaskManager.printTaskList(); //Просмотр всех задач
                    case 2 -> inMemoryTaskManager.clearTaskList(); //Удаление всех задач
                    case 3 -> System.out.println(inMemoryTaskManager.findTaskByKey() + '\n'); //Поиск задачи по ключу
                    case 4 -> inMemoryTaskManager.createTask(); //Создание задачи
                    case 5 -> inMemoryTaskManager.updateTask(); //Обновление задачи
                    case 6 -> System.out.println(inMemoryTaskManager.removeTaskByKey() + '\n'); //Удаление задачи по ключу
                    case 7 -> inMemoryTaskManager.printSubtasksOfEpic(); //Получение списка подзадач определенного эпика
                    case 0 -> System.out.println("Выходим ...");
                    default -> throw new NumberFormatException();
                }
                if (menuKey == 0)
                    break;
            } catch (NumberFormatException e) {
                System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
            }
        }
    }

    public static void printMenu() {

        System.out.println("Добро пожаловать в менеджер задач <3");
        System.out.println("Выберите, что вы хотите сделать");
        System.out.println("Введите 1, чтобы посмотреть список всех задач");
        System.out.println("Введите 2, чтобы удалить все задачи");
        System.out.println("Введите 3, чтобы найти задачу по ключу");
        System.out.println("Введите 4, чтобы создать задачу");
        System.out.println("Введите 5, чтобы обновить задачу");
        System.out.println("Введите 6, чтобы удалить задачу по ключу");
        System.out.println("Введите 7, чтобы получить список всех подзадач определённого эпика");
        System.out.println("Введите 0, чтобы выйти из программы");
    }
}
