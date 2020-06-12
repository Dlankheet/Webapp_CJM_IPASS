package nl.cjm.webapp.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;

public class Fotoslider implements Serializable {
    private ArrayList<Foto> fotos = new ArrayList<>();
    public Fotoslider(Foto foto){
        fotos.add(foto);
    }
    public void addFoto(Foto f){
        fotos.add(f);
    }
    public ArrayList<Foto> getFotos(){
        return fotos;
    }

    public boolean isEqual(Object o) {
        if (o instanceof Fotoslider) {
            Fotoslider andereFotoslide = (Fotoslider) o;
            return (andereFotoslide.fotos).equals(this.fotos);
        } return false;
    }
}
