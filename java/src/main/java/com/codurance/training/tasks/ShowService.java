package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ShowService implements InfoService {
    private final Map<String, List<Task>> tasks;
    private final PrintWriter out;

    public ShowService(PrintWriter out, Map<String, List<Task>> tasks) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void print() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }
}
