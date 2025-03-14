package symbolics.division.armisteel;

import com.google.common.base.Strings;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;

public enum ArmisteelType {
    // standard, corroded, frosted, ionized, rusted, scorched
    DEFAULT(""),
    CORRODED("corroded"),
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

    public String getName() {
        return id.isEmpty() ? "" : StringUtils.capitalize(id()) + " ";
    }
}
