package io.github.rubensrabelo.project.mscommand.services;

public interface BrokerService {

    void send(String type, Object data);
}
