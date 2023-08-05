attribute vec4 a_position;
attribute vec2 a_texCoord0;
attribute vec4 a_color;

varying vec4 v_color;
varying vec2 v_texCoords;

uniform bool useProjTrans2;
uniform mat4 u_projTrans;
uniform mat4 u_projTrans2;

float average(vec4 v);

void main() {
    mat4 proj;
    if (useProjTrans2) {
        proj = u_projTrans2;
    }
    else {
        proj = u_projTrans;
    }

    gl_Position = proj * a_position;
    v_texCoords = a_texCoord0;
    v_color = a_color;
}

float average(vec4 v) {
    return (v.r + v.g + v.b + v.a) / 4.0;
}
