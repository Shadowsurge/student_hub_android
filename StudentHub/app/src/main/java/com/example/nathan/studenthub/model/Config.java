package com.example.nathan.studenthub.model;

/**
 * Created by Nathan on 20/05/2017.
 *
 * Config file, used only to store strings and ints that are used throughout the code. This is better for memory performance on the Android
 * platform, compared to using Enum classes, for example.
 */

public abstract class Config
{
    // ********************** Static ints for differentiating between objects to download. Static ints are more efficient than using an Enum in Android ************************************************
    public static final int USER_DATA = 0;
    public static final int ADVERT_DATA = 1;
    public static final int BLOG_DATA = 2;

    // ********************** Static ints for differentiating between the downloads current status ************************************************
    static final int DOWNLOAD_WAITING = 0;
    static final int DOWNLOAD_NOT_INITIALISED = 1;
    static final int DOWNLOAD_PROCESSING = 2;
    public static final int DOWNLOAD_SUCCESS = 3;
    static final int DOWNLOAD_FAILED_OR_EMPTY = 4;

    // ********************** ADVERT RELATED TAGS ************************************************
    public static final String ADVERT_TAG = "ADVERT";
    public static final String LIVE_ADVERT_RESULTS = "LIVE_ADVERT_RESULTS";
    public static final String TITLE = "title";

    public static final String SOCIAL_SCIENCE_AVDERTS = "https://dundee-university-student-hub.herokuapp.com/android/social_sciences";
    public static final String SCIENCE_ADVERTS = "https://dundee-university-student-hub.herokuapp.com/android/science";
    public static final String NURSING_ADVERTS = "https://dundee-university-student-hub.herokuapp.com/android/nursing";
    public static final String MEDICINE_ADVERTS = "https://dundee-university-student-hub.herokuapp.com/android/medicine";
    public static final String LIFE_SCIENCE_ADVERTS = "https://dundee-university-student-hub.herokuapp.com/android/life_sciences";
    public static final String HUMANITIES_ADVERTS = "https://dundee-university-student-hub.herokuapp.com/android/humanities";
    public static final String EDUCATION_ADVERTS = "https://dundee-university-student-hub.herokuapp.com/android/education";
    public static final String DENTISTRY_ADVERTS = "https://dundee-university-student-hub.herokuapp.com/android/dentistry";
    public static final String ART_ADVERTS = "https://dundee-university-student-hub.herokuapp.com/android/art";

    // ********************** User related strings ************************************************
    public static final String USERNAME_TAG = "Username";
    public static final String USER_ID_TAG = "User ID";
    public static final String USER_EMAIL_TAG ="User Email";
    public static final String USER_ADVERTS_TAG = "User Adverts";
    public static final String USER_ADMIN_LEVEL_TAG = "User Admin Level";
    public static final String SHARED_PREFS_TAG = "Shared_Prefs";

    // ********************** URL's for the API ************************************************
    // ********************** GET URL's ************************************************
    public static final String GET_USER_URL = "https://dundee-university-student-hub.herokuapp.com/android/user/";
    public static final String GET_BLOG_URL = "https://dundee-university-student-hub.herokuapp.com/android/blog";

    // ********************** POST URL's ************************************************
    public static final String CREATE_ADVERT_URL = "https://dundee-university-student-hub.herokuapp.com/android/advert";
    public static final String LOGIN_URL = "https://dundee-university-student-hub.herokuapp.com/android/login";
    public static final String REGISTER_URL = "https://dundee-university-student-hub.herokuapp.com/android/register";

    // ********************** CLASS NAMES ************************************************
    public static final String HOME_FEED_NAME = "HomeFeed";
    public static final String MENTAL_HEALTH_SERVICES_NAME = "MentalHealthResources";
    public static final String UNIVERSITY_SERVICES_NAME = "UniversityServices";
    public static final String ADVERT_LISTING_NAME = "AdvertListing";
}
