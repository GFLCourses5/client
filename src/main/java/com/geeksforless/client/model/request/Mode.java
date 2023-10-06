package com.geeksforless.client.model.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Mode {
    DIRECT("direct"), BACKBONE("backbone");

    private final String name;

}
