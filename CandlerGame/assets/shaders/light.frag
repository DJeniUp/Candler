#ifdef GL_ES
precision mediump float;
#endif

uniform vec2 u_resolution;
uniform vec2 u_mouse;
uniform float u_time;

varying vec2 v_texCoord;

void main() {
    vec2 st = gl_FragCoord.xy / u_resolution.xy;
    vec3 color = vec3(0.0);

    // Distance from the center
    float d = distance(st, u_mouse / u_resolution);

    // Create a circular light effect
    color = vec3(1.0 - d);

    gl_FragColor = vec4(color, 1.0);
}
