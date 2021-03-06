package zula.util;

import zula.resources.resource;

import java.awt.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class Constants {
    public static final Color mainColor = new Color(280671);
    public static final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final Font mainFont = new Font("Verdana", Font.PLAIN, screenHeight/27); //при 1080 будет 40 шрифт
    public static final Font subFont = new Font("Verdana", Font.PLAIN, screenHeight/54); //в 2 раза поменьше
    public static final String[] languages = {"Русский", "Македонски", "Latviski", "letón"};
    public static final ResourceBundle ruBundle = ResourceBundle.getBundle("zula.resources.resource", new Locale("ru"));
    public static final ResourceBundle lvaBundle = ResourceBundle.getBundle("zula.resources.resource", new Locale("lva"));
    public static final ResourceBundle mkdBundle = ResourceBundle.getBundle("zula.resources.resource", new Locale("mkd"));
    public static final ResourceBundle espBundle = ResourceBundle.getBundle("zula.resources.resource", new Locale("esp"));
    public static final HashMap<String, ResourceBundle> languagesAndBundles = new HashMap<>();
    static {
        languagesAndBundles.put("Русский", ruBundle);
        languagesAndBundles.put("Latviski", lvaBundle);
        languagesAndBundles.put("Македонски", mkdBundle);
        languagesAndBundles.put("letón", espBundle);
    }


    public static ResourceBundle getBundleFromLanguageName(String s) {
        return languagesAndBundles.get(s);
    }
    public static String getNameByBundle(ResourceBundle resourceBundle) {
        for(String key : languagesAndBundles.keySet()) {
            if(languagesAndBundles.get(key) == resourceBundle) {
                return key;
            }
        }
        return null;
    }

/*    public static String translate(String toTranslate, Languages language) {
        if(language == Languages.RUSSIAN) {
            return resource_ru.getTranslation(toTranslate);
        }
        if(language == Languages.SPANISH) {

            return resource_esp.getTranslation(toTranslate);
        }
        if(language == Languages.MAKEDONSKI) {
            return resource_mkd.getTranslation(toTranslate);
        }
        if(language == Languages.LATVISKI) {
        return resource_lva.getTranslation(toTranslate);
        }
        return null;
    }*/

}
