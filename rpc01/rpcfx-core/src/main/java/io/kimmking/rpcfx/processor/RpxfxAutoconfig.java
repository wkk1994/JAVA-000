package io.kimmking.rpcfx.processor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description rpc自动配置类
 * @Author Wangkunkun
 * @Date 2020/12/16 22:15
 */
@Configuration
public class RpxfxAutoconfig {

    @Bean
    public RpxfxBeanPostProcessor beanPostProcessor() {
        return new RpxfxBeanPostProcessor();
    }
}
