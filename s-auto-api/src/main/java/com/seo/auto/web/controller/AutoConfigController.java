package com.seo.auto.web.controller;

import com.seo.auto.web.model.*;
import com.seo.auto.web.model.enums.ResponseStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/configs/")
public class AutoConfigController {

    @RequestMapping(value = "listConfigs")
    @ResponseBody
    public ListConfigsResponseTO listConfigs() {
        List<String> configs = new ArrayList<String>();

        return new ListConfigsResponseTO(ResponseStatus.SUCCESS, configs);
    }

    @RequestMapping(value = "saveConfig", method = RequestMethod.POST)
    @ResponseBody
    public AddAutoConfigResponseTO save(@RequestBody AddAutoConfigRequestTO request) {

        return new AddAutoConfigResponseTO(ResponseStatus.SUCCESS, 7L);
    }

    @RequestMapping(value = "deleteConfig", method = RequestMethod.DELETE)
    @ResponseBody
    public DeleteAutoConfigResponseTO delete(@RequestBody DeleteAutoConfigRequestTO request) {

        return new DeleteAutoConfigResponseTO(ResponseStatus.SUCCESS, 7L);
    }
}
