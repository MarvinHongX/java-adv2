package anotation.java;

public class DeprecatedMain {
    public static void main(String[] args) {
        System.out.println("DeprecatedMain.main");
        DeprecatedClass deprecatedClass = new DeprecatedClass();
        deprecatedClass.call1();
        deprecatedClass.call2(); // IDE 경고
        deprecatedClass.call3(); // IDE 경고(심각)
    }
}
