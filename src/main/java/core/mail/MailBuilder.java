package core.mail;

public abstract class MailBuilder {
    protected final StringBuilder text = new StringBuilder();

    public String build() {
        return greeting() + text + signature();
    }

    protected abstract String greeting();

    protected abstract String signature();
}
