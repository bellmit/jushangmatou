package com.jsmt.developer.utils.language;

import java.util.Locale;

public class LanguageBean {
    private Locale language;
    private String name;

    public LanguageBean(Locale language, String name) {
        this.language = language;
        this.name = name;
    }
    public LanguageBean(Locale language) {
        this.language = language;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class LanguageRefresh{
        private String refresh;

        public String getRefresh() {
            return refresh;
        }

        public void setRefresh(String refresh) {
            this.refresh = refresh;
        }
    }
}
