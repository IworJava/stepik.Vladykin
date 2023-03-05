package part4_3.service;

import part4_3.sendable.Sendable;

public interface MailService {
    Sendable processMail(Sendable mail);
}
