package com.wkk.lean.java.dynamic.data.source.aspect;

import com.wkk.lean.java.dynamic.data.source.annotation.DataSource;
import com.wkk.lean.java.dynamic.data.source.config.DataSourceEnum;
import com.wkk.lean.java.dynamic.data.source.config.DynamicDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @Description 数据源切面处理
 * @Author Wangkunkun
 * @Date 2020/12/2 22:33
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.wkk.lean.java.dynamic.data.source.annotation.DataSource)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSource ds = method.getAnnotation(DataSource.class);
        String dataSource = ds.value();
        if (StringUtils.isEmpty(dataSource)) {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getValue());
            System.out.println("dataSource : " + DataSourceEnum.MASTER.getValue());
        } else {
            DynamicDataSource.setDataSource(dataSource);
            System.out.println("dataSource : " + dataSource);
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }

    }
}

