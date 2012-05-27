package com.seo.core.facade.command;

public interface DoorwayCommand {
    String getName();
    void process(Long doorwayId);
}
