import java.util.Scanner;

public class TaskTracker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Task task = new Task();
        Subtask subtask = new Subtask();
        Epic epic = new Epic(subtask);
        WritingTaskList writingTaskList = new WritingTaskList(task, epic, subtask);
        RemovingTaskList removingTaskList = new RemovingTaskList(task, epic, subtask);
        ResearchTask researchTask = new ResearchTask(task, epic, subtask);
        CreatingTaskAndEpic creatingTaskAndEpic = new CreatingTaskAndEpic(task, epic, subtask);
        UpdateTask updateTask = new UpdateTask(task, epic, subtask);
        RemovingTask removingTask = new RemovingTask(task, epic, subtask);
        WritingSubtaskByEpic writingSubtaskByEpic = new WritingSubtaskByEpic(task, epic, subtask);

        while (true) {
            printMenu();
            try {
                int menuKey = Integer.parseInt(scanner.nextLine().trim());
                if (menuKey == 1) {
                    writingTaskList.prepareOutput(); //Просмотр всех задач
                } else if (menuKey == 2) {
                    removingTaskList.acceptChoice(); //Удаление всех задач
                } else if (menuKey == 3) {
                    System.out.println(researchTask.research() + '\n'); //Поиск задачи по ключу
                } else if (menuKey == 4) {
                    creatingTaskAndEpic.in(); //Создание задачи
                } else if (menuKey == 5) {
                    updateTask.chooseTask(); //Обновление задачи
                } else if (menuKey == 6) {
                    System.out.println(removingTask.remove() + '\n'); //Удаление задачи по ключу
                } else if (menuKey == 7) {
                    writingSubtaskByEpic.takeEpicKey(); //Получение списка подзадач определенного эпика
                } else if (menuKey == 0) {
                    System.out.println("Выходим ...");
                    break;
                } else System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
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
