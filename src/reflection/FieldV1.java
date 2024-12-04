package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Field;

public class FieldV1 {
    public static void main(String[] args) {
        Class<BasicData> basicDataClass = BasicData.class;

        System.out.println("== fields ==");
        Field[] fields = basicDataClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("== declared fields ==");
        Field[] declaredFields = basicDataClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);
        }
    }
}
