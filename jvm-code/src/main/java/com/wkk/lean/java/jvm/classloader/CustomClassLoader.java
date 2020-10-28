package com.wkk.lean.java.jvm.classloader;

import java.io.*;
import java.util.Base64;

/**
 * @Description 自定义ClassLoader
 * @Author Wangkunkun
 * @Date 2020/10/18 19:42
 */
public class CustomClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String helloClassBase64 = "yv66vgAAADQAJAoACAAVBwAWCgACABUJABcAGAgAGQoAGgAbBwAcBwAdAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBAC9MY29tL3dray9kZW1vL2p2bS9jbGFzc2xvYWRlci9IZWxsb0NsYXNzTG9hZGVyOwEAD3JlbHlDbGFzc0xvYWRlcgEAM0xjb20vd2trL2RlbW8vanZtL2NsYXNzbG9hZGVyL0hlbGxvUmVseUNsYXNzTG9hZGVyOwEACDxjbGluaXQ+AQAKU291cmNlRmlsZQEAFUhlbGxvQ2xhc3NMb2FkZXIuamF2YQwACQAKAQAxY29tL3dray9kZW1vL2p2bS9jbGFzc2xvYWRlci9IZWxsb1JlbHlDbGFzc0xvYWRlcgcAHgwAHwAgAQASSGVsbG8gQ2xhc3NMb2FkZXIhBwAhDAAiACMBAC1jb20vd2trL2RlbW8vanZtL2NsYXNzbG9hZGVyL0hlbGxvQ2xhc3NMb2FkZXIBABBqYXZhL2xhbmcvT2JqZWN0AQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOwEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWACEABwAIAAAAAAACAAEACQAKAAEACwAAAEkAAgACAAAADSq3AAG7AAJZtwADTLEAAAACAAwAAAAOAAMAAAAOAAQADwAMABAADQAAABYAAgAAAA0ADgAPAAAADAABABAAEQABAAgAEgAKAAEACwAAACUAAgAAAAAACbIABBIFtgAGsQAAAAEADAAAAAoAAgAAAAsACAAMAAEAEwAAAAIAFA==";
        byte[] decode = Base64.getDecoder().decode(helloClassBase64);
        return defineClass(name, decode, 0, decode.length);
    }

    public static void main(String[] args) throws Exception {
        //getByte();
        Class<?> aClass = new CustomClassLoader().findClass("com.wkk.demo.jvm.classloader.HelloClassLoader");
        aClass.newInstance();
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass.getClassLoader().getParent());
        System.out.println(HelloRelyClassLoader.class.getClassLoader());
    }

    /**
     * 获取base64编码的字节码文件
     * @throws Exception
     */
    public static void getByte() throws Exception {
        InputStream inputStream = new FileInputStream(new File("/Users/xujinxiu/Documents/project/my_code/JAVA-000/jvm-code/target/classes/com/wkk/demo/jvm/classloader/HelloClassLoader.class"));
        byte[] bytes = new byte[1024];
        int length = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((length = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0 , length);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byte[] encode = Base64.getEncoder().encode(byteArray);
        String s = new String(encode, "utf-8");
        System.out.println(s);
    }
}
