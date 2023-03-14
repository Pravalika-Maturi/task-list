package com.codurance.training.tasks;

import java.io.PrintWriter;

public class ErrorServiceImpl implements ErrorService{

    private final PrintWriter out;

    public ErrorServiceImpl(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
