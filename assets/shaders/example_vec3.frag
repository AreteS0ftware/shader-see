#ifdef GL_ES
#define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform mat4 u_projTrans;

uniform bvec3 bvec3Uniform;
uniform ivec3 ivec3Uniform;     //uvec3 doesn't work
uniform vec3 vec3Uniform;

void main()
{
    vec4 color = v_color * texture2D(u_texture, v_texCoords);

    if (vec3Uniform.r != 0.0 || vec3Uniform.g != 0.0 || vec3Uniform.b != 0.0) {
        color = vec4(vec3Uniform, 1.0) * color;
    }

    if (bvec3Uniform.x) {
        color.x = float(ivec3Uniform.x) * 0.1;
    }
    if (bvec3Uniform.y) {
        color.y = float(ivec3Uniform.y) * 0.1;
    }
    if (bvec3Uniform.z) {
        color.z = float(ivec3Uniform.z) * 0.1;
    }

    gl_FragColor = color;
}
