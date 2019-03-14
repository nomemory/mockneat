package net.andreinc.mockneat.utils.file;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.MarkovChainType;
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

import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;

public class FileManager {

    private static final Logger logger = LoggerFactory.getLogger(FileManager.class);

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
     * @return
     */
    public List<String> getLines(String path) {
        if (!JAR_EXTERNAL.containsKey(path)) {
            try {
                List<String> lines = read(path);
                logger.info("Loaded file '{}' in memory. The file contains {} lines.", path, lines.size());
                JAR_EXTERNAL.put(path, lines);
            } catch (IOException e) {
                logger.error("Cannot read file '{}' in memory.", path);
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
                logger.info("Loading internal dictionary '{}' in memory. The dictionary contains {} lines.",
                        dictType.getFile(),
                        lines.size());
                JAR_INTERNAL.put(internal, lines);
            } catch (IOException e) {
                logger.error("Cannot read internal dictionary '{}' in memory. Something is terribly wrong.",
                        dictType.getFile());
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

    private List<String> readInternal(String internal) throws IOException {
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(loader.getResourceAsStream(internal), defaultCharset()))) {
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
