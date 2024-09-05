package org.example;

import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.util.*;

public class MicroSpringBoot {
    private static Map<String, Method> getMappings = new HashMap<>();
    private static Map<String, Object> controllerInstances = new HashMap<>();

    public static void main(String[] args) throws Exception {
        scanClasspathForControllers();
        SimpleHttpServer.startServer();
    }

    private static void scanClasspathForControllers() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("");
        if (resource != null) {
            File directory = new File(resource.toURI());
            scanDirectory(directory, "");
        }
    }

    private static void scanDirectory(File directory, String packageName) throws Exception {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                scanDirectory(file, packageName + file.getName() + ".");
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + file.getName().replace(".class", "");
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(RestController.class)) {
                    Object controllerInstance = clazz.getDeclaredConstructor().newInstance();
                    controllerInstances.put(clazz.getName(), controllerInstance);
                    for (Method method : clazz.getDeclaredMethods()) {
                        if (method.isAnnotationPresent(GetMapping.class)) {
                            GetMapping getMapping = method.getAnnotation(GetMapping.class);
                            getMappings.put(getMapping.value(), method);
                        }
                    }
                }
            }
        }
    }

    public static String handleGetRequest(String uri) throws Exception {
        String path = uri.split("\\?")[0];
        Method method = getMappings.get(path);
        if (method != null) {
            Object controllerInstance = controllerInstances.get(method.getDeclaringClass().getName());
            if (uri.contains("?")) {
                String queryString = uri.split("\\?")[1];
                Map<String, String> queryParams = parseQueryString(queryString);
                Parameter[] parameters = method.getParameters();
                Object[] args = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
                    if (requestParam != null) {
                        String paramName = requestParam.value();
                        String defaultValue = requestParam.defaultValue();
                        args[i] = queryParams.getOrDefault(paramName, defaultValue);
                    }
                }
                return (String) method.invoke(controllerInstance, args);
            } else {
                return (String) method.invoke(controllerInstance);
            }
        }
        return "404 Not Found";
    }

    private static Map<String, String> parseQueryString(String queryString) {
        Map<String, String> queryParams = new HashMap<>();
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length > 1) {
                queryParams.put(keyValue[0], keyValue[1]);
            } else {
                queryParams.put(keyValue[0], "");
            }
        }
        return queryParams;
    }
}