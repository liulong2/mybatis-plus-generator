package com.liu.controller;

import com.liu.config.MpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Configuration
//@EnableConfigurationProperties({MpGenerator.class})
@ConditionalOnExpression("${mp.generator.enabled:false}")
public class MybatisPlusGenerator {

    @Autowired
    private MpGenerator mpGenerator;

    public MybatisPlusGenerator(){

    }

    @Value("${mp.name}")
    private String name;

    @Value("${mp.prefix}")
    private String prefix;

    @PostConstruct
    public void ceshi() throws Exception {
        mpGenerator.generateByTables(name,prefix);
    }

}
