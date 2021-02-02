package net.andreinc.mockneat.utils.file;

import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.MarkovChainType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.Paths.get;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class FileManager {

    private static final String DICT_FOLDER = "dicts/";

    private static final String MARKOV_FOLDER = "markov/";

    private static final FileManager fileManager = new FileManager();

    private static final ClassLoader loader = Thread.currentThread().getContextClassLoader();

    private static final Map<String, List<String>> JAR_INTERNAL =
            new HashMap<>();

    private static final Map<String, List<String>> JAR_EXTERNAL =
            new HashMap<>();

    public static FileManager getInstance() {
        return fileManager;
    }

    /**
     * This method returns the cached lines for the file.
     *
     * @param path The path to the file.
     *
     * @return A string containing all the lines from path (cached).
     */
    public List<String> getLines(String path) {
        if (!JAR_EXTERNAL.containsKey(path)) {
            try {
                List<String> lines = read(path);
                JAR_EXTERNAL.put(path, lines);
            } catch (IOException e) {
               throw new IllegalArgumentException(e);
            }
        }
        return JAR_EXTERNAL.get(path);
    }

    public List<String> getLines(DictType dictType) {
        String internal = getDictPath(dictType);
        if (!JAR_INTERNAL.containsKey(internal)) {
            try {
                List<String> lines = read(dictType);
                JAR_INTERNAL.put(internal, lines);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return JAR_INTERNAL.get(internal);
    }

    public Set<String> getUniqueLines(DictType dictType) {
        return new HashSet<>(getLines(dictType));
    }

    private String getDictPath(DictType dictType) {
        return DICT_FOLDER + dictType.getFile();
    }

    private String getMarkovPath(MarkovChainType markovChainType) {
        return MARKOV_FOLDER + markovChainType.getFile();
    }

    private List<String> readInternal(String internal) throws IOException {
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(requireNonNull(loader.getResourceAsStream(internal)), defaultCharset()))) {
            return buff.lines().collect(toList());
        }
    }

    public List<String> read(MarkovChainType markovChainType) throws IOException {
        return readInternal(getMarkovPath(markovChainType));
    }

    public List<String> read(DictType dictType) throws IOException {
        return readInternal(getDictPath(dictType));
    }

    public List<String> read(String key) throws IOException {
        Path p = get(key);
        try (Stream<String> stream = Files.lines(p)) {
            return stream.collect(toList());
        }
    }
}
