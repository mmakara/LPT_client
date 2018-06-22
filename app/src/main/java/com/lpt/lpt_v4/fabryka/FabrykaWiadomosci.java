package com.lpt.lpt_v4.fabryka;

import com.lpt.lpt_v4.Uzytkownik;

import org.json.JSONException;
import org.json.JSONObject;

public class FabrykaWiadomosci {
    public static Uzytkownik zApi(JSONObject userFromApi) throws JSONException
    {
        Uzytkownik uzytkownik = new Uzytkownik();
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
