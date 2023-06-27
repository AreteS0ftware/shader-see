Shaders behave slightly differently on Android compared to Desktop. 
This file documents all trouble I had with them and also the changes I applied to make them work.
- I'm not sure what GLSL version Android uses, but placing "#version 101" at the top of the shader crashes the game once it gets applied
	"'101' : version number not supported"
  So one can only assume the version is 1.0 or less.
- Likewise, placing "#version 110" at the top of the shader and starting the application on Desktop crashes the game once it gets applied
	"global function texture requires "#version 130" or later"
  Which implies Desktop's version is 1.3 or more, and that the "texture" function can only be used if the version is as described earlier.
- The "texture" function is not recognized as a function at all if the shader's version is <= 1.0 (i.e. running on Android); the game will
  crash with a message that implies "texture" not being a function, once the shader gets applied. 
  Although deprecated, the function "texture2D" is compatible with these older versions. Still, it's not good and I should find a way around this limitation.
- If a fragment shader defines precision like so
	"#ifdef GL_ES #define LOWP lowp precision mediump float; #else #define LOWP #endif"
  but the vertex shader doesn't, then the game will crash with the message
    "Error: uniform u_projTrans specified with different precision in different shaders."
  To fix this, have both shaders have the same precision, i.e. copy & paste the precision definition from the fragment shader to the vertex shader.
  This problem only occurs on Android, probably because of the low version number.
  I also haven't tested if it happens on reverse, i.e. having the error generate from a fragment shader uniform.
- It seems like you can omit the "#version <number>" at the top of the shader, irregardless of whether the application's 
  running on Desktop or Android; it picks automatically depending on the system.