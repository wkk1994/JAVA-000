package com.wkk.learn.java.jvm.classloader;

import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * @Description 输出类加载器加载的jar列表
 * @Author Wangkunkun
 * @Date 2020/10/18 20:25
 */
public class JvmClassLoaderPrintPath {

    public static void main(String[] args) {
        // 启动类加载器
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for (URL urL : urLs) {
            System.out.println("==> " + urL.toExternalForm());
        }
        // 扩展类加载器
        System.out.println("扩展类加载器");
        printClassLoader("扩展类加载器", JvmClassLoaderPrintPath.class.getClassLoader().getParent());
        printClassLoader1("扩展类加载器", (URLClassLoader) JvmClassLoaderPrintPath.class.getClassLoader().getParent());
        // 启动类加载器
        System.out.println("启动类加载器");
        printClassLoader("启动类加载器", JvmClassLoaderPrintPath.class.getClassLoader());
        printClassLoader1("启动类加载器", (URLClassLoader) JvmClassLoaderPrintPath.class.getClassLoader());

    }

    public static void printClassLoader(String name, ClassLoader classLoader) {
        if(classLoader != null) {
            System.out.println(name + " ClassLoader -> " + classLoader);
            printURLForClassLoader(classLoader);
        }else {
            System.out.println(name + " ClassLoader ‐> null");
        }

    }


    private static void printURLForClassLoader(ClassLoader classLoader) {
        Object ucp = insightField(classLoader,"ucp");
        Object path = insightField(ucp,"path");
        ArrayList ps = (ArrayList) path;
        for (Object p : ps){
            System.out.println(" ==> " + p.toString());
        }
    }

    private static Object insightField(Object object, String fName) {
        Field field = null;
        try {
            if (object instanceof URLClassLoader) {

                field = URLClassLoader.class.getDeclaredField(fName);

            } else {
                field = object.getClass().getDeclaredField(fName);

            }
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void printClassLoader1(String name, URLClassLoader urlClassLoader) {
        if(urlClassLoader != null) {
            System.out.println(name + " ClassLoader -> " + urlClassLoader);
            URL[] urLs = urlClassLoader.getURLs();
            for (URL urL : urLs) {
                System.out.println("==> " + urL.toExternalForm());
            }
        }

    }

}
