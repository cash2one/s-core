package com.seo.core.facade.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DoorwayCommandFacadeImpl implements DoorwayCommandFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(DoorwayCommandFacadeImpl.class);

    private List<DoorwayCommand> doorwayCommands;

    public void setDoorwayCommands(List<DoorwayCommand> doorwayCommands) {
        this.doorwayCommands = doorwayCommands;
    }

    @Override
    public List<String> getCommands() {
        LOGGER.debug("getting commands list");

        List<String> commands = new ArrayList<String>();

        for (DoorwayCommand doorwayCommand : doorwayCommands) {
            commands.add(doorwayCommand.getName());
        }

        return commands;
    }

    @Override
    public void processGroup(String commandName, List<Long> ids) {
        final DoorwayCommand doorwayCommand = getCommandByName(commandName);
        for (Long id : ids) {
            LOGGER.debug("processing '{}' command for id: {}", commandName, id);
            doorwayCommand.process(id);
        }
    }

    private DoorwayCommand getCommandByName(String commandName) {
        for (DoorwayCommand doorwayCommand : doorwayCommands) {
            if (doorwayCommand.getName().equals(commandName)) {
                return doorwayCommand;
            }
        }

        LOGGER.error("no such command: {}", commandName);
        throw new IllegalArgumentException("no such command: " + commandName);
    }
}
