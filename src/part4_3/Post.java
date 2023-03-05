package part4_3;

import part4_3.dto.Package;
import part4_3.sendable.MailMessage;
import part4_3.sendable.MailPackage;
import part4_3.service.MailService;
import part4_3.sendable.Sendable;
import part4_3.service.RealMailService;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Post {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main(String[] args) {
    }

    public static class UntrustworthyMailWorker implements MailService {
        private final MailService[] mailServices;
        private final MailService realMailService;

        public UntrustworthyMailWorker(MailService[] mailServices) {
            this.mailServices = mailServices;
            realMailService = new RealMailService();
        }

        public MailService getRealMailService() {
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            Sendable processedMail = mail;
            for (MailService service : mailServices) {
                processedMail = service.processMail(processedMail);
            }
            return realMailService.processMail(processedMail);
        }
    }

    public static class Spy implements MailService {
        private final Logger logger;

        public Spy(Logger logger) {
            this.logger = logger;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                MailMessage msg = (MailMessage) mail;

                if (Objects.equals(AUSTIN_POWERS, msg.getFrom()) ||
                        Objects.equals(AUSTIN_POWERS, msg.getTo())) {
                    logger.log(Level.WARNING,
                            "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                            new Object[]{msg.getFrom(), msg.getTo(), msg.getMessage()});
                } else {
                    logger.log(Level.INFO,
                            "Usual correspondence: from {0} to {1}",
                            new Object[]{msg.getFrom(), msg.getTo()});
                }
            }
            return mail;
        }
    }

    public static class Thief implements  MailService {
        private final int minValue;
        private int stolenValue;

        public Thief(int minValue) {
            this.minValue = minValue;
        }

        public int getStolenValue() {
            return stolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;

                if (mailPackage.getContent().getPrice() >= minValue) {
                    Package pkg = mailPackage.getContent();
                    stolenValue += pkg.getPrice();
                    String stolenContent = "stones instead of " + pkg.getContent();
                    Package newPackage = new Package(stolenContent, 0);
                    return new MailPackage(mailPackage.getFrom(), mail.getTo(), newPackage);
                }
            }
            return mail;
        }
    }

    public static class Inspector implements MailService {
        private static final String STONES = "stones";

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                Package pkg = mailPackage.getContent();
                String content = pkg.getContent();

                if (content.contains(WEAPONS) || content.contains(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException();
                }
                if (content.contains(STONES)) {
                    throw new StolenPackageException();
                }
            }
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {
    }
    public static class StolenPackageException extends RuntimeException {
    }
}
