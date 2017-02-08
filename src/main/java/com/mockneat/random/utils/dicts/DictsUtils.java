package com.mockneat.random.utils.dicts;

import com.mockneat.types.enums.DictType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by andreinicolinciobanu on 08/11/2016.
 */
public class DictsUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictsUtils.class);

    private static final DictsUtils INSTANCE = new DictsUtils();

    private final Map<String, List<String>> defaults = new HashMap<>();

    protected final com.mockneat.utils.DictsUtils utils = com.mockneat.utils.DictsUtils.getInstance();

    public static synchronized DictsUtils getInstance() {
        return INSTANCE;
    }

    public List<String> get(DictType dict) {
        String fileName = dict.getFileName();
        // Test if dictionary was not loaded
        if (!defaults.containsKey(fileName)) {
            // Load the dictionary first
            try {
                load(fileName);
                int dictSize = defaults.get(fileName).size();
                LOGGER.info("Loaded dictionary '{}' in memory. The dictionary contains {} entries.",
                        fileName, dictSize);
            }
            catch (URISyntaxException | IOException e) {
                // TODO report error
                LOGGER.error("Cannot read default dictionary: {} from the disk.", dict);
            }
        }
        return defaults.get(dict.getFileName());
    }

    protected void load(String defaultName) throws IOException, URISyntaxException {
        defaults.put(defaultName, utils.getLines(utils.getDefaultDictPath(defaultName)));
    }
}
