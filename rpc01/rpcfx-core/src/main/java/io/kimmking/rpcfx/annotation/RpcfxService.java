package io.kimmking.rpcfx.annotation;

import java.lang.annotation.*;

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcfxService {

    String url();

}
