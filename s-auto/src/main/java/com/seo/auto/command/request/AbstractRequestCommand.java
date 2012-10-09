package com.seo.auto.command.request;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.mods.extract.ExtractCommand;
import com.seo.auto.command.mods.extract.Extractor;
import com.seo.auto.command.mods.extract.impl.ExtractorImpl;
import com.seo.auto.command.mods.test.TestCommand;
import com.seo.auto.command.mods.test.Tester;
import com.seo.auto.command.mods.test.impl.TesterImpl;
import com.seo.webclient.model.Request;
import com.seo.webclient.model.Response;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractRequestCommand extends AbstractCommand {
    private String url;
    private String referer;

    private Collection<ExtractCommand> extractCommands = new ArrayList<ExtractCommand>();
    private Collection<TestCommand> testCommands = new ArrayList<TestCommand>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    protected void initializeRequestVariables(Registry registry) {
        this.url = evaluatePlaceholder(url, registry);
        this.referer =  evaluatePlaceholder(referer, registry);

    }

    public void addExtractCommand(ExtractCommand extractCommand) {
        extractCommands.add(extractCommand);
    }

    public void addTestCommand(TestCommand testCommand) {
        testCommands.add(testCommand);
    }

    protected void extract(Response response, Registry registry) {
        Extractor extractor = new ExtractorImpl(response.getContent(), registry);

        for (ExtractCommand extractCommand : extractCommands) {
            extractor.extract(extractCommand);
        }
    }

    protected void test(Response response, Registry registry) {
        Tester tester = new TesterImpl(response, registry);

        for (TestCommand testCommand : testCommands) {
            tester.test(testCommand);
        }
    }

    protected abstract Request createRequest();
}
