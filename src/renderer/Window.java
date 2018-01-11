package renderer;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import input.KeyInput;
import input.MouseInput;

public class Window {

    private static GLWindow window = null;

    public static final int X = 800;
    public static final int Y = 600;

    public static void init() {
        GLProfile.initSingleton();
        GLProfile profile = GLProfile.get(GLProfile.GL2);                       //choosing OpenGL 2 profile
        GLCapabilities caps = new GLCapabilities(profile);                      //getting GL2 profile capabilities

        //noinspection SpellCheckingInspection
        window = GLWindow.create(caps);                                         //creating an true'Ъ opengl window
        window.setSize(X, Y);
        window.setTitle("Freaking window");
        window.setResizable(false);                                             //that cannot be resized
        window.addGLEventListener(new EventListener());                         //attaching event listener
        window.addMouseListener(new MouseInput());                              //attaching input listener
        window.addKeyListener(new KeyInput());



//        animator = new FPSAnimator(window, 60 /* fps */);                       //setting up drawing loop
//        animator.start();                                                       //starting drawing loop

        window.setVisible(true);                                                //showing up window

    }

    public static void render(){
        if(window == null) return;

        window.display();
    }

}
