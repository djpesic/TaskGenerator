/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author djordje
 */
public class FileNameLoader {

    public static List<String> loadNames(String dir, FilenameFilter filter) {
        File templateDir = new File(dir);
        List<String> result = new LinkedList<>();
        for (File f : templateDir.listFiles(filter)) {
            String name = f.getName();
            name = name.substring(0, name.indexOf(".xml"));
            result.add(name);
        }
        Collections.sort(result);
        return result;
    }
}
