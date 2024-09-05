package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpringECI {

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (args.length == 0) {
            System.out.println("No service class provided.");
            return;
        }

        String serviceClassName = args[0];
        Map<String, Method> services = new HashMap<>();

        // Load the provided service class
        Class<?> c = Class.forName(serviceClassName);
        if (c.isAnnotationPresent(RestController.class)) {
            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(GetMapping.class)) {
                    String key = m.getAnnotation(GetMapping.class).value();
                    services.put(key, m);
                }
            }
        }

        // Hardcoded URLs for example
        String[] urls = {
            "http://localhost:8080/App/hello1",
            "http://localhost:8080/App/goodbye",
            "http://localhost:8080/App/welcome"
        };

        for (String urlString : urls) {
            URL serviceUrl = new URL(urlString);
            String path = serviceUrl.getPath();
            String serviceName = path.substring("/App".length());

            Method ms = services.get(serviceName);
            if (ms != null) {
                Object instance = ms.getDeclaringClass().getDeclaredConstructor().newInstance();
                System.out.println(ms.invoke(instance));
            }
        }
    }
}