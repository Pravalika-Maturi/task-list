package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class DeleteServiceImpl implements DeleteService{

    private final PrintWriter out;

    private Map<String, List<Task>> tasks;

    public DeleteServiceImpl(PrintWriter out,Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }

    @Override
    public void delete(long id) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            project.getValue().removeIf(task -> task.getId() == id);
        }
        out.printf("Could not find a task with an ID of %s.", id);
        out.println();
    }
}
