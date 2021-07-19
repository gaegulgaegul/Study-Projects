package me.gaegul.springbootbatchchunk.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class PayJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final Step payItemReaderStep;

    @Bean
    public Job jdbcCursorItemReaderJob() {
        return jobBuilderFactory.get("payItemReaderJob")
                .start(payItemReaderStep)
                .build();
    }

}
