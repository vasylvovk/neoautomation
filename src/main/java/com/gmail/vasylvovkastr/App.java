package com.gmail.vasylvovkastr;

import java.util.List;


/**
 * Hello world!
 */
public class App {
    static private String ref = "http://newton.dm.unipi.it/neodys/index.php?pc=3.2.1&pc0=3.2&lra=&ura=&lde=-30&ude=90&lvm=0&uvm=18.5&lel=&uel=&lph=&uph=&lgl=&ugl=&ldfe=&udfe=&ldfs=&udfs=&sb=9&lmo=1&umo=60000&lspu=&uspu=&ldu=&udu=&lal=&ual=";

    public static void main(String... args) {
        String savePath = args[0];
        NEODySTools neoDySTools = new NEODySTools();
        List<String> bodyList = neoDySTools.getAstList(ref);
        System.out.println(bodyList);
        String htmlText;
        for (String item : bodyList) {
            String target = "433";
            String[] tmp = item.split(" ");
            if (tmp.length > 1) {
                target = tmp[1];
            } else {
                target = item;
            }
            System.out.println(target);
            htmlText = neoDySTools.getAstBody(target);
            neoDySTools.saveBodyEphem(savePath, target, htmlText);
        }
    }
}
