package core.tracker;

import java.net.URI;
import java.util.Map;
import java.util.Set;

/*
Здесь был вариант оставить только один метод и создать по 3 класса реализации
на каждый тип URL, однако вне контекста сложно сказать, насколько это целесообразно
 */
public abstract class URLChangesTracker {
    protected final Map<URI, String> yesterdayHtmlPages;
    protected final Map<URI, String> todayHtmlPages;

    public URLChangesTracker(Map<URI, String> yesterdayHtmlPages, Map<URI, String> todayHtmlPages) {
        this.yesterdayHtmlPages = yesterdayHtmlPages;
        this.todayHtmlPages = todayHtmlPages;
    }

    public abstract Set<URI> disappearedURLs();

    public abstract Set<URI> appearedURLs();

    public abstract Set<URI> updatedURLs();
}
