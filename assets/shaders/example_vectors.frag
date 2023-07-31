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

uniform bvec4 bvec4Uniform;
uniform ivec4 ivec4Uniform;     //uvec4 doesn't work
uniform vec4 vec4Uniform;

void main()
{
    vec4 color = v_color * texture2D(u_texture, v_texCoords);

    if (vec4Uniform.r != 0.0 || vec4Uniform.g != 0.0 || vec4Uniform.b != 0.0 || vec4Uniform.a != 0.0) {
        color = vec4Uniform * color;
    }

    if (bvec4Uniform.x) {
        color.x = float(ivec4Uniform.x) / 2.0;
    }
    if (bvec4Uniform.y) {
        color.y = float(ivec4Uniform.y) / 2.0;
    }
    if (bvec4Uniform.z) {
        color.z = float(ivec4Uniform.z) / 2.0;
    }
    if (bvec4Uniform.w) {
        color.w = float(ivec4Uniform.w) / 2.0;
    }

    gl_FragColor = color;
}
