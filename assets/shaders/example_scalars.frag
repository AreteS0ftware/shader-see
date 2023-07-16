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

uniform bool booleanUniform;
uniform int uintUniform;     //uint doesn't work
uniform int intUniform;
uniform float floatUniform;
uniform float doubleUniform; //double doesn't work

void main()
{
    vec4 color = texture2D(u_texture, v_texCoords);
    if (booleanUniform) {
        color.a -= 0.5;
    }
    color.rgb += doubleUniform;

    gl_FragColor = v_color * color;
}
