package com.haykz.GymCRM.storage;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class InMemoryStorage {
    private final Map<String, Object> storage = new HashMap<>();
}
