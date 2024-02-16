package core.mail;

public class SecretaryMailBuilder extends MailBuilder {

    private final String secretaryFullName;

    public SecretaryMailBuilder(String secretaryFullName) {
        this.secretaryFullName = secretaryFullName;
    }

    @Override
    protected String greeting() {
        return String.format("""
                Здравствуйте, дорогая %s!
                """, secretaryFullName);
    }

    public SecretaryMailBuilder URLChangesMail(String disappearedURLs,
                                               String appearedURLs,
                                               String updatedURLs) {
        text.append(String.format("""
                                
                За последние сутки во вверенных Вам сайтах произошли следующие изменения:
                                 
                Исчезли следующие страницы: %s
                Появились следующие новые страницы: %s
                Изменились следующие страницы: %s
                                
                """, disappearedURLs, appearedURLs, updatedURLs)
        );
        return this;
    }

    @Override
    protected String signature() {
        return """
                С уважением,
                автоматизированная система
                мониторинга.
                """;
    }
}