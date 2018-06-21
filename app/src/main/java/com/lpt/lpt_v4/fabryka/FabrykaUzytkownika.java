package com.lpt.lpt_v4.fabryka;

import com.lpt.lpt_v4.User;

import org.json.JSONException;
import org.json.JSONObject;

public class FabrykaUzytkownika {
    public static User zApi(JSONObject userFromApi) throws JSONException
    {
        User uzytkownik = new User();
        uzytkownik.id = userFromApi.getString("id");
        uzytkownik.first_name = userFromApi.getString("first_name");
        uzytkownik.username = userFromApi.getString("username");
        uzytkownik.email = userFromApi.getString("email");
        uzytkownik.account_type = userFromApi.getString("account_type");
        uzytkownik.lat = userFromApi.getString("lat");
        uzytkownik.lng = userFromApi.getString("lng");

        return uzytkownik;
    }
}
