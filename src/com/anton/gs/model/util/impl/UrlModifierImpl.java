package com.anton.gs.model.util.impl;

import com.anton.gs.model.util.UrlModifier;

public final class UrlModifierImpl implements UrlModifier {
    private static final String PART_REGEXP = "http://localhost:8080/Guitar_School_0_0_2_war_exploded";
    private static final String EMPTY_REGEXP = "";

    private static UrlModifier urlModifier;

    private UrlModifierImpl() {
    }

    public static UrlModifier getInstance() {
        if (urlModifier == null) {
            urlModifier = new UrlModifierImpl();
        }
        return urlModifier;
    }

    @Override
    public String modifyUrl(String url) {
        url = url.replaceAll(PART_REGEXP, EMPTY_REGEXP);
        return url;
    }
}
