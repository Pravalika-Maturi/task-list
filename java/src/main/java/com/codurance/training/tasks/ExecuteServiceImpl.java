package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ExecuteServiceImpl implements ExecuteService{
    private final AddService addService;
    private final CheckService checkService;
    private final HelpService helpService;
    private final ShowService showService;
    private final ErrorService errorService;
    private final DeleteService deleteService;
    private final ViewService viewService;
    Map<String, List<Task>> tasks;
    PrintWriter out;

    public ExecuteServiceImpl(PrintWriter out, Map<String, List<Task>> tasks) {
        this.addService = new AddServiceImpl(out, tasks);
        this.checkService = new CheckServiceImpl(out, tasks);
        this.helpService = new HelpService(out);
        this.showService = new ShowService(out, tasks);
        this.errorService = new ErrorServiceImpl(out);
        this.deleteService = new DeleteServiceImpl(out,tasks);
        this.viewService = new ViewServiceImpl(out,tasks);
    }

    @Override
    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                showService.print();
                break;
            case "add":
                addService.add(commandRest[1]);
                break;
            case "check":
                checkService.check(commandRest[1]);
                break;
            case "uncheck":
                checkService.uncheck(commandRest[1]);
                break;
            case "help":
                helpService.print();
                break;
            case "delete":
                new DeleteServiceImpl(out, tasks).delete(Long.parseLong(commandRest[1]));
                break;
            case "deadline":
                addService.addDeadline(Long.parseLong(commandRest[0]), commandRest[1]);
                break;
            case "today":
                viewService.viewDueTasks();
                break;
            case "view":
                String viewBy = commandRest[1];
                switch (viewBy) {
                    case "by date":
                        viewService.viewByDate();
                        break;
                    case "by deadline":
                        viewService.viewByDeadline();
                        break;
                    case "by project":
                        viewService.viewByProject();
                        break;
                    default:
                        errorService.error(command);
                        break;
                }
            default:
                errorService.error(command);
                break;
        }
    }
}
