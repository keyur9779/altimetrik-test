package com.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {
    public static final String BUNDLE_KEY_ARTICLE_URL = "articleUrl";
    public static final String BUNDLE_KEY_ARTICLE_PUBLISHED_DATE = "publishDate";

    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private Constants() {
        // Private constructor to hide the implicit one
    }

}
