package com.yang;


public class T23_Singleton5 {
 
    // 私有内部类，按需加载，用时加载，也就是延迟加载
    private static class Holder {
        private static T23_Singleton5 singleton5 = new T23_Singleton5();
    }
 
    private T23_Singleton5() {
 
    }
 
    public static T23_Singleton5 getSingleton5() {
        return Holder.singleton5;
    }
}