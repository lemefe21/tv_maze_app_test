package com.leme.tvmazeapptest.utils;

public final class AppValues {

    private AppValues() throws IllegalAccessException {
        throw new IllegalAccessException(VALUE_CLASS);
    }

    public static final String DEFAULT_URL_QUERY = "cartoon";
    public static final String NO_IMAGE_URL = "no_image";
    public static final String BASE_URL = "http://api.tvmaze.com/";
    public static final String DATABASE_NAME = "show";
    public static final String EXTRA_SHOW_KEY = "extra_show_key";
    public static final String PARAMS_TO_FRAGMENT = "show_detail_fragment";
    public static final String EXTRA_RESULT_DETAIL_KEY = "passed_item";
    public static final String UTIL_CLASS = "Util class";
    public static final String VALUE_CLASS = "Value class";
    public static final int REQUEST_CODE = 10;
    public static final int DELAY_SPLASH_SCREEN = 5000;
}
