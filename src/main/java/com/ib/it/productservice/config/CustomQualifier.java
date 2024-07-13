package com.ib.it.productservice.config;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*A custom qualifier annotation in Spring is a way to create more specific and descriptive annotations for
differentiating beans, especially when there are multiple beans of the same type. This allows you
to manage and inject the correct beans more precisely.
Create a custom annotation that will act as a qualifier.
This annotation should be annotated with @Qualifier to inherit its functionality.
@Target specifies where the annotation can be applied (fields, parameters, methods).
@Retention defines how long the annotation will be retained (runtime).
@Qualifier makes this annotation act as a qualifier.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface CustomQualifier {
    String value();
}