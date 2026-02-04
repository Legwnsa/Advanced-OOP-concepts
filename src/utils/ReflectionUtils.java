package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static void inspectClass(Class<?> clazz) {
        System.out.println("Class name: " + clazz.getName());
        System.out.println("Fields:");
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(" " + f.getName() + " : " + f.getType().getSimpleName());
        }

        System.out.println("Methods:");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(" " + m.getName());
        }
    }

    public static void  main(String[] args) {
        inspectClass(model.PrintedBook.class);
    }
}
