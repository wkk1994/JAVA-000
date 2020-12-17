package io.kimmking.rpcfx;

import java.util.Objects;

/**
 * @Description 接口类信息
 * @Author Wangkunkun
 * @Date 2020/12/16 21:58
 */
public class ClassIdBean {

    private String url;

    private Class<?> interfaceClass;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public ClassIdBean(String url, Class<?> interfaceClass) {
        this.url = url;
        this.interfaceClass = interfaceClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassIdBean that = (ClassIdBean) o;
        if(!url.equals(that.url)) {
            return false;
        }
        if(interfaceClass == null && that.interfaceClass == null) {
            return true;
        }
        if(interfaceClass == null) {
            return false;
        }
        if(that.interfaceClass == null) {
            return false;
        }
        if(interfaceClass.getName().equals(that.interfaceClass.getName())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, interfaceClass);
    }
}
