package com.ib.it.productservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "service")
public class ServiceBindingsConfig {
    private List<Binding> bindings;

    @Getter
    @Setter
    public static class Binding {
        private String service;
        private String implementation;
        private String scope;
    }

    public List<Binding> getBindings() {
        return bindings;
    }

    public void setBindings(List<Binding> bindings) {
        this.bindings = bindings;
    }
}
