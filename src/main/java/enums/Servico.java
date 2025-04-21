package enums;

public enum Servico {
    PUFV_BACKOFFICE( "pufv-backoffice"),
    PUFV_USERS("pufv-users"),
    PUFV_CONTRATO("pufv-contrato"),
    TOKEN("token");

    private final String path;

    Servico(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}