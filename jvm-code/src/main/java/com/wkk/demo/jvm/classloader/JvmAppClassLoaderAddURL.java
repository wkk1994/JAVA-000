package com.wkk.demo.jvm.classloader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Description 运行时动态添加类加载路径
 * @Author Wangkunkun
 * @Date 2020/10/18 21:37
 */
public class JvmAppClassLoaderAddURL {

    public static void main(String[] args) {
        String appPath = "file:/Users/xujinxiu/Desktop/";
        ClassLoader classLoader = JvmAppClassLoaderAddURL.class.getClassLoader();
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(appPath);
            addURL.invoke(classLoader, url);
            Class.forName("HelloByteCode");
        } catch (NoSuchMethodException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
