package com.wkk.learn.java.springdemo.work02.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/11/17 21:29
 */
public class MyBeanFactoryAware implements BeanFactoryAware {

    private BeanFactory beanFactory;

    /**
     * Callback that supplies the owning factory to a bean instance.
     * <p>Invoked after the population of normal bean properties
     * but before an initialization callback such as
     * {@link InitializingBean#afterPropertiesSet()} or a custom init-method.
     *
     * @param beanFactory owning BeanFactory (never {@code null}).
     *                    The bean can immediately call methods on the factory.
     * @throws BeansException in case of initialization errors
     * @see BeanInitializationException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public School createSchool() {
        School school = new School();
        school.setStudent(beanFactory.getBean(Student.class));
        return school;
    }
}
