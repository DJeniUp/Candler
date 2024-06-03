#version 330 core

layout(location = 0) in vec4 a_position; // The position variable is the vertex position.
layout(location = 1) in vec4 a_color; // The color variable is the vertex color.
layout(location = 2) in vec2 a_texCoord0; // The input texture coordinate from the vertex data.

uniform mat4 u_projTrans; // Projection and transformation matrix.

out vec4 v_color; // Pass the color to the fragment shader.
out vec2 v_texCoord; // Pass the texture coordinate to the fragment shader.

void main()
{
    v_color = a_color; // Pass the vertex color to the fragment shader.
    v_texCoord = a_texCoord0; // Pass the texture coordinate to the fragment shader.
    gl_Position = u_projTrans * a_position; // Set the output position of the vertex.
}
