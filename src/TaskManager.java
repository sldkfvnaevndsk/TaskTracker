public interface TaskManager {

    void printTaskList();

    void clearTaskList();

    String findTaskByKey();

    void createTaskOrEpic();

    void updateTask();

    String removeTaskByKey();

    void printSubtasksOfEpic();
}
