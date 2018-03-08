package com.example.coolweather.util;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/3/6.
 */

public class Utility {
    /**
     * 解析处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        try {
            JSONArray allprovinces=new JSONArray(response);
            for (int i=0;i<allprovinces.length();i++){
                JSONObject provinceObject=allprovinces.getJSONObject(i);
                Province province=new Province();
                province.setProvinceCode(provinceObject.getInt("id"));
                province.setProvinceName(provinceObject.getString("name"));
                province.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解析处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId){
        try {
            JSONArray allCities=new JSONArray(response);
            for (int i=0;i<allCities.length();i++){
                JSONObject cityObject=allCities.getJSONObject(i);
                City city=new City();
                city.setCityCode(cityObject.getInt("id"));
                city.setCityName(cityObject.getString("name"));
                city.setProvinceId(provinceId);
                city.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解析处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response,int cityId){
        try {
            JSONArray allCounties=new JSONArray(response);
            for (int i=0;i<allCounties.length();i++){
                JSONObject countyObject=allCounties.getJSONObject(i);
                County county=new County();
                county.setWeatherId(countyObject.getString("weather_id"));
                county.setCountyName(countyObject.getString("name"));
                county.setCityId(cityId);
                county.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
