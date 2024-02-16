package core.tracker;

import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class URLChangesTrackerImpl extends URLChangesTracker {
    public URLChangesTrackerImpl(Map<URI, String> yesterdayHtmlPages, Map<URI, String> todayHtmlPages) {
        super(yesterdayHtmlPages, todayHtmlPages);
    }

    @Override
    public Set<URI> disappearedURLs() {
        return yesterdayHtmlPages.keySet()
                .stream()
                .filter(uri -> !todayHtmlPages.containsKey(uri))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Set<URI> appearedURLs() {
        return todayHtmlPages.keySet()
                .stream()
                .filter(uri -> !yesterdayHtmlPages.containsKey(uri))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Set<URI> updatedURLs() {
        return todayHtmlPages.entrySet()
                .stream()
                .filter(entry -> {
                            String yesterdayHtml = yesterdayHtmlPages.get(entry.getKey());
                            String todayHtml = entry.getValue();
                            return yesterdayHtml != null && todayHtml.compareTo(yesterdayHtml) != 0;
                        }
                )
                .map(Map.Entry::getKey)
                .collect(Collectors.toUnmodifiableSet());
    }
}
