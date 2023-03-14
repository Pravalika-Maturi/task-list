package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AddServiceImpl implements AddService {
    private long lastId = 0;
    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;

    public AddServiceImpl(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }

    @Override
    public void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private void addTask(String project, String description) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(nextId(), description, false));
    }

    private void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    private long nextId() {
        return ++lastId;
    }

    @Override
    public void addDeadline(long id, String deadline) {
        Date date = parseDate(deadline);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for(Task task: project.getValue()) {
                if(task.getId() == id) {
                    task.setDeadline(date);
                }
            }
        }
        out.printf("Could not find a task with an ID of %s", id);
        out.println();
    }

    private Date parseDate(String deadline) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(deadline);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

}
