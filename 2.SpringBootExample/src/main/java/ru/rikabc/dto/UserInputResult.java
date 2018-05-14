package ru.rikabc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author Roman Khayrullin on 12.05.2018
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public class UserInputResult {
    private final String message;
    private final boolean valid;
    public boolean isValid(){
        return valid;
    }
}
