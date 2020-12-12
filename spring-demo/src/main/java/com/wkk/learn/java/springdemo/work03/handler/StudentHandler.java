package com.wkk.learn.java.springdemo.work03.handler;

import com.wkk.learn.java.springdemo.work03.parser.KlassParser;
import com.wkk.learn.java.springdemo.work03.parser.StudentParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/11/17 22:33
 */
public class StudentHandler extends NamespaceHandlerSupport {
    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after
     * construction but before any custom elements are parsed.
     *
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     */
    @Override
    public void init() {
        registerBeanDefinitionParser("student", new StudentParser());
        registerBeanDefinitionParser("klass", new KlassParser());
    }
}
