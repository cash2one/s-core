package com.seo.auto.model;

import com.seo.auto.command.Command;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private List<Command> commands = new ArrayList<Command>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public List<Command> getCommands() {
        return commands;
    }
}
