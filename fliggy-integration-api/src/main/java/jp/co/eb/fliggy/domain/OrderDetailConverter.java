package jp.co.eb.fliggy.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.eb.fliggy.util.ObjectMapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.seasar.doma.ExternalDomain;
import org.seasar.doma.jdbc.domain.DomainConverter;

import java.io.IOException;

@Slf4j
@ExternalDomain
public class OrderDetailConverter implements DomainConverter<OrderDetail, String> {
    private ObjectMapper objectMapper = ObjectMapperFactory.getInstance();

    @Override
    public String fromDomainToValue(OrderDetail domain) {
        try {
            return objectMapper.writeValueAsString(domain);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public OrderDetail fromValueToDomain(String value) {
        try {
            return objectMapper.readValue(value, OrderDetail.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
