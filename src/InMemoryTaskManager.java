import java.util.Scanner;

public class InMemoryTaskManager implements TaskManager {

    Scanner scanner;
    Task task;
    Epic epic;
    Subtask subtask;
    int taskKey;

    public InMemoryTaskManager(Task task, Epic epic, Subtask subtask) {
        this.task = task;
        this.epic = epic;
        this.subtask = subtask;
        this.scanner = new Scanner(System.in);
        this.taskKey = 0;
    }

    private void takePause() {
        int counter = 0;
        while (counter != 1_500_000_000) {
            counter++;
        }
    }

    @Override
    public void printTaskList() {
        System.out.println("Выводим все задачи ..." + '\n');
        takePause();
        task.printTaskAll();
        epic.printEpicAll();
        subtask.printSubtaskAll();
    }

    @Override
    public void clearTaskList() {
        while (true) {
            System.out.println("Вы уверены, что хотите удалить все задачи?");
            System.out.println("Введите 1, если подтверждаете удаление всех задач");
            System.out.println("Введите 2, если передумали удалять все задачи");
            try {
                int menuKey = Integer.parseInt(scanner.nextLine().trim());
                switch (menuKey) {
                    case 1 -> {
                        takePause();
                        task.clearTaskAll();
                        takePause();
                        epic.clearEpicAll();
                        takePause();
                        subtask.clearSubtaskAll();
                        takePause();
                        System.out.println("Все задачи успешно удалены" + '\n');
                    }
                    case 2 -> {}
                    default -> throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
            }
        }
    }

    @Override
    public String findTaskByKey() {
        String nameOfTask = null;
        String warning = null;
        System.out.println("Введите идентификатор задачи, которую надо найти:");
        try {
            int key = Integer.parseInt(scanner.nextLine().trim());
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

    @Override
    public void createTask() {
        while (true) {
            System.out.println("Какую задачу вы хотите создать?");
            System.out.println("Введите 1, чтобы создать стандартную задачу");
            System.out.println("Введите 2, чтобы создать глобальную задачу");
            System.out.println("Введите 0, чтобы вернуть назад");
            try {
                int menuKey = Integer.parseInt(scanner.nextLine().trim());
                switch (menuKey) {
                    case 1 -> task.createTask(++taskKey);
                    case 2 -> {
                        epic.createEpic(++taskKey);
                        taskKey = subtask.subtaskKey;
                    }
                    case 0 -> System.out.println("Возвращаемся назад ..." + '\n');
                    default -> throw new NumberFormatException();
                }
                if (menuKey == 0)
                    break;
            } catch (NumberFormatException e) {
                System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
            }
        }
    }

    @Override
    public void updateTask() {
        while (true) {
            System.out.println("Введите идентификатор задачу, которую вы хотите обновить");
            try {
                int keyToFind = Integer.parseInt(scanner.nextLine().trim());
                if (keyToFind <= 0) {
                    throw new NumberFormatException();
                }
                if (task.findTaskByKey(keyToFind) != null) {
                    while (true) {
                        System.out.println("По введённому идентификатору определилась стандартная задача");
                        System.out.println("Выберите, что вы хотите обновить");
                        System.out.println("Введите 1, если хотите обновить название задачи");
                        System.out.println("Введите 2, если хотите обновить краткое описание задачи");
                        System.out.println("Введите 3, если хотите обновить статус задачи");
                        try {
                            int updateKey = Integer.parseInt(scanner.nextLine().trim());
                            switch (updateKey) {
                                case 1 -> task.updateNameOfTask(keyToFind);
                                case 2 -> task.updateShortDescriptionOfTask(keyToFind);
                                case 3 -> task.updateStatusOfTask(keyToFind);
                                default -> throw new NumberFormatException();
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
                        }
                    }
                    break;
                } else if (epic.findEpicByKey(keyToFind) != null) {
                    while (true) {
                        System.out.println("По введённому идентификатору определилась глобальная задача");
                        System.out.println("Выберите, что вы хотите обновить");
                        System.out.println("Введите 1, если хотите обновить название задачи");
                        System.out.println("Введите 2, если хотите обновить краткое описание задачи");
                        System.out.println("Для глобальных задач обновление статуса невозможно");
                        try {
                            int updateKey = Integer.parseInt(scanner.nextLine().trim());
                            switch (updateKey) {
                                case 1 -> epic.updateNameOfEpic(keyToFind);
                                case 2 -> epic.updateShortDescriptionOfEpic(keyToFind);
                                default -> throw new NumberFormatException();
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Такой команды не существует, попробуй ещё раз (:" + '\n');
                        }
                    }
                    break;
                } else if (subtask.findSubtaskByKey(keyToFind) != null) {
                    while (true) {
                        System.out.println("По введённому идентификатору определилась подзадача");
                        System.out.println("Выберите, что вы хотите обновить");
                        System.out.println("Введите 1, если хотите обновить название подзадачи");
                        System.out.println("Введите 2, если хотите обновить краткое описание подзадачи");
                        System.out.println("Введите 3, если хотите обновить статус подзадачи");
                        try {
                            int updateKey = Integer.parseInt(scanner.nextLine().trim());
                            switch (updateKey) {
                                case 1 -> subtask.updateNameOfSubtask(keyToFind);
                                case 2 -> subtask.updateShortDescriptionOfSubtask(keyToFind);
                                case 3 -> subtask.updateStatusOfSubtask(keyToFind, epic);
                                default -> throw new NumberFormatException();
                            }
                            break;
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

    @Override
    public String removeTaskByKey() {
        int key = 0;
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

    @Override
    public void printSubtasksOfEpic() {
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
