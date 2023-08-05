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

uniform bvec2 bvec2Uniform;
uniform ivec2 ivec2Uniform;     //uvec2 doesn't work
uniform vec2 vec2Uniform;

void main()
{
    vec4 color = v_color * texture2D(u_texture, v_texCoords);

    if (vec2Uniform.r != 0.0 || vec2Uniform.g != 0.0) {
        color = vec4(vec2Uniform, 1.0, 1.0) * color;
    }

    if (bvec2Uniform.x) {
        color.x = float(ivec2Uniform.x) * 0.1;
    }
    if (bvec2Uniform.y) {
        color.y = float(ivec2Uniform.y) * 0.1;
    }

    gl_FragColor = color;
}
