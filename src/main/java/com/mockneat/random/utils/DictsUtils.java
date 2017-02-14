package com.mockneat.random.utils;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
