package com.seo.auto.batch.tasklet;

import com.seo.auto.batch.ContextConstants;
import com.seo.auto.facade.ConfigFacade;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
public class RunAutoConfigTasklet implements Tasklet {

    @Inject
    private ConfigFacade configFacade;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Map<String,Object> jobParameters = chunkContext.getStepContext().getJobParameters();

        String config = (String)jobParameters.get(ContextConstants.AUTO_CONFIG.getValue());

        configFacade.processConfig(config);

        return RepeatStatus.FINISHED;
    }
}
