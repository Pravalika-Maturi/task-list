package com.codurance.training.tasks;

import java.io.PrintWriter;

public class HelpService implements InfoService{
    private final PrintWriter out;

    public HelpService(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void print() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }
}
