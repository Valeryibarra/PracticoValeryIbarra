package eileen.com.practicovalery;

import android.app.Application;

public class Variable extends Application {
    private int puntaje=300;

    public int getPuntaje(){
        return this.puntaje;
    }

    public void setPuntaje(int puntaje){
        this.puntaje=puntaje;
    }
}
