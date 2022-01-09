package com.lijw.decision.core.id;

import java.util.UUID;

public class JdkIdGenerator implements IdGenerator<UUID> {

    @Override
    public UUID generateId() {
        return UUID.randomUUID();
    }

}
