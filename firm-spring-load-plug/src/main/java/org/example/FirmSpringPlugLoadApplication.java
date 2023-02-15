package org.example;

import cn.hutool.extra.spring.SpringUtil;
import firm.plug.common.FirmPlug;
import org.example.register.PluginImportBeanDefinitionRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
@Import(PluginImportBeanDefinitionRegistrar.class)
public class FirmSpringPlugLoadApplication {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        SpringApplication.run(FirmSpringPlugLoadApplication.class, args);
    }


    @PostConstruct
    public void init() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        FirmPlug firmPluginImpl = SpringUtil.getBean(FirmPlug.class);
        firmPluginImpl.doPlug();
    }

}
