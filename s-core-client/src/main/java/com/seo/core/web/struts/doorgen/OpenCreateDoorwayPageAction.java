package com.seo.core.web.struts.doorgen;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.*;
import com.seo.core.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OpenCreateDoorwayPageAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(OpenCreateDoorwayPageAction.class);

    private List<DoorwayConfiguration> doorwayConfigurations;
    private List<KeywordGroup> keywordGroups;
    private List<Template> templates;
    private List<TextSource> textSources;
    private List<RedirectScript> redirectScripts;

    private DoorwayConfigurationFacade doorwayConfigurationFacade;
    private KeywordGroupFacade keywordGroupFacade;
    private TemplateFacade templateFacade;
    private TextSourceFacade textSourceFacade;
    private RedirectScriptFacade redirectScriptFacade;
    private FTPAccountFacade ftpAccountFacade;

    public void setFtpAccountFacade(FTPAccountFacade ftpAccountFacade) {
        this.ftpAccountFacade = ftpAccountFacade;
    }

    public void setRedirectScriptFacade(RedirectScriptFacade redirectScriptFacade) {
        this.redirectScriptFacade = redirectScriptFacade;
    }

    public void setDoorwayConfigurationFacade(DoorwayConfigurationFacade doorwayConfigurationFacade) {
        this.doorwayConfigurationFacade = doorwayConfigurationFacade;
    }

    public void setKeywordGroupFacade(KeywordGroupFacade keywordGroupFacade) {
        this.keywordGroupFacade = keywordGroupFacade;
    }

    public void setTemplateFacade(TemplateFacade templateFacade) {
        this.templateFacade = templateFacade;
    }

    public void setTextSourceFacade(TextSourceFacade textSourceFacade) {
        this.textSourceFacade = textSourceFacade;
    }

    public List<DoorwayConfiguration> getDoorwayConfigurations() {
        return doorwayConfigurations;
    }

    public List<KeywordGroup> getKeywordGroups() {
        return keywordGroups;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public List<TextSource> getTextSources() {
        return textSources;
    }

    public List<RedirectScript> getRedirectScripts() {
        return redirectScripts;
    }

    public void setRedirectScripts(List<RedirectScript> redirectScripts) {
        this.redirectScripts = redirectScripts;
    }

    private int ftpAccountCount;

    public int getFtpAccountCount() {
        return ftpAccountCount;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing open create doorway page action");

        this.doorwayConfigurations = doorwayConfigurationFacade.getAll();
        this.keywordGroups = keywordGroupFacade.getAll();
        this.templates = templateFacade.getAll();
        this.textSources = textSourceFacade.getAll();
        this.redirectScripts = redirectScriptFacade.getAll();
        this.ftpAccountCount = ftpAccountFacade.getFreeAccountCount();

        return SUCCESS;
    }
}
