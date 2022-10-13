package jp.co.eb.fliggy.service;

import jp.co.eb.fliggy.converter.AbstractParameter;
import jp.co.eb.fliggy.exception.ParamNotValidException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ValidatingService {
    public void validate(AbstractParameter input) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<AbstractParameter>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
            throw new ParamNotValidException(message);
        }
    }

}
