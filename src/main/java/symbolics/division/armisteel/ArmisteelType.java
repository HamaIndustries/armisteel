package symbolics.division.armisteel;

public enum ArmisteelType {
    // standard, corroded, frosted, ionized, rusted, scorched
    DEFAULT(""),
    CORRODED("corroded_"),
    FROSTED("frosted_"),
    IONIZED("ionized_"),
    RUSTED("rusted_"),
    SCORCHED("scorched_");

    private final String id;

    ArmisteelType(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
