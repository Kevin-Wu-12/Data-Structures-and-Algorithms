/*
 * NAME: TODO
 * PID: TODO
 */

/**
 * Round Robin Task
 *
 * @author Kevin Wu
 */
public class RoundRobin {

    /* constants */
    private static final String TASKS_COMPLETED = "All tasks are already completed.";

    /* instance variables */
    private MyQueue<Task> waitlist, finished;
    private static final int LETTERS = 3;
    private static final int DEFAULT_TIME = 4;
    private int quantum, burstTime, waitTime, totalWait;

    /**
     * Creates a RoundRobin with a default quantum time of 4.
     *
     * @param toRun List of task to run
     * @throws IllegalArgumentException Thrown if toRun is null or if it does not contain any
     * task.
     */
    public RoundRobin(Task[] toRun) {
        if (toRun == null || toRun.length == 0) {
            throw new IllegalArgumentException();
        }
        waitlist = new MyQueue<>();
        finished = new MyQueue<>();
        quantum = DEFAULT_TIME;
        for (Task task: toRun) {
            waitlist.enqueue(task);
        }

    }

    /**
     * Creates a RoundRobin with a default quantum time of 4.
     *
     * @param toRun List of task to run
     * @param quantum Quantum time allowed.
     * @throws IllegalArgumentException Thrown if quantum is less than 1 and if toRun is either
     * null or there are no task in it.
     *
     */
    public RoundRobin(int quantum, Task[] toRun) {
        if (quantum < 1 ||  toRun.length == 0 || toRun == null) {
            throw new IllegalArgumentException();
        }
        waitlist = new MyQueue<>();
        finished = new MyQueue<>();
        this.quantum = quantum;

        for (Task task: toRun) {
            waitlist.enqueue(task);
        }
    }

    /**
     * Runs all the task  in the RoundRobin.
     * @return Returns how long the wait and burst time was and the order the task were finished.
     *
     */
    public String runAllTasks() {
        StringBuilder str = new StringBuilder();
        int totalWaitTime = 0;

        if (waitlist.isEmpty()) {
            return TASKS_COMPLETED;
        }

        while (!waitlist.isEmpty()) {
            Task current = waitlist.dequeue();
            int a = 0;
            for (int i = 0; i < quantum; i++) {
                if (current.runTask()) {
                    burstTime++;
                    a++;
                    if (current.isFinished()) {
                        finished.enqueue(current);
                        break;
                    }
                }
            }
            waitTime += a * waitlist.size();
            if (!current.isFinished()) {
                waitlist.enqueue(current);
            }
        }

        str.append("All tasks are run within ").append(burstTime)
        .append(" units of burst time and ").append(waitTime)
        .append(" units of wait time. The tasks are finished in this order:\n");

        while (!finished.isEmpty()) {
            Task a = finished.dequeue();
            str.append(a.toString()).append(" -> ");
        }
        String info = str.toString();
        return info.substring(0, info.length() - LETTERS);
    }

    /**
     * Main method for testing.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Task[] test1 = {new Task("A", 3), new Task("B", 4),
                new Task("C", 4), new Task("D", 12),
                new Task("E", 6), new Task("F", 3)};
        RoundRobin rr1 = new RoundRobin(3, test1);     // Quantum: 3, toRun: test1
        System.out.println(rr1.runAllTasks());   // Burst: 32, Wait: 86, Order: AFBCED
        System.out.println();
        System.out.println(rr1.runAllTasks());   // TASKS_COMPLETED
        System.out.println();

        Task[] test2 = {new Task("A", 9), new Task("B", 8),
                new Task("C", 6), new Task("D", 4),
                new Task("E", 4), new Task("F", 3)};
        RoundRobin rr2 = new RoundRobin(test2);  // Quantum: 4, toRun: test2
        System.out.println(rr2.runAllTasks());   // Burst: 34, Wait: 123, Order: DEFBCA
        System.out.println();
        System.out.println(rr2.runAllTasks());   // TASKS_COMPLETED
        System.out.println();

        Task[] test3 = {new Task("A", 7), new Task("B", 5),
                new Task("C", 3), new Task("D", 1),
                new Task("E", 2), new Task("F", 4),
                new Task("G", 6), new Task("H", 8)};
        RoundRobin rr3 = new RoundRobin(3, test3);     // Quantum: 3, toRun: test3
        System.out.println(rr3.runAllTasks());   // Burst: 36, Wait: 148, Order: CDEBFGAH
        System.out.println();
        System.out.println(rr3.runAllTasks());   // TASKS_COMPLETED
        System.out.println();
    }
}
