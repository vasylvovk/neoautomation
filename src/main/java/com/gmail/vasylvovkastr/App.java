package com.gmail.vasylvovkastr;

import java.util.List;


/**
 * Hello world!
 */
public class App {
    static private String ref = "http://newton.dm.unipi.it/neodys/index.php?pc=3.2.1&pc0=3.2&lra=&ura=&sb=2&lde=-30&ude=&lvm=&uvm=19&lel=&uel=&lph=&uph=&lgl=&ugl=&ldfe=0&udfe=0.2&ldfs=&udfs=&lmo=4&umo=&lspu=&uspu=&ldu=&udu=&lal=&ual=";
    public static void main(String... args) {
        String savePath = args[0];
        NEODySTools neoDySTools = new NEODySTools();
        List<String> bodyList = neoDySTools.getAstList(ref);
        String htmlText;
        for (String item: bodyList) {
            htmlText = neoDySTools.getAstBody(item);
            neoDySTools.saveBodyEphem(savePath, item, htmlText);
        }
    }
}
