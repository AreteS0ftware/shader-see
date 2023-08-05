uniform mat4 u_projTrans;

attribute vec4 a_position;
attribute vec2 a_texCoord0;
attribute vec4 a_color;

varying vec4 v_color;
varying vec2 v_texCoords;

uniform int uintUniform;     //uint doesn't work
uniform int intUniform;
uniform float floatUniform;

void main() {
    int number = uintUniform;
    if (number > 0) {
        number += 1;
    }
    float clampedUIntUniform = max(1.0, cos(floatUniform) * float(number));
    vec4 scale = vec4(clampedUIntUniform, clampedUIntUniform, clampedUIntUniform, clampedUIntUniform);

    mat4 displacement;
    displacement[0] = u_projTrans[0] * scale;
    displacement[1] = u_projTrans[1] * scale;
    displacement[2] = u_projTrans[2];
    displacement[3] = u_projTrans[3];

    vec4 translation = a_position;
    translation.xy += float(intUniform);
    
    gl_Position = displacement * translation;
    v_texCoords = a_texCoord0;
    v_color = a_color;
}
