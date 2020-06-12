package nl.cjm.webapp.model;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Foto implements Serializable {
    private Path padNaarFoto;
    private String altText;
    public Foto(String pad, String txt){
        this.padNaarFoto = Paths.get(pad);
        this.altText = txt;
    }

    public Path getPadNaarFoto() {
        return padNaarFoto;
    }
}
