package com.minis.test;

/**
 * @Title: AServiceImpl
 * @Package: com.minis.test
 * @Description:
 * @Author: Jinqiang.Jiao
 * @Date: 2025/5/6 - 15:57
 */
public class AServiceImpl implements AService {

    private String name;

    private int level;

    private String property1;

    private String property2;

    private BaseService ref1;

    public AServiceImpl() {}

    public AServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
        System.out.println(this.name+","+this.level);
    }

    @Override
    public void sayHello() {
        System.out.println(this.property1+","+this.property2);
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty1() {
        return this.property1;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public String getProperty2() {
        return this.property2;
    }

    public BaseService getRef1() {
        return ref1;
    }

    public void setRef1(BaseService ref1) {
        this.ref1 = ref1;
    }
}
