package com.verysafe.falconshield.events.infrastructure.messages.listener;

import com.verysafe.falconshield.events.application.dto.request.EventRequestDto;
import com.verysafe.falconshield.events.domain.services.commands.IEventCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessagesListener {
    private final IEventCommands eventCommands;

    public KafkaMessagesListener(IEventCommands eventCommands) {
        this.eventCommands = eventCommands;
    }

    @KafkaListener(
            topics = {"device-events"},
            groupId = "device-event-group-id",
            containerFactory = "deviceEventKafkaListenerFactory"
    )
    public void listenDeviceEvents(EventRequestDto eventData) {
        log.info("Received message with data = {}", eventData);
        eventCommands.createEvent(eventData);
    }
}
