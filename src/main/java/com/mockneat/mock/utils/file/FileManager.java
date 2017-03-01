package com.mockneat.mock.utils.file;

import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.MarkovChainType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
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

    private static final Logger logger = LoggerFactory.getLogger(FileManager.class);

    private static final String DICT_FOLDER = "dicts/";

    private static final String MARKOV_FOLDER = "markov/";

    private static final FileManager fileManager = new FileManager();

    private static final ClassLoader loader = FileManager.getInstance().getClass().getClassLoader();

    private static final Map<String, List<String>> JAR_INTERNAL =
            new HashMap<>();

    private static final Map<String, List<String>> JAR_EXTERNAL =
            new HashMap<>();

    public static FileManager getInstance() {
        return fileManager;
    }

    public List<String> getLines(String path) {
        if (!JAR_EXTERNAL.containsKey(path)) {
            try {
                List<String> lines = read(path);
                logger.info("Loaded file '{}' in memory. The file contains {} lines.", path, lines.size());
                JAR_EXTERNAL.put(path, lines);
            } catch (IOException e) {
                logger.error("Cannot read file '{}' in memory.", e);
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
                logger.info("Loading internal dictionary '{}' in memory. The dictionary contains {} lines",
                        dictType.getFile(),
                        lines.size());
                JAR_INTERNAL.put(internal, lines);
            } catch (IOException e) {
                logger.error("Cannot read internal dictionary '{}' in memory. Something is terribly wrong.");
                throw new UncheckedIOException(e);
            }
        }
        return JAR_INTERNAL.get(internal);
    }

    private String getDictPath(DictType dictType) {
        return DICT_FOLDER + dictType.getFile();
    }

    private String getMarkovPath(MarkovChainType markovChainType) {
        return MARKOV_FOLDER + markovChainType.getFile();
    }

    protected List<String> readInternal(String internal) throws IOException {
        try (BufferedReader buff =
                     new BufferedReader(
                             new InputStreamReader(
                                     loader.getResourceAsStream(
                                             internal)))) {
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
