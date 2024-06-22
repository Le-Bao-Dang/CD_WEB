package org.uaf.cd_web.entity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import com.restfb.types.User;

import java.io.IOException;

public class RestFb {

    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(ConstantsFB.FACEBOOK_LINK_GET_TOKEN, ConstantsFB.FACEBOOK_APP_ID, ConstantsFB.FACEBOOK_APP_SECRET, ConstantsFB.FACEBOOK_REDIRECT_URL, code);
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static User getUserInfo(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, ConstantsFB.FACEBOOK_APP_SECRET, Version.LATEST);
        return facebookClient.fetchObject("me", User.class, Parameter.with("fields", "name, email,friends"));
    }


}
