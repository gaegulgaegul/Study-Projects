package me.gaegul.springbootbatchchunk.step;

import lombok.RequiredArgsConstructor;
import me.gaegul.springbootbatchchunk.model.Pay;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PayStepConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private static final int chunkSize = 10;

    @Bean
    public Step payItemReaderStep() {
        return stepBuilderFactory.get("payItemReaderStep")
                .<Pay, Pay>chunk(chunkSize)
                .reader(payItemReader())
                .processor(payItemProcessor())
                .writer(payItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public ListItemReader<Pay> payItemReader() {
        List<Pay> pays = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            pays.add(new Pay(i*1L, i*1000L));
        }

        return new ListItemReader<>(pays);

    }

    @Bean
    @StepScope
    public ItemProcessor<Pay,Pay> payItemProcessor() {
        return item -> {
            item.setAmount(item.getAmount() + 10L);
            return item;
        };
    }

    @Bean
    @StepScope
    public JpaItemWriter<Pay> payItemWriter() {
        return new JpaItemWriterBuilder<Pay>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

}
