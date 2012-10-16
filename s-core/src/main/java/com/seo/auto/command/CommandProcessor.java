package com.seo.auto.command;

import com.seo.auto.client.CommandClient;
import com.seo.auto.model.Project;
import com.seo.auto.model.exception.ProjectFailedException;
import com.seo.message.MessageNotifier;

/**
 * Command processor interface
 */
public interface CommandProcessor{
    void process(Project project, CommandClient commandClient) throws ProjectFailedException;
}
