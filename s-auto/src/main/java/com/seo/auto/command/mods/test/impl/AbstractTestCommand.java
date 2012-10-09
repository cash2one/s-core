package com.seo.auto.command.mods.test.impl;

import com.seo.auto.command.PlaceholderSupport;
import com.seo.auto.command.mods.test.TestCommand;

public abstract class AbstractTestCommand extends PlaceholderSupport implements TestCommand{
    protected Boolean expected = Boolean.TRUE;
    protected String message = "test passed";

    public void setExpected(Boolean expected) {
        this.expected = expected;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
