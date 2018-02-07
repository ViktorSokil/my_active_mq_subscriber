package com.sokil.massaging;

import com.sokil.dto.PersonDTO;
import com.sokil.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Slf4j
@Component
public class MessageSubscriber {

    @Autowired
    private IPersonService personService;

    private static final String RANDOM_DATA_TOPIC = "random_data_topic";

    @JmsListener(destination = RANDOM_DATA_TOPIC)
    public void savePersonToSQL(PersonDTO personDTO) throws JMSException {
        log.info("----------------------------------------------------");
        log.info("Application : product : {}", personDTO);
        personService.save(personDTO);
        log.info("Save to mySQL");
        log.info("----------------------------------------------------");

    }

    @JmsListener(destination = RANDOM_DATA_TOPIC)
    public void savePersonToMongo(PersonDTO personDTO) throws JMSException {
        log.info("----------------------------------------------------");
        log.info("Application : product : {}", personDTO);
        personService.create(personDTO);
        log.info("Save to mongoDB");
        log.info("----------------------------------------------------");

    }
}
