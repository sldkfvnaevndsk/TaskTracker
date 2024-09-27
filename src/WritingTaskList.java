import java.util.Scanner;

public class WritingTaskList {

    Scanner scanner;
    Task task;
    Epic epic;
    Subtask subtask;

    public WritingTaskList(Task task, Epic epic, Subtask subtask) {

        scanner = new Scanner(System.in);
        this.task = task;
        this.epic = epic;
        this.subtask = subtask;
    }

    public void prepareOutput() {

        System.out.println("Выводим все задачи ..." + '\n');
        takePause();
        outputTaskList();
    }

    public void outputTaskList() {

        task.printTaskAll();
        epic.printEpicAll();
        subtask.printSubtaskAll();
    }

    public void takePause() {

        int counter = 0;
        while (counter != 1_500_000_000) {
            counter++;
        }
    }
}
