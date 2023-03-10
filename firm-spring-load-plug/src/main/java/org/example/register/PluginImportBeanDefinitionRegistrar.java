package org.example.register;

/**
 * @author: hj
 * @date: 2023/2/15
 * @time: 5:10 PM
 */

import firm.plug.core.CustomPlugLoader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Modifier;

/**
 * 启动时注册bean核心类
 * 此类用于动态加载jar中的类进行自动注册进系统。
 * @author bask
 * @version 1.0
 * @date 2022/7/5
 * <p>
 */
@Slf4j
@Component
public class PluginImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    /**
     * 存储Jar文件基础路径
     */
    private String basePath;

    /**
     * 包名称集，多个名称则通过","逗号进行区分。
     */
    private String jarNames;
    /**
     * 包前缀，如：com.tsingsoft
     */
    private String packagePrefix;

    @SneakyThrows
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        CustomPlugLoader customPlugLoader = new CustomPlugLoader();
        Class<?> clazz = customPlugLoader.loadSingleJar("/Users/hejie/Desktop/myCode/firm-plug/firm-plug-business/Plugs/firm-plug-Impl.jar");
        registerBean(clazz, registry);


    }

    /**
     * 註冊BEAN
     * @param c
     * @param registry
     */
    private void registerBean(Class<?> c, BeanDefinitionRegistry registry) {
        String className = c.getSimpleName();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(c);
        BeanDefinition beanDefinition = builder.getBeanDefinition();

//        if (isSpringBeanClass(c)) {
            registry.registerBeanDefinition(className, beanDefinition);
//        }


    }

    /**
     * 方法描述 判断class对象是否带有spring的注解
     * 存放實現
     *
     * @param cla jar中的每一个class
     * @return true 是spring bean   false 不是spring bean
     * @method isSpringBeanClass
     */
    public boolean isSpringBeanClass(Class<?> cla) {

        if (cla == null) {
            return false;
        }
        //是否是接口
        if (cla.isInterface()) {
            return false;
        }
        //是否是抽象类
        if (Modifier.isAbstract(cla.getModifiers())) {
            return false;
        }
        try {
            if (cla.getAnnotation(Component.class) != null) {
                return true;
            }
        }catch (Exception e){
            log.error("出现异常：{}",e.getMessage());
        }

        try {
            if (cla.getAnnotation(Repository.class) != null) {
                return true;
            }
        }catch (Exception e){
            log.error("出现异常：{}",e.getMessage());
        }

        try {
            if (cla.getAnnotation(Service.class) != null) {
                return true;
            }
        }catch (Exception e){
            log.error("出现异常：{}",e.getMessage());
        }
        return false;
    }

    /**
     * 因加载顺序原因，则获取配置不用通过@Value来获取。
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.basePath = environment.getProperty("basePath");
        this.packagePrefix = environment.getProperty("packagePrefix");
        this.jarNames = environment.getProperty("jarNames");
    }

}