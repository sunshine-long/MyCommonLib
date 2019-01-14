package com.marlon.module.common.javabean.convernet;

import com.google.gson.Gson;
import com.marlon.module.common.javabean.PointBean;

import org.greenrobot.greendao.converter.PropertyConverter;


/**
 * @author Marlon
 * @desc
 * @date 2019/1/9
 */
public class ConvernetPoint implements PropertyConverter<PointBean, String> {

    @Override
    public PointBean convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        return new Gson().fromJson(databaseValue, PointBean.class);

    }

    @Override
    public String convertToDatabaseValue(PointBean entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        return new Gson().toJson(entityProperty);
    }
}
