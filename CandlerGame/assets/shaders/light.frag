#version 330 core

in vec4 v_color; // The input color from the vertex shader.
in vec2 v_texCoord; // The input texture coordinate from the vertex shader.

out vec4 FragColor; // The output color of the fragment.

uniform sampler2D u_texture; // The texture sampler.
uniform vec2 u_lightPos; // The position of the light source.
uniform vec3 u_lightColor; // The color of the light.
uniform float u_lightRadius; // The radius of the light.

void main()
{
    vec4 texColor = texture(u_texture, v_texCoord); // Sample the texture color.

    float dist = distance(gl_FragCoord.xy, u_lightPos); // Calculate the distance from the fragment to the light source.
    float intensity = 1.0 - smoothstep(u_lightRadius - 10.0, u_lightRadius, dist); // Calculate the light intensity.

    vec3 finalColor = texColor.rgb + u_lightColor * intensity; // Add the light color based on intensity.
    FragColor = vec4(finalColor, texColor.a); // Set the final fragment color.
}
