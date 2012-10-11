package com.seo.auto.web.controller;

import com.seo.auto.web.model.ListConfigsResponseTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

        return new ListConfigsResponseTO(configs);
    }
}
