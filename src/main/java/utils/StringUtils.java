package utils;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

public class StringUtils {
    public static String getURLsCommaSeparatedString(Set<URI> URLs) {
        return String.join(
                ", ",
                URLs.stream()
                        .map(URI::toString)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }
}
