package com.lighsync.worldofmilk.utils;

import com.lighsync.worldofmilk.utils.annotations.AutomaticSerialization;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NBTHelper {
    public static CompoundTag serialize(Object instance) {
        CompoundTag compoundTag = new CompoundTag();
        Class<?> clazz = instance.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(AutomaticSerialization.class)) {
                AutomaticSerialization annot = (AutomaticSerialization) field.getAnnotation(AutomaticSerialization.class);
                String key = annot.value().isEmpty() ? field.getName() : annot.value();

                try {
                    Object value = field.get(instance);
                    if (value instanceof Integer) {
                        compoundTag.putInt(key, (Integer) value);
                    } else if (value instanceof String) {
                        compoundTag.putString(key, (String) value);
                    } else if (value instanceof Boolean) {
                        compoundTag.putBoolean(key, (Boolean) value);
                    } else if (value instanceof List) {
                        ListTag listTag = new ListTag();
                        for (Object object : (List) value) {
                            if (object instanceof NBTSerializing) {
                                listTag.add(((NBTSerializing) object).serializeNBT());
                            } else if (object instanceof Enum) {
                                listTag.add(StringTag.valueOf(((Enum) object).name()));
                            }
                        }
                        compoundTag.put(key, listTag);
                    } else if (!(value instanceof Map)) {
                        if (value instanceof Enum) {
                            compoundTag.putString(key, ((Enum)value).name());
                        }
                    } else {
                        CompoundTag compoundTag1 = new CompoundTag();
                        Map<?, ?> map = (Map) value;

                        for (Map.Entry<?, ?> entry : map.entrySet()) {
                            String mapKey = entry.getKey().toString();
                            Object mapVal = entry.getValue();
                            if (mapVal instanceof NBTSerializing) {
                                compoundTag1.put(mapKey, ((NBTSerializing)mapVal).serializeNBT());
                            } else if (mapVal instanceof Enum) {
                                compoundTag1.put(mapKey, StringTag.valueOf(((Enum) mapVal).name()));
                            }
                        }
                        compoundTag.put(key, compoundTag1);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return compoundTag;
    }

    public static void deserialize(Object instance, CompoundTag compoundTag) {
        Class<?> clazz = instance.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(AutomaticSerialization.class)) {
                AutomaticSerialization annot = (AutomaticSerialization) field.getAnnotation(AutomaticSerialization.class);
                String key = annot.value().isEmpty() ? field.getName() : annot.value();

                try {
                    if (compoundTag.contains(key)) {
                        Class<?> type = field.getType();
                        if (type != Integer.TYPE && type != Integer.class) {
                            if (type != Boolean.TYPE && type != Boolean.class) {
                                if (type == String.class) {
                                    field.set(instance, compoundTag.getString(key));
                                } else if (List.class.isAssignableFrom(type)) {
                                    ListTag listTag = compoundTag.getList(key, 10);
                                    List<Object> list = new ArrayList();
                                    Class<?> type1 = getGenericComponent(field);

                                    for (Tag tag : listTag) {
                                        if (tag instanceof StringTag) {
                                            String name = ((StringTag) tag).getAsString();
                                            if (type1 != null && type1.isEnum()) {
                                                list.add(Enum.valueOf((Class<? extends Enum>) type1, name));
                                            }
                                        } else if (tag instanceof CompoundTag) {
                                            Object item = createInstance(type1);
                                            if (item instanceof NBTSerializing) {
                                                ((NBTSerializing) item).deserializeNBT((CompoundTag) tag);
                                            }
                                        }
                                    }
                                    field.set(instance, list);
                                } else if (!Map.class.isAssignableFrom(type)) {
                                    if (type.isEnum()) {
                                        String name = compoundTag.getString(key);
                                        field.set(instance, Enum.valueOf((Class<? extends Enum>)type, name));
                                    }
                                } else {
                                    CompoundTag compoundTag1 = compoundTag.getCompound(key);
                                    Map<Object, Object> map = new HashMap<>();
                                    Class<?> keyType = getGenericMapKeyType(field);
                                    Class<?> valType = getGenericMapValType(field);

                                    for (String mapKey : compoundTag1.getAllKeys()) {
                                        Tag mapValTag = compoundTag1.get(mapKey);
                                        if (mapValTag instanceof StringTag) {
                                            String name = ((StringTag) mapValTag).getAsString();
                                            if (keyType != null && keyType.isEnum()) {
                                                map.put(mapKey, Enum.valueOf((Class<? extends Enum>)keyType, name));
                                            }
                                        } else if (mapValTag instanceof CompoundTag) {
                                                Object value = createInstance(valType);
                                                if (value instanceof NBTSerializing) {
                                                    ((NBTSerializing) value).deserializeNBT((CompoundTag) mapValTag);
                                                    map.put(mapKey, value);
                                                }
                                        }
                                    }
                                    field.set(instance, map);
                                }
                            } else {
                                field.set(instance, compoundTag.getBoolean(key));
                            }
                        } else {
                            field.set(instance, compoundTag.getInt(key));
                        }
                    }
                } catch(IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Nullable
    private static Object createInstance(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    private static Class<?> getGenericMapValType(Field field) {
        Type generic = field.getGenericType();
        if (generic instanceof ParameterizedType parType) {
            Type[] actualTypeArgs = parType.getActualTypeArguments();
            if (actualTypeArgs.length > 1) {
                Type typeArg = actualTypeArgs[1];
                if (typeArg instanceof Class) {
                    return (Class) typeArg;
                }
            }
        }
        return null;
    }

    private static Class<?> getGenericMapKeyType(Field field) {
        Type generic = field.getGenericType();
        if (generic instanceof ParameterizedType parType) {
            Type[] actualTypeArgs = parType.getActualTypeArguments();
            if (actualTypeArgs.length > 0) {
                Type typeArg = actualTypeArgs[0];
                if (typeArg instanceof Class) {
                    return (Class) typeArg;
                }
            }
        }
        return null;
    }

    private static Class<?> getGenericComponent(Field field) {
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType paramType) {
            Type[] actualTypeArgs = paramType.getActualTypeArguments();
            if (actualTypeArgs.length > 0) {
                Type typeArg = actualTypeArgs[0];
                if (typeArg instanceof Class) {
                    return (Class) typeArg;
                }
            }
        }
        return null;
    }

    public static CompoundTag writeVec3f(Vector3f vec3f) {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putFloat("x", vec3f.x);
        compoundTag.putFloat("y", vec3f.y);
        compoundTag.putFloat("z", vec3f.z);
        return compoundTag;
    }

    public static Vector3f readVec3f(CompoundTag compoundTag) {
        return new Vector3f(
                compoundTag.getFloat("x"),
                compoundTag.getFloat("y"),
                compoundTag.getFloat("z")
        );
    }
}
