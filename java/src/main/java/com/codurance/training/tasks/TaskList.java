package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";
    private final BufferedReader in;
    private final PrintWriter out;
    private final ExecuteService executeService;

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
        Map<String, List<Task>> tasks = new LinkedHashMap<>();
        this.executeService = new ExecuteServiceImpl(out,tasks);
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            executeService.execute(command);
        }
    }

}
