package tracker;

import core.tracker.URLChangesTracker;
import core.tracker.URLChangesTrackerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TrackerTests {
    private final static Map<URI, String> yesterdayHtmls = Map.ofEntries(
            Map.entry(URI.create("https://www.example.com"), "<h1>Some text</h1>"),
            Map.entry(URI.create("https://www.ya.ru"), "<h1>Some text</h1>"),
            Map.entry(URI.create("https://www.google.com"), "<h1>Some text</h1>"),
            Map.entry(URI.create("https://www.wikipedia.org"), "<h1>Some text</h1>")
    );
    private final static Map<URI, String> todayHtmls = Map.ofEntries(
            Map.entry(URI.create("https://www.example.com"), "<h1>Another text</h1>"),
            Map.entry(URI.create("https://www.ya.ru"), "<h1>Some text</h1>"),
            Map.entry(URI.create("https://www.hh.ru"), "<h1>Some text</h1>"),
            Map.entry(URI.create("https://web.telegram.org"), "<h1>Some text</h1>"),
            Map.entry(URI.create("https://web.whatsapp.com"), "<h1>Some text</h1>")
    );

    @Test
    public void appearedURLsAreTracked() {
        URLChangesTracker tracker = new URLChangesTrackerImpl(yesterdayHtmls, todayHtmls);

        Set<URI> appearedURLs = tracker.appearedURLs();

        Assertions.assertEquals(
                appearedURLs.stream().map(URI::toString).collect(Collectors.toUnmodifiableSet()),
                Set.of("https://www.hh.ru", "https://web.telegram.org", "https://web.whatsapp.com")
        );
    }

    @Test
    public void disappearedURLsAreTracked() {
        URLChangesTracker tracker = new URLChangesTrackerImpl(yesterdayHtmls, todayHtmls);

        Set<URI> appearedURLs = tracker.disappearedURLs();

        Assertions.assertEquals(
                appearedURLs.stream().map(URI::toString).collect(Collectors.toUnmodifiableSet()),
                Set.of("https://www.google.com", "https://www.wikipedia.org")
        );
    }

    @Test
    public void updatedURLsAreTracked() {
        URLChangesTracker tracker = new URLChangesTrackerImpl(yesterdayHtmls, todayHtmls);

        Set<URI> appearedURLs = tracker.updatedURLs();

        Assertions.assertEquals(
                appearedURLs.stream().map(URI::toString).collect(Collectors.toUnmodifiableSet()),
                Set.of("https://www.example.com")
        );
    }
}
