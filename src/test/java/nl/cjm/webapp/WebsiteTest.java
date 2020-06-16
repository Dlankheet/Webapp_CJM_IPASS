//package nl.cjm.webapp;
//
//import nl.cjm.model.*;
//
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.lang.reflect.Array;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class WebsiteTest {
//    @Test
//    public void getandsetFotosliderSuccesTest() {
//        Foto foto1 = new Foto("images/IMG_9919.JPG", "deze bestaat niet");
//        Fotoslider fotoslider = new Fotoslider(foto1);
//        fotoslider.addFoto(foto1);
//        try {
//            Website website = new Website(true);
//            Fotoslider Fakefotoslider = new Fotoslider(foto1);
//            Fakefotoslider.addFoto(foto1);
//            website.addFotoslider(Fakefotoslider);
//            Fotoslider Fakefotoslider1 = website.getFotosliders().get(0);
//            boolean fotoslide = Fakefotoslider1.isEqual(fotoslider);
//            assertTrue(fotoslide);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getandsetFotosliderFailTest() {
//        Foto foto1 = new Foto("images/IMG_9919.JPG", "deze bestaat niet");
//        Foto foto2 = new Foto("images/IMG_9918.JPG", "deze bestaat niet");
//        Fotoslider fotoslider = new Fotoslider(foto1);
//        fotoslider.addFoto(foto1);
//        try {
//            Website website = new Website(true);
//            Fotoslider Fakefotoslider = new Fotoslider(foto2);
//            Fakefotoslider.addFoto(foto2);
//            website.addFotoslider(Fakefotoslider);
//            Fotoslider Fakefotoslider1 = website.getFotosliders().get(0);
//            boolean fotoslide = Fakefotoslider1.isEqual(fotoslider);
//            assertFalse(fotoslide);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void fotoSuccesTest() {
//        Foto foto1 = new Foto("images/IMG_9919.JPG", "deze bestaat niet");
//        try {
//            Path fotopad = foto1.getPadNaarFoto();
//            Path echteFotopad = Paths.get("images/IMG_9919.JPG");
//            assertEquals(echteFotopad, fotopad);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void fotoinWebsiteTest() {
//        Foto foto1 = new Foto("images/IMG_9919.JPG", "deze bestaat niet");
//        Fotoslider fotoslider = new Fotoslider(foto1);
//        fotoslider.addFoto(foto1);
//        try {
//            Website website = new Website(true);
//            website.addFotoslider(fotoslider);
//            Fotoslider fotoslider1 = website.getFotosliders().get(0);
//            ArrayList<Foto> fotos = fotoslider1.getFotos();
//            Foto foto2 = fotos.get(0);
//            Path padNaarFoto = foto2.getPadNaarFoto();
//            assertEquals(foto1.getPadNaarFoto(), padNaarFoto);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getandsetBeheerderSuccesTest() {
//        Beheerder beheer1 = new Beheerder("jan01", "pannenkoek", "Jan@gmail.com");
//        try {
//            Website website = new Website(true);
//            website.addBeheerders("jan01", "pannenkoek", "Jan@gmail.com");
//            Beheerder beheerder = website.getBeheerders().get(0);
//            boolean beheerbool = beheer1.isEqual(beheerder);
//            assertTrue(beheerbool);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
