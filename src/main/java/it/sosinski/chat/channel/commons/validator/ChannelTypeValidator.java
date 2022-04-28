package it.sosinski.chat.channel.commons.validator;

import it.sosinski.chat.channel.domain.channel.ChannelType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChannelTypeValidator implements ConstraintValidator<ChannelTypeV, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        for (ChannelType c : ChannelType.values()) {
            if (c.name().equals(value.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
