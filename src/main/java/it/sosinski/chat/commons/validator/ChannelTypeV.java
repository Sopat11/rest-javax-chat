package it.sosinski.chat.commons.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ChannelTypeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChannelTypeV {

    String message() default "Invalid channel type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
