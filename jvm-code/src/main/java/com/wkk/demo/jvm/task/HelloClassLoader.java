package com.wkk.demo.jvm.task;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @Description hello类加载器
 * @Author Wangkunkun
 * @Date 2020/10/20 22:46
 */
public class HelloClassLoader extends ClassLoader{

    /**
     * hello文件地址
     */
    private String helloPath;

    @Override
    protected Class<?> findClass(String name) {
        try {
            InputStream inputStream = new FileInputStream(new File(helloPath));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(bytes)) != -1) {
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) (255 - bytes[i]);
                }
                byteArrayOutputStream.write(bytes, 0, length);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return defineClass(name, byteArray, 0, byteArray.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)  {
        URL resource = HelloClassLoader.class.getResource("/task/Hello.xlass");
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        helloClassLoader.setHelloPath(resource.getPath());
        try {
            Class<?> aClass = helloClassLoader.loadClass("Hello");
            Method hello = aClass.getDeclaredMethod("hello");
            hello.invoke(aClass.newInstance());
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public String getHelloPath() {
        return helloPath;
    }

    public void setHelloPath(String helloPath) {
        this.helloPath = helloPath;
    }
}
