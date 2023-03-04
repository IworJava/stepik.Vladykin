package part4_1;

public class Caller {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        System.out.println(a());

    }
    static String a() {
        return getCallerClassAndMethodName();
    }
    public static String getCallerClassAndMethodName() {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        return ste.length > 2
                ? ste[2].getClassName() + "#" + ste[2].getMethodName()
                : null;
    }
}
