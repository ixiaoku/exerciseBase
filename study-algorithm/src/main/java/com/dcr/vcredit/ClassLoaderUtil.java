package com.dcr.vcredit;

import java.net.URL;
import java.net.URLDecoder;

/**
 * @author Hao
 * @date 2017/4/25
 */
public class ClassLoaderUtil {

    private static ClassLoader loader = Thread.currentThread().getContextClassLoader();
    private static String classPath = "";

    static {
        if (loader == null) {
            loader = ClassLoader.getSystemClassLoader();
        }

        try {
            URL url = loader.getResource("");
            // get class path
            if (url != null) {
                classPath = url.getPath();
                classPath = URLDecoder.decode(classPath, "utf-8");
            }

            // 如果是jar包内的，则返回当前路径
            // String.isNullOrEmpty(classPath) ||
            if (classPath == null || classPath.length() == 0 || classPath.contains(".jar!")) {
                classPath = System.getProperty("user.dir");
            }
        } catch (Throwable ex) {
            classPath = System.getProperty("user.dir");
            ex.printStackTrace();
        }
    }

    public static ClassLoader getLoader() {
        return loader;
    }


    public static String getClassPath() {
        return classPath;
    }

    public static boolean isClassPresent(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
}
