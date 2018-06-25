package com.lpt.lpt_v4.fabryka;

import com.lpt.lpt_v4.Uzytkownik;
import com.lpt.lpt_v4.aktywnosci.NoweKonto;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Tworzy obiekty użytkownika na różne potrzeby systemu
 */
public class FabrykaUzytkownikow {
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

    public static JSONObject doLogowania(String username, String password) throws JSONException {
        JSONObject obiektLogowania = new JSONObject();
        obiektLogowania.put("username", username);
        obiektLogowania.put("password", password);

        return obiektLogowania;
    }

    public static JSONObject doNowegoKonta (
        String first_name,
        String username,
        String lat,
        String lng,
        String email,
        String password,
        String rodzaj_konta
    ) throws JSONException {
        JSONObject uzytkownik_json = new JSONObject();
        uzytkownik_json.put("first_name", first_name);
        uzytkownik_json.put("username", username);
        uzytkownik_json.put("lat", lat);
        uzytkownik_json.put("lng", lng);
        uzytkownik_json.put("email", email);
        uzytkownik_json.put("password", password);
        uzytkownik_json.put("rodzaj_konta", rodzaj_konta);

        return  uzytkownik_json;
    }
}
