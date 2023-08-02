#ifdef GL_ES
#define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;

uniform mat4 u_projTrans;
uniform sampler2D u_texture;

uniform sampler2D u_texture2;
uniform sampler2D u_texture3;
uniform float mixAmount;

void main()
{
    vec4 color1 = texture2D(u_texture, v_texCoords);
    vec4 color2 = texture2D(u_texture2, v_texCoords);
    vec4 color3 = texture2D(u_texture3, v_texCoords);
    
    float clampedMixAmount = min(1.0, mixAmount);
    vec4 firstMix = mix(color1, color2, clampedMixAmount);
    vec4 secondMix = mix(color3, firstMix, clampedMixAmount);

    gl_FragColor = secondMix;
}
