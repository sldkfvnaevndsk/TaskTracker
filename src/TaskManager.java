public interface TaskManager {

    void printTaskList();

    void clearTaskList();

    String findTaskByKey();

    void createTask();

    void updateTask();

    String removeTaskByKey();

    void printSubtasksOfEpic();

    void getHistory();
}
