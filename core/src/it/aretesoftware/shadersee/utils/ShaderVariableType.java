package it.aretesoftware.shadersee.utils;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

public class ShaderVariableType {

    private final static ObjectMap<String, Integer> types;

    static {
        types = new ObjectMap<>();
        types.put("bool", 0);
        types.put("int", 1);
        types.put("float", 2);
        types.put("bvec2", 3);
        types.put("bvec3", 4);
        types.put("bvec4", 5);
        types.put("ivec2", 6);
        types.put("ivec3", 7);
        types.put("ivec4", 8);
        types.put("vec2", 9);
        types.put("vec3", 10);
        types.put("vec4", 11);
        types.put("mat2", 12);
        types.put("mat3", 13);
        types.put("mat4", 14);
        types.put("sampler2D", 15);
        types.put("samplerCube", 16);
        types.put("void", 17);
    }

    public static int valueOf(String name) {
        return types.get(name, -1);
    }

}
