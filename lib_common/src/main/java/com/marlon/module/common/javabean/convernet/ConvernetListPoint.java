package com.marlon.module.common.javabean.convernet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marlon.module.common.javabean.PointBean;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Marlon
 * @desc
 * @date 2019/1/9
 */
public class ConvernetListPoint implements PropertyConverter<List<PointBean>, String> {

    @Override
    public List<PointBean> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        return new Gson().fromJson(databaseValue, new TypeToken<ArrayList<PointBean>>() {
        }.getType());

    }

    @Override
    public String convertToDatabaseValue(List<PointBean> entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        return new Gson().toJson(entityProperty);
    }
}
