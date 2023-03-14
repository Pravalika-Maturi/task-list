package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CheckServiceImpl implements CheckService {
    private final Map<String, List<Task>> tasks;
    private final PrintWriter out;

    public CheckServiceImpl(PrintWriter out, Map<String, List<Task>> tasks) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void check(String idString) {
        setDone(idString, true);
    }

    @Override
    public void uncheck(String idString) {
        setDone(idString, false);
    }

    private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }
}
