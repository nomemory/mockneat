package com.mockneat.mock.utils.file;

import com.mockneat.types.enums.DictType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;

/**
 * Created by andreinicolinciobanu on 20/02/2017.
 */
public class FileManager {

    private static final String DICT_FOLDER = "./resources/dicts/";

    private final static FileManager fileManager = new FileManager();

    private static final Map<String, List<String>> JAR_INTERNAL =
            new HashMap<>();

    private static final Map<String, List<String>> JAR_EXTERNAL =
            new HashMap<>();

    public static final FileManager getInstance() {
        return fileManager;
    }

    public List<String> getLines(String path) {
        if (!JAR_EXTERNAL.containsKey(path)) {
            try {
                JAR_EXTERNAL.put(path, read(path));
            } catch (IOException e) {
               throw new IllegalArgumentException(e);
            }
        }
        return JAR_EXTERNAL.get(path);
    }

    public List<String> getLines(DictType dictType) {
        String internal = interalDictPath(dictType);
        if (!JAR_INTERNAL.containsKey(internal)) {
            try {
                JAR_INTERNAL.put(internal, read(dictType));
            } catch (URISyntaxException | IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return JAR_INTERNAL.get(internal);
    }

    private List<String> read(String key) throws IOException {
        Path p = get(key);
        try (Stream<String> stream = Files.lines(p)) {
            return stream.collect(toList());
        }
    }

    private String interalDictPath(DictType dictType) {
        return DICT_FOLDER + dictType.getFileName();
    }

    private List<String> read(DictType key) throws URISyntaxException, IOException {
        String internal = interalDictPath(key);
        Path path = isRunningFromJar() ?
                get(ClassLoader.getSystemClassLoader().getSystemResource(internal).toURI()) :
                get(internal);
        try (Stream<String> stream = Files.lines(path)) {
            return stream.collect(toList());
        }
    }

    private boolean isRunningFromJar() {
        URL path = FileManager.class.getResource("FileManager.class");
        return path.getProtocol().equals("jar");
    }
}
