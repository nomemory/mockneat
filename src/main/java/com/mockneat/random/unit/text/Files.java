package com.mockneat.random.unit.text;

import com.mockneat.random.Rand;
import com.mockneat.random.interfaces.RandUnitString;
import com.mockneat.random.utils.file.FileManager;

import static com.mockneat.random.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 20/02/2017.
 */
public class Files {
    private Rand rand;
    private FileManager fm = FileManager.getInstance();

    public Files(Rand rand) {
        this.rand = rand;
    }

    public RandUnitString from(String path) {
        notNull(path, INPUT_PARAMETER_NOT_NULL, "path");
        return () -> rand.fromStrings(fm.getLines(path)).format(LOWER_CASE)::val;
    }
}
