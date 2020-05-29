package nl.cjm.webapp.model;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Foto {
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
