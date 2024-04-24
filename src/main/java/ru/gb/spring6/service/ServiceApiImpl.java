package ru.gb.spring6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.spring6.domain.Characters;
import ru.gb.spring6.properties.Properties;

import java.util.List;

@Service
public class ServiceApiImpl implements ServiceApi{
    @Autowired
    private Properties properties;
    @Autowired
    private RestTemplate template;

    @Autowired
    private HttpHeaders headers;
    @Override
    public Characters getAllCharacters() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Characters> responce = template.exchange(properties.getUrl(), HttpMethod.GET,entity, Characters.class);
        return responce.getBody();
    }
}
