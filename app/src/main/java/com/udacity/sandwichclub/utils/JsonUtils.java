package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();
        List<String>  alsoarray1 = new ArrayList<>();

        try {

            JSONObject object = new JSONObject(json);
            JSONObject name = object.getJSONObject("name");

            String mainName = name.getString("mainName");
            sandwich.setMainName(mainName);


            JSONArray alsoarray = name.getJSONArray("alsoKnownAs");

            if(alsoarray.length()!=0) {


                for (int i = 0; i < alsoarray.length(); i++) {
                    alsoarray1.add(alsoarray.getString(i));
                }
                sandwich.setAlsoKnownAs(alsoarray1);
            }
            else{
                alsoarray1.add("no data");
                sandwich.setAlsoKnownAs(alsoarray1);
            }
            String placeOfOrigin = object.getString("placeOfOrigin");

            if(placeOfOrigin.isEmpty()){
                sandwich.setPlaceOfOrigin("no data");
            }
            else{
                sandwich.setPlaceOfOrigin(placeOfOrigin);
            }

            String description = object.getString("description");
            sandwich.setDescription(description);

            String image = object.getString("image");
            sandwich.setImage(image);

            JSONArray ingredients = object.getJSONArray("ingredients");


            List<String> ingredients1 = new ArrayList<>();
            for (int i = 0; i < ingredients.length(); i++) {
                ingredients1.add(ingredients.getString(i));
            }
            sandwich.setIngredients(ingredients1);


        }

        catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
