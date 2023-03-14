package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ViewByDeadlineService implements ViewService{

    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;
    private static final String SAMPLE = "    [%c] %s: %s%n";

    public ViewByDeadlineService(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }

    @Override
    public void view() {
        Comparator<Task> compareByDate = Comparator.comparing(Task::getDeadline);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> newTasks = new ArrayList<>(project.getValue());
            newTasks.sort(compareByDate);
            newTasks.forEach(this::print);
            out.println();
        }
    }

    private void print(Task task) {
        out.printf(SAMPLE, task.isDone() ? 'x' : ' ', task.getId(), task.getDescription());
    }
}
