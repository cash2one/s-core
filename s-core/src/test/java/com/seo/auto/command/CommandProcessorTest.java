package com.seo.auto.command;

import com.seo.InjectUtil;
import com.seo.auto.client.CommandClient;
import com.seo.auto.client.CommandClientImpl;
import com.seo.auto.model.Project;
import com.seo.provider.manager.ProviderManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class CommandProcessorTest {

    @Mock
    private Command firstCommand;

    @Mock
    private Command secondCommand;

    private CommandProcessor commandProcessor;

    @Mock
    private ProviderManager providerManager;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        commandProcessor = new CommandProcessorImpl();

        InjectUtil.injectMock(commandProcessor, "providerManager", providerManager);
    }

    @Test
    public void testCommandProcessor() throws Exception{
        Project project = new Project();

        project.addCommand(firstCommand);
        project.addCommand(secondCommand);

        CommandClient commandClient = CommandClientImpl.newInstance();

        commandProcessor.process(project, commandClient);

        verify(firstCommand).initCommand(providerManager);
        verify(secondCommand).initCommand(providerManager);

        verify(firstCommand).execute(commandClient);
        verify(secondCommand).execute(commandClient);
    }
}
