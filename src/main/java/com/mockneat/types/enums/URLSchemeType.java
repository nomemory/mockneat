package com.mockneat.types.enums;

/**
 * Created by andreinicolinciobanu on 16/02/2017.
 */
public enum URLSchemeType {
    HTTP("http"),
    HTTPS("https"),
    FTP("ftp"),
    NONE("");

    private String str;

    URLSchemeType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return str;
    }
}
