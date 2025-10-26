public class MyClass {
    public void publicMethod1() {
        System.out.println("Вызван publicMethod1");
    }

    public String publicMethod2(String name) {
        String result = "Привет, " + name + "!";
        System.out.println(result);
        return result;
    }

    @Repeat(times = 2)
    protected void protectedMethod1() {
        System.out.println("Вызван protectedMethod1");
    }

    @Repeat(times = 3)
    protected int protectedMethod2(int a, int b) {
        int result = a + b;
        System.out.println("protectedMethod2: " + a + " + " + b + " = " + result);
        return result;
    }

    @Repeat(times = 4)
    private void privateMethod1() {
        System.out.println("Вызван privateMethod1");
    }

    @Repeat(times = 2)
    private String privateMethod2(String str, int count) {
        String result = str.repeat(count);
        System.out.println("privateMethod2: " + str + " × " + count + " = " + result);
        return result;
    }

    private void methodWithoutAnnotation() {
        System.out.println("Этот метод не должен вызываться");
    }
}