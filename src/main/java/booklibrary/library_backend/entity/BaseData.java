package booklibrary.library_backend.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.function.Consumer;

public interface BaseData {
    default <V> V asViewObj(Class<V> vClass, Consumer<V> consumer){
        V v=this.asViewObj(vClass);
        consumer.accept(v);
        return v;
    }
    default <V> V asViewObj(Class<V> vClass){
        try{
            Field[] declaredFields=vClass.getDeclaredFields();
            Constructor<V> constructor = vClass.getConstructor();
            V v=constructor.newInstance();
            for (Field declaredField : declaredFields) copy(declaredField,v);
            return v;
        }catch (ReflectiveOperationException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    private void copy(Field field,Object ViewObj){
        try{
            Field f=this.getClass().getDeclaredField(field.getName());
            field.setAccessible(true);
            f.setAccessible(true);
            field.set(ViewObj,f.get(this));
        }catch (IllegalAccessException|NoSuchFieldException ignored){}
    }
}
