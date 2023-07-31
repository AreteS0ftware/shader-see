package it.aretesoftware.shadersee.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class ShaderVariableType {

    private final static ObjectMap<String, Integer> types = new ObjectMap<>();

    public static final int BOOL = 0;
    public static final int INT = 1;
    public static final int UINT = 2;
    public static final int FLOAT = 3;
    public static final int DOUBLE = 4;
    public static final int BVEC2 = 5;
    public static final int BVEC3 = 6;
    public static final int BVEC4 = 7;
    public static final int IVEC2 = 8;
    public static final int IVEC3 = 9;
    public static final int IVEC4 = 10;
    public static final int UVEC2 = 11;
    public static final int UVEC3 = 12;
    public static final int UVEC4 = 13;
    public static final int VEC2 = 14;
    public static final int VEC3 = 15;
    public static final int VEC4 = 16;
    public static final int MAT2 = 17;
    public static final int MAT3 = 18;
    public static final int MAT4 = 19;
    public static final int SAMPLER2D = 20;
    public static final int SAMPLERCUBE = 21;
    public static final int VOID = 22;

    static {
        types.put("bool", BOOL);
        types.put("int", INT);
        types.put("uint", UINT);
        types.put("float", FLOAT);
        types.put("double", DOUBLE);
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

    public static Array<String> getTypesAsStrings() {
        return types.keys().toArray();
    }

}
