package org.ahmedukamel.eduai.mapper.invoice;

import org.ahmedukamel.eduai.dto.invoice.BilledToInfo;
import org.ahmedukamel.eduai.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BilledToInfoMapper implements Function<User, BilledToInfo> {

    @Override
    public BilledToInfo apply(User user) {
        return new BilledToInfo(
                user.getId(),
                user.getEmail(),
                user.getPicture()
        );
    }
}
