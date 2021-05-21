package com.b514.webapp.util;

import com.b514.webapp.exception.NoCustomConvert;
import com.b514.webapp.model.AbstractModel;
import com.b514.webapp.model.WebVO;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Models {
    private Models() {

    }

    /**
     * 将model转化为HashMap
     * @param model
     * @return
     */
    public static HashMap<String, String> toHashMap(AbstractModel model) {
        try {
            return model.toHashMap();
        } catch (NoCustomConvert e) {

        }

        Field[] fields = model.getClass().getDeclaredFields();
        HashMap<String, String> map = new HashMap<>();
        for(Field field : fields) {
            try {
                field.setAccessible(true);
                String name = field.getName();
                String value = field.get(model).toString();
                map.put(name, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if(model instanceof WebVO) {
            fields = model.getClass().getSuperclass().getDeclaredFields();
            for(Field field : fields) {
                try {
                    field.setAccessible(true);
                    String name = field.getName();
                    String value = field.get(model).toString();
                    map.put(name, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return map;
    }

    /**
     * vo<->po转化，注意变量名和类型的一一对应。
     * @param source
     * @param target
     */
    public static void convert(AbstractModel source, AbstractModel target) {
        Field[] fields = target.getClass().getDeclaredFields();
        for(Field targetField : fields) {
            try {
                String name = targetField.getName();
                Field sourceField = source.getClass().getDeclaredField(name);
                targetField.setAccessible(true);
                sourceField.setAccessible(true);
                targetField.set(target, sourceField.get(source));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // e.printStackTrace();
            }
        }
    }

    /**
     * String调试
     * @param model
     * @return
     */
    public static String toString(AbstractModel model) {
        Field[] fields = model.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append(model.getClass().getName()).append("(");
        for(Field field : fields) {
            try {
                field.setAccessible(true);
                String name = field.getName();
                String value = field.get(model).toString();
                sb.append(name).append("=").append(value).append(" ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if(model instanceof WebVO) {
            fields = model.getClass().getSuperclass().getDeclaredFields();
            for(Field field : fields) {
                try {
                    field.setAccessible(true);
                    String name = field.getName();
                    String value = field.get(model).toString();
                    sb.append(name).append("=").append(value).append(" ");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }
}
