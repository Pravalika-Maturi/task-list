package com.codurance.training.tasks;

public interface AddService {
    void add(String commandLine);
    void addDeadline(long id, String deadline);
}
