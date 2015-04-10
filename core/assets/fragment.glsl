    #ifdef GL_ES
    #define LOWP lowp
    #define MEDP mediump
    #define HIGP highp
    precision lowp float;
    #else
    #define LOWP
    #define MEDP
    #define HIGP
    #endif

    uniform sampler2D u_diffuse;
    varying MEDP vec2 texCoords;

    void main() {
       float perspective_far = 250.0;
       float fog_cord = (gl_FragCoord.z / gl_FragCoord.w) / perspective_far;
       float fog_density = 2.5;
       float fog = fog_cord * fog_density;
       vec4 frag_color = texture2D(u_diffuse, texCoords);
       vec4 fog_color = vec4(0,0,0,0.6);
       gl_FragColor = mix(fog_color, frag_color, clamp(1.0-fog,0.0, 1.0));
       
    }
