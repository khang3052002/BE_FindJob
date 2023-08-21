package backend.findjob.helper;

import java.lang.reflect.Field;

public class GenericConverter {
    public static <T, U> U convert(T source, Class<U> targetClass) throws Exception {
        U target = targetClass.getDeclaredConstructor().newInstance();
        convert(source, target);
        return target;
    }
    public static <T, U> U convert(T source, U target) throws Exception {

        Class<?> sourceClass = source.getClass();
        Field[] sourceFields = sourceClass.getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        if(sourceClass.getSuperclass() != null){
            for (Field sourceField : sourceClass.getSuperclass().getDeclaredFields()) {
                String fieldName = sourceField.getName();
                Field targetField = findFieldByName(targetFields, fieldName);

                if (targetField != null) {
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);
                    Object fieldValue = sourceField.get(source);
                    targetField.set(target, fieldValue);
                }
            }
        }


        for (Field sourceField : sourceFields) {
            String fieldName = sourceField.getName();
            Field targetField = findFieldByName(targetFields, fieldName);

            if (targetField != null) {
                sourceField.setAccessible(true);
                targetField.setAccessible(true);
                Object fieldValue = sourceField.get(source);
                targetField.set(target, fieldValue);
            }
        }

        return target;
    }

    private static Field findFieldByName(Field[] fields, String fieldName) {
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }
}
