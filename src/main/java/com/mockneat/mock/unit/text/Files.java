package com.mockneat.mock.unit.text;

import com.mockneat.mock.utils.ValidationUtils;
import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.mock.utils.file.FileManager;

import static com.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 20/02/2017.
 */
public class Files {
    private MockNeat rand;
    private FileManager fm = FileManager.getInstance();

    public Files(MockNeat mock) {
        this.rand = mock;
    }

    public MockUnitString from(String path) {
        notNull(path, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "path");
        return () -> rand.fromStrings(fm.getLines(path)).format(LOWER_CASE)::val;
    }
}
