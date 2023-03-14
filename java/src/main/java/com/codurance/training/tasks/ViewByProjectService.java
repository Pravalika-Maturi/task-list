package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ViewByProjectService implements ViewService{

    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;
    private static final String SAMPLE = "    [%c] %s: %s%n";

    public ViewByProjectService(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }

    @Override
    public void view() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf(SAMPLE, task.isDone() ? 'x' : ' ', task.getId(), task.getDescription());
            }
            out.println();
        }
    }
}
