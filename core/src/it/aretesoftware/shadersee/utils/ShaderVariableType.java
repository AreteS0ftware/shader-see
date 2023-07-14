package it.aretesoftware.shadersee.utils;

import com.badlogic.gdx.utils.ObjectMap;

public class ShaderVariableType {

    private final static ObjectMap<String, Integer> types = new ObjectMap<>();

    public static final int BOOL = 0;
    public static final int INT = 1;
    public static final int FLOAT = 2;
    public static final int BVEC2 = 3;
    public static final int BVEC3 = 4;
    public static final int BVEC4 = 5;
    public static final int IVEC2 = 6;
    public static final int IVEC3 = 7;
    public static final int IVEC4 = 8;
    public static final int VEC2 = 9;
    public static final int VEC3 = 10;
    public static final int VEC4 = 11;
    public static final int MAT2 = 12;
    public static final int MAT3 = 13;
    public static final int MAT4 = 14;
    public static final int SAMPLER2D = 15;
    public static final int SAMPLERCUBE = 16;
    public static final int VOID = 17;

    static {
        types.put("bool", BOOL);
        types.put("int", INT);
        types.put("float", FLOAT);
        types.put("bvec2", BVEC2);
        types.put("bvec3", BVEC3);
        types.put("bvec4", BVEC4);
        types.put("ivec2", IVEC2);
        types.put("ivec3", IVEC3);
        types.put("ivec4", IVEC4);
        types.put("vec2", VEC2);
        types.put("vec3", VEC3);
        types.put("vec4", VEC4);
        types.put("mat2", MAT2);
        types.put("mat3", MAT3);
        types.put("mat4", MAT4);
        types.put("sampler2D", SAMPLER2D);
        types.put("samplerCube", SAMPLERCUBE);
        types.put("void", VOID);
    }

    public static int toInt(String name) {
        return types.get(name, -1);
    }

    public static String toString(int value) {
        return types.findKey(value, true);
    }

}
