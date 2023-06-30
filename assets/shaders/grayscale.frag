#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform mat4 u_projTrans;

uniform bool u_luminanceScale;
uniform float u_strength;

void main() {
    vec4 finalColor;
	vec4 color = texture2D(u_texture, v_texCoords);

    float gray;
    if (u_luminanceScale) {
        gray = dot(color.rgb, vec3(0.22, 0.707, 0.071));
    }
    else {
        gray = (color.r + color.g + color.b) / 3.0;
    }
    float clampedStrength = clamp(1.0 - u_strength, 0.0, 1.0);
    finalColor.rgb = mix(vec3(gray), color.rgb, clampedStrength);
    finalColor.a = color.a;

	gl_FragColor = finalColor;
}
