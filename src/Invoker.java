import java.lang.reflect.Method;

public class Invoker {
    public static void main(String[] args) {
        try {
            MyClass obj = new MyClass();
            Method[] methods = MyClass.class.getDeclaredMethods();

            System.out.println("=== Вызов аннотированых методов ===");
            invokeAnnotatedMethods(obj, methods);
            System.out.println("\n=== Вызов завершен ===");

        }
        catch (Exception e) {
            System.err.println("Ошибка Invoker: " + e.getMessage());
        }
    }

    /**
     * Вызывает все методы с аннотацией @Repeat
     */
    private static void invokeAnnotatedMethods(Object obj, Method[] methods) throws Exception {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Repeat.class)) {
                processAnnotatedMethod(obj, method);
            }
        }
    }

    /**
     * Обрабатывает отдельный аннотированный метод
     */
    private static void processAnnotatedMethod(Object obj, Method method) throws Exception {
        Repeat repeatAnnotation = method.getAnnotation(Repeat.class);
        int times = repeatAnnotation.times();

        System.out.println("\nМетод: " + method.getName() + ", аннотация @Repeat(times=" + times + ")");

        method.setAccessible(true);
        invokeMethodMultipleTimes(obj, method, times);
    }

    /**
     * Вызывает метод указанное количество раз
     */
    private static void invokeMethodMultipleTimes(Object obj, Method method, int times) throws Exception {
        for (int i = 0; i < times; i++) {
            System.out.print("Вызов " + (i + 1) + ": ");
            invokeMethodWithParameters(obj, method);
        }
    }

    /**
     * Вызывает метод с обработкой параметров
     */
    private static void invokeMethodWithParameters(Object obj, Method method) throws Exception {
        if (method.getParameterCount() == 0) {
            method.invoke(obj);
        }
        else {
            Object[] params = createParametersForMethod(method);
            method.invoke(obj, params);
        }
    }

    /**
     * Создает параметры для вызова метода
     */
    private static Object[] createParametersForMethod(Method method) {
        Class<?>[] paramTypes = method.getParameterTypes();
        Object[] params = new Object[paramTypes.length];

        for (int j = 0; j < paramTypes.length; j++) {
            params[j] = getDefaultValueForType(paramTypes[j], j);
        }
        return params;
    }

    /**
     * Возвращает значение по умолчанию для типа параметра
     */
    private static Object getDefaultValueForType(Class<?> paramType, int index) {
        if (paramType == String.class) {
            return "test";
        }
        else if (paramType == int.class) {
            return index + 1;
        }
        return null;
    }
}