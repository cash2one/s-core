package com.seo.core.facade.doorgen.request;

public class CreateDoorwayRequest {
    private Long doorwayConfigurationId;
    private Long templateId;
    private Long keywordGroupId;
    private Long textSourceId;
    private Long redirectScriptId;
    private Integer doorwayCount;

    public Long getDoorwayConfigurationId() {
        return doorwayConfigurationId;
    }

    public void setDoorwayConfigurationId(Long doorwayConfigurationId) {
        this.doorwayConfigurationId = doorwayConfigurationId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getKeywordGroupId() {
        return keywordGroupId;
    }

    public void setKeywordGroupId(Long keywordGroupId) {
        this.keywordGroupId = keywordGroupId;
    }

    public Long getTextSourceId() {
        return textSourceId;
    }

    public void setTextSourceId(Long textSourceId) {
        this.textSourceId = textSourceId;
    }

    public Long getRedirectScriptId() {
        return redirectScriptId;
    }

    public void setRedirectScriptId(Long redirectScriptId) {
        this.redirectScriptId = redirectScriptId;
    }

    public Integer getDoorwayCount() {
        return doorwayCount;
    }

    public void setDoorwayCount(Integer doorwayCount) {
        this.doorwayCount = doorwayCount;
    }
}
