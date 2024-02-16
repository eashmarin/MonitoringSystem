package core;

import core.mail.SecretaryMailBuilder;
import core.tracker.URLChangesTracker;
import core.tracker.URLChangesTrackerImpl;
import utils.StringUtils;

import java.net.URI;
import java.util.Map;

public class Main {

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

    public static void main(String[] args) {
        URLChangesTracker changesTracker = new URLChangesTrackerImpl(yesterdayHtmls, todayHtmls);

        SecretaryMailBuilder mailBuilder = new SecretaryMailBuilder("Елена Павловна");
        String mail = mailBuilder.URLChangesMail(
                StringUtils.getURLsCommaSeparatedString(changesTracker.disappearedURLs()),
                StringUtils.getURLsCommaSeparatedString(changesTracker.appearedURLs()),
                StringUtils.getURLsCommaSeparatedString(changesTracker.updatedURLs())
        ).build();

        System.out.println(mail);
    }
}