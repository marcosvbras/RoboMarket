package com.marcosvbras.robomarket.utils;

public class Constants {

    public class Api {
        public static final String BASE_API_URL = "https://parseapi.back4app.com/";
        public static final String LOGIN_ENDPOINT = "login";
        public static final String LOGOUT_ENDPOINT = "logout";
        public static final String USER_CREATION_ENDPOINT = "users";
        public static final String USER_ACTIONS_ENDPOINT = "users/{objectId}";
        public static final String AUTHENTICATED_USER_ENDPOINT = "users/me";
        public static final String PASSWORD_RESET_ENDPOINT = "requestPasswordReset";
        public static final String HEADER_APP_ID = "X-Parse-Application-Id";
        public static final String HEADER_REST_API_KEY = "X-Parse-REST-API-Key";
        public static final String HEADER_SESSION_TOKEN = "X-Parse-Session-Token";
        public static final String APP_ID = "D8MjOxsJpDNPaLdBaxvRU6Afm1xMGF9TTY5TQArd";
        public static final String REST_API_KEY = "kv9fEiX8x7RlrxNOcvqKaLXYZg7qBlDh1lCzHVbY";
    }

    public class Preferences {
        public static final String PREF_KEY = "userCredentials";
        public static final String USER_ID_KEY = "userId";
        public static final String SESSION_TOKEN_KEY = "userSessionToken";
    }

    public class Other {
        public static final String TAG = "robomarket";
    }

    public class Code {
        public static final int OK = 200;
    }
}
