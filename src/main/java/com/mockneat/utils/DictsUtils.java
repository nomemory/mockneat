package com.mockneat.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by andreinicolinciobanu on 17/11/2016.
 */
public class DictsUtils {

    // TODO needs refactoring
    // Started as a SingleTon ended as an UTIL class

    private static final DictsUtils instance = new DictsUtils();
    private static final String DEFAULTS_PATH = "./resources/dicts/";

    private Boolean isRunningFromJar;

    private DictsUtils() {
        this.isRunningFromJar = isRunningFromJar();
    }

    public static DictsUtils getInstance() {
        return instance;
    }

    public String getDefaultDictPath(String fileName) {
        return DEFAULTS_PATH + fileName;
    }

    public List<String> getLines(String file) throws URISyntaxException, IOException {
        String strPath = file;
        Path path = isRunningFromJar ?
               Paths.get(ClassLoader.getSystemClassLoader().getSystemResource(strPath).toURI()) : Paths.get(strPath);
        try (Stream<String> stream = Files.lines(path)) {
            return stream.collect(Collectors.toList());
        }
    }

    public String getDefaultsPathByName(String name) {
        return DEFAULTS_PATH + name;
    }

    private boolean isRunningFromJar() {
        String className = this.getClass().getName().replace('.', File.separatorChar) + ".class";
        URL path = DictsUtils.class.getResource(this.getClass().getSimpleName() + ".class");
        return path.getProtocol().equals("jar");
    }
}
