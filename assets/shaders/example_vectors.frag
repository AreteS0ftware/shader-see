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

void main()
{
    vec4 color = texture2D(u_texture, v_texCoords);

    if (bvec4Uniform.x) {
        color.x = 0.0;
    }
    if (bvec4Uniform.y) {
        color.y = 0.0;
    }
    if (bvec4Uniform.z) {
        color.z = 0.0;
    }
    if (bvec4Uniform.w) {
        color.w = 0.5;
    }

    gl_FragColor = v_color * color;
}
