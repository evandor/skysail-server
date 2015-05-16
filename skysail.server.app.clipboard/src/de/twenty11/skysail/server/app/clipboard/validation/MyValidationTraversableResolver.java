//package de.twenty11.skysail.server.app.clipboard.validation;
//
//import java.lang.annotation.ElementType;
//
//import javax.validation.Path;
//
//import org.hibernate.validator.internal.engine.resolver.DefaultTraversableResolver;
//
//public class MyValidationTraversableResolver extends DefaultTraversableResolver {
//
//    public MyValidationTraversableResolver() {
//        // we simply override the constructor to disable jpa detection
//    }
//
//    @Override
//    public boolean isReachable(Object traversableObject, Path.Node traversableProperty, Class<?> rootBeanType,
//            Path pathToTraversableObject, ElementType elementType) {
//        return true;
//    }
//
//    @Override
//    public boolean isCascadable(Object traversableObject, Path.Node traversableProperty, Class<?> rootBeanType,
//            Path pathToTraversableObject, ElementType elementType) {
//        return true;
//    }
//}
