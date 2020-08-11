package com.jair.petagram.restApi;

public final class ConstantesRestApi {
    public static final String ROOT_URL = "https://graph.instagram.com/";
    public static final String KEY_ACCESS_TOKEN = "&access_token=";
    public static final String ACCESS_TOKEN = "";
    public static final String KEY_GET_MEDIA_USER = "me/media?fields=media_url,username";
    public static final String URL_GET_MEDIA_USER = KEY_GET_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
}
