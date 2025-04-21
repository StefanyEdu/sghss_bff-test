package enums;

import org.apache.xpath.operations.String;

public enum Servico {
   SGHSS-BFF( "sghss-bff"),

    TOKEN("token");

    private final String path;

    Servico(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}