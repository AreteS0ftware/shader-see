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

uniform bool useMatrix;
uniform mat3 mat3Uniform;

void main()
{
    vec4 finalColor = v_color * texture2D(u_texture, v_texCoords);

    vec3 column0 = mat3Uniform[0];
    vec3 column1 = mat3Uniform[1];
    vec3 column2 = mat3Uniform[2];
    if (useMatrix) {
       finalColor.r *= (column0.x + column0.y + column0.z) / 3.0;
       finalColor.g *= (column1.x + column1.y + column1.z) / 3.0;
       finalColor.b *= (column2.x + column2.y + column2.z) / 3.0;
    }

    gl_FragColor = finalColor;
}
