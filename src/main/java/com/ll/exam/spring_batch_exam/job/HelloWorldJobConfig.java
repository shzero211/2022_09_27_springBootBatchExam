package com.ll.exam.spring_batch_exam.job;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HelloWorldJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory.get("helloWorldJob")
                .start(helloWorldStep1())
                .next(helloWorldStep2())
                .incrementer(new RunIdIncrementer())
                .build();
    }
    @Bean
    public Step helloWorldStep1() {
        return stepBuilderFactory.get("helloWorldStep1")
                .tasklet(helloWorldStep1Tasklet())
                .build();
    }

    @Bean
    public Tasklet helloWorldStep1Tasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("헬로월드!");

            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step helloWorldStep2(){
        return stepBuilderFactory.get("helloWorldStep2")
                .tasklet(helloWorldStep2Tasklet()).build();
    }
    @Bean
    public Tasklet helloWorldStep2Tasklet(){
        return (contribution, chunkContext) -> {
            System.out.println("헬로월드2!");
            return RepeatStatus.FINISHED;
        };
    }


}
