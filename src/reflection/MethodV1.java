package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Method;

public class MethodV1 {
    public static void main(String[] args) {
        Class<BasicData> basicDataClass = BasicData.class;
        System.out.println("== methods() ==");
        Method[] methods = basicDataClass.getMethods(); // 해당 클래스 + 상속 받은 클래스의 모든 public method 반환
        for (Method method : methods) {
            System.out.println("method = " + method);
        }

        System.out.println("== declared methods() =="); // 해당 클래스의 모든 method 반환 (접근 제어자 무관, 상속 받은 클래스는 제외)
        Method[] declaredMethods = basicDataClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("declared method = " + declaredMethod);
        }
    }
}
