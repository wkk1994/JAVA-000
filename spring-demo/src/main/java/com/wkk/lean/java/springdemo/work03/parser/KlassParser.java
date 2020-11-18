package com.wkk.lean.java.springdemo.work03.parser;

import com.wkk.lean.java.springdemo.work03.entity.Klass;
import com.wkk.lean.java.springdemo.work03.entity.Student;
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
public class KlassParser extends AbstractBeanDefinitionParser {

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(Klass.class);
        String students = element.getAttribute("students");
        if(StringUtils.hasText(students)) {
            for (String studentName : students.split(",")) {
                factory.addPropertyReference("students", studentName);
            }
        }
        return factory.getBeanDefinition();
    }
}
