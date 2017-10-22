package com.nitesh.springbatchdemo.configuration;

import com.nitesh.springbatchdemo.model.Student;
import com.nitesh.springbatchdemo.service.SftpTasklet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@Slf4j
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    private SftpTasklet sftpTasklet;

    @Autowired
    public SpringBatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                             SftpTasklet sftpTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.sftpTasklet = sftpTasklet;
    }

    @Bean
    public StaxEventItemWriter<Student> customerItemWriter() throws Exception {

        XStreamMarshaller marshaller = new XStreamMarshaller();

        Map<String, Class> aliases = new HashMap<>();
        aliases.put("student", Student.class);

        marshaller.setAliases(aliases);
        StaxEventItemWriter<Student> itemWriter = new StaxEventItemWriter<>();

        itemWriter.setRootTagName("students");
        itemWriter.setMarshaller(marshaller);
//        String studentOutputPath = File.createTempFile("students", ".xml").getAbsolutePath();
        File studentFile = new File("D:\\student.xml");
        boolean fileCreated = studentFile.createNewFile();

        if (!fileCreated) {
            boolean delete = studentFile.delete();
            studentFile.createNewFile();
        }

        String studentOutputPath = studentFile.getAbsolutePath();
        System.out.println(studentOutputPath);
        System.out.println(">> Output Path: " + studentOutputPath);
        itemWriter.setResource(new FileSystemResource(studentOutputPath));

        itemWriter.afterPropertiesSet();
        return itemWriter;
    }


    @Bean
    public ItemReader<Student> studentReader() {
        return new StudentReader();
    }


    @Bean
    public Step fetchRecord() throws Exception {
        log.info("FetchRecord Step has been executed");
        return stepBuilderFactory.get("fetchRecord")
                .<Student, Student>chunk(10)
                .reader(studentReader())
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public Step sftpFileSendStep() {
        return stepBuilderFactory.get("sftpFileSendStep")
                .tasklet(sftpTasklet)
                .build();
    }

    @Bean
    public Job job() throws Exception {
        log.info("Job is Starting");
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .start(fetchRecord())
                .next(sftpFileSendStep())
                .build();
    }


}
