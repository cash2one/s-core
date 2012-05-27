package com.seo.doorgen.render.services;

import com.seo.doorgen.render.AbstractRenderService;
import com.seo.random.facade.RandomFacade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateRenderService extends AbstractRenderService{
    private final static String NAME = "dateService";

    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private RandomFacade randomFacade;

    public void setRandomFacade(RandomFacade randomFacade) {
        this.randomFacade = randomFacade;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public String generateDate(int years, int month, int days) {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.YEAR, -randomFacade.getInteger(years));
        calendar.add(Calendar.MONTH, -randomFacade.getInteger(month));
        calendar.add(Calendar.DATE, -randomFacade.getInteger(days));

        return DATE_FORMAT.format(calendar.getTime());
    }
}
