package com.lpt.lpt_v4.fabryka;

import com.lpt.lpt_v4.Uzytkownik;

import org.json.JSONException;
import org.json.JSONObject;

public class FabrykaZlecen {

    /**
     * Zwraca obiekt uzytkownika stworzony z odpowiedzi api
     *
     * @param userFromApi
     *
     * @return Uzytkownik
     * @throws JSONException
     */
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

    /**
     * Tworzy uzytkownika uzywanego podczas dodawania nowego zlecenia.
     *
     * @param user_id
     * @param title
     * @param description
     * @param lat
     * @param lng
     * @return JSONObject uzytkownik
     * @throws JSONException
     */
    public static JSONObject doNowegoZlecenia (
        String user_id,
        String title,
        String description,
        String lat,
        String lng
    ) throws JSONException {
        JSONObject uzytkownik_json = new JSONObject();
        uzytkownik_json.put("user_id", user_id);
        uzytkownik_json.put("title", title);
        uzytkownik_json.put("description", description);
        uzytkownik_json.put("lat", lat);
        uzytkownik_json.put("lng", lng);

        return  uzytkownik_json;
    }
}
