package com.cyh.despat;

public class Singleton {
    public static void main(String[] args) {

    }
}
// 饿汉式单例
class HungrySingleton {
    // 私有静态成员变量
    private static final HungrySingleton singleton = new HungrySingleton();
    // 构造器私有
    private HungrySingleton() {
    }
    // 返回单例的方法
    public static HungrySingleton getSingleton() {
        return singleton;
    }
}

// 懒汉式单例
class LazySingleton {
    // 私有静态成员变量
    private static volatile LazySingleton singleton;

    // 构造器私有
    private LazySingleton() {
    }

    // 返回单例的方法
    public LazySingleton getSingleton() {
        if (singleton != null) {
            return singleton;
        } else {
            synchronized (LazySingleton.class) {
                if (singleton != null) {
                    return singleton;
                } else {
                    return singleton = new LazySingleton();  // 释放锁之前将引用指向实例对象  否则会发生锁的时序问题
                }
            }
        }
    }
}
