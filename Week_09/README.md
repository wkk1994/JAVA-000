# 学习笔记

## 第 17 课作业实践

### 3、（必做）改造自定义RPC的程序，提交到github： 

* 1）尝试将服务端写死查找接口实现类变成泛型和反射 
* 2）尝试将客户端动态代理改成AOP
  参考dubbo的BeanPostProcessor对注解Reference的处理，实现对RpcfxService注解属性的代理。
  
  [RpxfxAutoconfig.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/processor/RpxfxAutoconfig.java)
  [RpxfxBeanPostProcessor.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/processor/RpxfxBeanPostProcessor.java)
  [RpcfxCglib.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/client/RpcfxCglib.java)
  [QueryUserService.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-demo-consumer/src/main/java/io/kimmking/rpcfx/demo/consumer/QueryUserService.java)
  
* 3）尝试使用Netty+HTTP作为client端传输方式

  [NettyHttpClient.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/client/http/NettyHttpClient.java)
  [NettyClientHandler.java](https://github.com/wkk1994/JAVA-000/blob/main/rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/client/http/NettyClientHandler.java)