package com.seo.auto.web.controller;

import com.seo.auto.service.AutoConfigService;
import com.seo.auto.web.model.*;
import com.seo.auto.web.model.enums.ResponseStatus;
import com.seo.core.model.AutoConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/configs/")
public class AutoConfigController {

    @Inject
    private AutoConfigService autoConfigService;

    @RequestMapping(value = "listConfigs")
    @ResponseBody
    public ListConfigsResponseTO listConfigs() {
        List<AutoConfig> configs = autoConfigService.getAll();

        return new ListConfigsResponseTO(ResponseStatus.SUCCESS, configs);
    }

    @RequestMapping(value = "saveConfig", method = RequestMethod.POST)
    @ResponseBody
    public AddAutoConfigResponseTO save(@RequestBody AddAutoConfigRequestTO request) {

        AutoConfig autoConfig = new AutoConfig(request.getName(), request.getContent());
        autoConfig = autoConfigService.save(autoConfig);

        return new AddAutoConfigResponseTO(ResponseStatus.SUCCESS, autoConfig.getId());
    }

    @RequestMapping(value = "deleteConfig", method = RequestMethod.DELETE)
    @ResponseBody
    public DeleteAutoConfigResponseTO delete(@RequestBody DeleteAutoConfigRequestTO request) {

        autoConfigService.delete(request.getId());

        return new DeleteAutoConfigResponseTO(ResponseStatus.SUCCESS, request.getId());
    }
}
