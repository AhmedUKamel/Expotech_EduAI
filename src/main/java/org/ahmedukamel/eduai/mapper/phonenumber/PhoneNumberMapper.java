package org.ahmedukamel.eduai.mapper.phonenumber;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PhoneNumberMapper implements Function<String, PhoneNumber> {
    @Override
    public PhoneNumber apply(String number) {
        try {
            Phonenumber.PhoneNumber phoneNumber = PhoneNumberUtil.getInstance().parse(number, "EG");
            return new PhoneNumber(phoneNumber.getCountryCode(), phoneNumber.getNationalNumber());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}