package com.seo.core.facade.doorgen.task;

import com.seo.core.concurrency.task.AbstractMessageTask;
import com.seo.core.facade.*;
import com.seo.core.facade.doorgen.DoorwayGeneratorFacadeAdaptor;
import com.seo.core.facade.doorgen.request.CreateDoorwayRequest;
import com.seo.core.model.*;
import com.seo.core.model.account.FTPAccount;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CreateDoorwayTask extends AbstractMessageTask{
    private DoorwayGeneratorFacadeAdaptor doorwayGeneratorFacadeAdaptor;
    private DoorwayConfigurationFacade doorwayConfigurationFacade;
    private TemplateFacade templateFacade;
    private KeywordGroupFacade keywordGroupFacade;
    private TextSourceFacade textSourceFacade;
    private DoorwayFacade doorwayFacade;
    private FTPAccountFacade ftpAccountFacade;
    private RedirectScriptFacade redirectScriptFacade;

    public void setRedirectScriptFacade(RedirectScriptFacade redirectScriptFacade) {
        this.redirectScriptFacade = redirectScriptFacade;
    }

    public void setFtpAccountFacade(FTPAccountFacade ftpAccountFacade) {
        this.ftpAccountFacade = ftpAccountFacade;
    }

    public void setDoorwayFacade(DoorwayFacade doorwayFacade) {
        this.doorwayFacade = doorwayFacade;
    }

    public void setDoorwayConfigurationFacade(DoorwayConfigurationFacade doorwayConfigurationFacade) {
        this.doorwayConfigurationFacade = doorwayConfigurationFacade;
    }

    public void setTemplateFacade(TemplateFacade templateFacade) {
        this.templateFacade = templateFacade;
    }

    public void setKeywordGroupFacade(KeywordGroupFacade keywordGroupFacade) {
        this.keywordGroupFacade = keywordGroupFacade;
    }

    public void setTextSourceFacade(TextSourceFacade textSourceFacade) {
        this.textSourceFacade = textSourceFacade;
    }

    public void setDoorwayGeneratorFacadeAdaptor(DoorwayGeneratorFacadeAdaptor doorwayGeneratorFacadeAdaptor) {
        this.doorwayGeneratorFacadeAdaptor = doorwayGeneratorFacadeAdaptor;
    }

    private CreateDoorwayRequest request;
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setRequest(CreateDoorwayRequest request) {
        this.request = request;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run() {
        create(request);
    }

    public void create(CreateDoorwayRequest request) {
        doorwayGeneratorFacadeAdaptor.setMessageListener(getMessageListener());

        DoorwayConfiguration configuration = doorwayConfigurationFacade.getById(request.getDoorwayConfigurationId());
        Template template = templateFacade.getById(request.getTemplateId());
        KeywordGroup keywordGroup = keywordGroupFacade.getById(request.getKeywordGroupId());
        TextSource textSource = textSourceFacade.getById(request.getTextSourceId());
        RedirectScript redirectScript = redirectScriptFacade.getById(request.getRedirectScriptId());

        FTPAccount ftpAccount = ftpAccountFacade.reserveAccount();
        String doorwayPath = doorwayGeneratorFacadeAdaptor.createDoorway(configuration, template, keywordGroup, textSource, ftpAccount.getUrl());

        Doorway doorway = new Doorway(DoorwayState.NEW, doorwayPath, ftpAccount.getUrl(), ftpAccount, redirectScript);
        doorwayFacade.save(doorway);
    }

    private final static String NAME = "create doorway";

    @Override
    public String toString() {
        return String.format("%s @ index=%s", NAME, index) ;
    }
}
