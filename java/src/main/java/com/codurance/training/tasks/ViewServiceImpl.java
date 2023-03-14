package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class ViewServiceImpl implements ViewService{

    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;
    private static final String SAMPLE = "    [%c] %s: %s%n";

    public ViewServiceImpl(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }

    @Override
    public void viewByDate() {
        Comparator<Task> compareByDate = Comparator.comparing(Task::getCreatedDate);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> newTasks = new ArrayList<>(project.getValue());
            newTasks.sort(compareByDate);
            newTasks.forEach(this::print);
            out.println();
        }
    }

    @Override
    public void viewByDeadline() {
        Comparator<Task> compareByDate = Comparator.comparing(Task::getDeadline);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> newTasks = new ArrayList<>(project.getValue());
            newTasks.sort(compareByDate);
            newTasks.forEach(this::print);
            out.println();
        }
    }

    @Override
    public void viewByProject() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                print(task);
            }
            out.println();
        }
    }

    @Override
    public void viewDueTasks() {
        LocalDate today = LocalDate.now();
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                Date deadline = task.getDeadline();
                if (deadline != null && deadline.equals(today)) {
                    print(task);
                }
            }
            out.println();
        }
    }

    private void print(Task task) {
        out.printf(SAMPLE, task.isDone() ? 'x' : ' ', task.getId(), task.getDescription());
    }
}
