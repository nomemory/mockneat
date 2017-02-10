package com.mockneat.random.utils.dicts;

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
