package com.marcosvbras.robomarket.utils;

public class Constants {

    public class Api {
        public static final String BASE_API_URL = "https://parseapi.back4app.com/";
        public static final String LOGIN_ENDPOINT = "login";
        public static final String LOGOUT_ENDPOINT = "logout";
        public static final String USER_ROOT_ENDPOINT = "users";
        public static final String USER_ACTIONS_ENDPOINT = "users/{objectId}";
        public static final String AUTHENTICATED_USER_ENDPOINT = "users/me";
        public static final String PASSWORD_RESET_ENDPOINT = "requestPasswordReset";
        public static final String ROBOT_ROOT_ENDPOINT = "classes/Robot";
        public static final String SALE_ROOT_ENDPOINT = "classes/Sale";
        public static final String ROBOT_ACTIONS_ENDPOINT = "classes/Robot/{objectId}";
        public static final String SALE_ACTIONS_ENDPOINT = "classes/Robot/{objectId}";
        public static final String HEADER_APP_ID = "X-Parse-Application-Id";
        public static final String HEADER_REST_API_KEY = "X-Parse-REST-API-Key";
        public static final String HEADER_SESSION_TOKEN = "X-Parse-Session-Token";
        public static final String APP_ID = "D8MjOxsJpDNPaLdBaxvRU6Afm1xMGF9TTY5TQArd";
        public static final String REST_API_KEY = "kv9fEiX8x7RlrxNOcvqKaLXYZg7qBlDh1lCzHVbY";
        public static final int DEFAULT_ROBOT_PAGINATION = 10;
        public static final int DEFAULT_ROBOT_SKIP = 10;
        public static final String DEFAULT_ROBOT_ORDER = "-updatedAt";
    }

    public class Preferences {
        public static final String PREF_KEY = "userCredentials";
        public static final String USER_ID_KEY = "userId";
        public static final String SESSION_TOKEN_KEY = "userSessionToken";
    }

    public class Other {
        public static final String TAG = "robomarket";
        public static final String ROBOTS_FRAGMENT_TAG = "robots";
        public static final String PROFILE_FRAGMENT_TAG = "profile";
        public static final String SALES_FRAGMENT_TAG = "sales";
        public static final String ROBOHASH_API = "https://robohash.org/";
        public static final String ROBOHASH_SET_1_PARAM = "?set=set1";
        public static final String ROBOHASH_SET_2_PARAM = "?set=set2";
        public static final String ACTION_TAG = "action";
        public static final String ROBOT_TAG = "robot";
        public static final int ACTION_CREATE_ROBOT = 8963;
        public static final int ACTION_EDIT_ROBOT = 6658;
        public static final int FINISH_LOGIN_ACTIVITY = 9000;
        public static final int NEW_ROBOT_REQUEST_CODE = 6000;
        public static final int EDIT_ROBOT_REQUEST_CODE = 7987;
        public static final int SELECT_ROBOT_REQUEST_CODE = 9584;
    }

    public class Code {
        public static final int OK = 200;
    }
}
