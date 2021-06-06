package com.example.a302projecct2;

import android.content.Context;

import com.example.a302projecct2.dataprovider.ItemClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonFuncs {

    private Context ctx;

    public JsonFuncs(Context ctx) {
        this.ctx = ctx;
    }


    /**
     * Loading dishes.json from assets folder
     * @param ctx
     * @return
     */
    public String LoadJsonFromAsset(Context ctx){
        String json = null;
        try{
            InputStream in = ctx.getAssets().open("dishes.json");
            int size = in.available();
            byte[] bbuffer = new byte[size];
            in.read(bbuffer);
            in.close();
            json= new String(bbuffer, "UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * Retrieves dishes for cuisine we pass in
     */
    public ArrayList<ItemClass> generateData(Context ctx, String cuisine){

        ArrayList<ItemClass> dishes = new ArrayList<ItemClass>();
        try {
            JSONObject obj = new JSONObject(LoadJsonFromAsset(this.ctx));
            JSONArray array = obj.getJSONArray(cuisine);

            for(int i=0; i<array.length(); i++){
                JSONObject dish = array.getJSONObject(i);
                dish.getJSONArray("dishImages").toString();
                ItemClass itemDish = new ItemClass(dish.getString("dishName"),
                        dish.getString("dishDescription"),
                        dish.getString("dishPrice"),
                        conv2StringArr(dish.getJSONArray("dishImages")));
                dishes.add(itemDish);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dishes;
    }

    /**
     * Convert JsonArray to String array
     */
    public String[] conv2StringArr(JSONArray jsonArray){
        List<String> list = new ArrayList<String>();

        for(int i=0; i < jsonArray.length(); i++) {
            try {
                list.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list.toArray(new String[list.size()]);
    }
}
