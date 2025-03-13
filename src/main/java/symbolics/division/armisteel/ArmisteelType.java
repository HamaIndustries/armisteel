package symbolics.division.armisteel;

public enum ArmisteelType {
    // standard, corroded, frosted, ionized, rusted, scorched
    DEFAULT(""),
    CORRODED("corroded"),
    FROSTED("frosted"),
    IONIZED("ionized"),
    RUSTED("rusted"),
    SCORCHED("scorched");

    private final String id;
    private final String prefix;

    ArmisteelType(String id) {
        this.id = id;
        this.prefix = id.isEmpty() ? "" : id + "_";
    }

    public String id() {
        return id;
    }

    public String prefix() {
        return prefix;
    }
}
