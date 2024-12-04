package anotation.basic.inherited;

import java.lang.annotation.Annotation;

public class InheritedMain {
    public static void main(String[] args) {
        print(Parent.class);
        print(Child.class);
        print(TestInterface.class);
        print(TestInterfaceImpl.class); // 인터페이스의 구현체는 애노테이션을 상속하지 않음
    }

    private static void print(Class<?> clazz) {
        System.out.println("class = " + clazz);
        for (Annotation annotation : clazz.getAnnotations()) {
            System.out.println(" - " + annotation.annotationType().getSimpleName());
        }
        System.out.println();
    }
}
