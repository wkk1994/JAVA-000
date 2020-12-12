package com.wkk.learn.java.springdemo.work03.parser;

import com.wkk.learn.java.springdemo.work03.entity.Student;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/11/17 22:34
 */
public class StudentParser extends AbstractBeanDefinitionParser {

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(Student.class);
        String id = element.getAttribute("_id");
        if(StringUtils.hasText(id)) {
            factory.addPropertyValue("id", Integer.valueOf(id));
        }
        String name = element.getAttribute("name");
        if(StringUtils.hasText(name)) {
            factory.addPropertyValue("name", name);
        }
        return factory.getBeanDefinition();
    }
}
