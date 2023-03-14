package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ViewDueTasksService implements ViewService{

    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;
    private static final String SAMPLE = "    [%c] %s: %s%n";

    public ViewDueTasksService(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }

    @Override
    public void view() {
        LocalDate today = LocalDate.now();
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                Date deadline = task.getDeadline();
                if (deadline != null && deadline.equals(today)) {
                    out.printf(SAMPLE, task.isDone() ? 'x' : ' ', task.getId(), task.getDescription());
                }
            }
            out.println();
        }
    }
}
