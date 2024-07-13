package com.ib.it.productservice.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServiceFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private final ServiceBindingsConfig serviceBindingsConfig;

    public ServiceFactory(ServiceBindingsConfig serviceBindingsConfig) {
        this.serviceBindingsConfig = serviceBindingsConfig;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        registerServices();
    }
    private void registerServices() {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
        List<ServiceBindingsConfig.Binding> bindings = serviceBindingsConfig.getBindings();

        for (ServiceBindingsConfig.Binding binding : bindings) {
            registerBean(registry, binding.getService(), binding.getImplementation(), binding.getScope());
        }
    }

    private void registerBean(BeanDefinitionRegistry registry, String serviceName, String implementationName, String scope) {
        try {
            Class<?> serviceClass = Class.forName(serviceName);
            Class<?> implementationClass = Class.forName(implementationName);

            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(implementationClass);
            beanDefinition.setScope(scope);
            String beanName = serviceClass.getName();

            registry.registerBeanDefinition(beanName, beanDefinition);
            // Register an alias for the custom qualifier
            System.out.println(serviceClass.getSimpleName());
            registry.registerAlias(beanName, serviceClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unknown class: " + serviceName + " or " + implementationName, e);
        }
    }
}
