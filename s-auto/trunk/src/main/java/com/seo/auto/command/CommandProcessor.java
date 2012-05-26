package com.seo.auto.command;

import com.seo.auto.model.Project;
import com.seo.auto.model.exception.ProjectFailedException;
import com.seo.message.MessageNotifier;

/**
 * Command processor interface
 */
public interface CommandProcessor extends MessageNotifier{
    void process(Project project) throws ProjectFailedException;
}
