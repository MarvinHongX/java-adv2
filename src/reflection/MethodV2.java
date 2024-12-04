package reflection;

import reflection.data.BasicData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodV2 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("== 정적 메서드 호출 ==");
        BasicData basicData = new BasicData();
        basicData.call();

        System.out.println("== 동적 메서드 호출 ==");
        Class<? extends BasicData> basicDataClass = basicData.getClass();

        String methodName = "hello"; // 메서드 이름을 동적으로 변경할 수 있다.

        Method method1 = basicDataClass.getDeclaredMethod(methodName, String.class);
        Object returnValue = method1.invoke(basicData, "h1");
        System.out.println("returnValue = " + returnValue);

    }
}
