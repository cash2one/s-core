package com.seo.core.facade.command;

import java.util.List;

public interface DoorwayCommandFacade {
    List<String> getCommands();
    void processGroup(String commandName, List<Long> ids);
}
