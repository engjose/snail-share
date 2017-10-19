package com.snail.baselibrary.config;


/**
 * APP全局Config
 */
public class Config {
    private static int ENV = Environment.PRODUCT;

    /**
     * 获得环境
     *
     * @return
     */
    public static int getENV() {
        return ENV;
    }

    /**
     * 切换环境
     *
     * @param env
     */
    public static void changeEnv(int env) {
        ENV = env;
    }
}