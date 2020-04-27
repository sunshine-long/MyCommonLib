package com.marlon.retrofitclent.handler;

import com.marlon.retrofitclent.exception.ResponeException;
import com.marlon.retrofitclent.exception.ServerException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.HttpException;
import retrofit2.Response;

import static com.marlon.retrofitclent.exception.ResponeException.BAD_GATEWAY;
import static com.marlon.retrofitclent.exception.ResponeException.FORBIDDEN;
import static com.marlon.retrofitclent.exception.ResponeException.GATEWAY_TIMEOUT;
import static com.marlon.retrofitclent.exception.ResponeException.INTERNAL_SERVER_ERROR;
import static com.marlon.retrofitclent.exception.ResponeException.NOT_FOUND;
import static com.marlon.retrofitclent.exception.ResponeException.SERVICE_UNAVAILABLE;
import static com.marlon.retrofitclent.exception.ResponeException.UNAUTHORIZED;

/**
 * @author Marlon
 * @desc
 * @date 2019/5/14
 */
public class ExceptionHandle {

    public static ResponeException handleException(Throwable e) {
        ResponeException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponeException(e, INTERNAL_SERVER_ERROR);
            Response<?> response = httpException.response();
            try {
                String string = response.errorBody().string();
                JSONObject jsonObject = new JSONObject(string);
                String error = (String) jsonObject.get("error_description");
                ex.message = error;
                return ex;
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            switch (httpException.code()) {
                case UNAUTHORIZED:
                    break;
                case FORBIDDEN:
                    break;
                case NOT_FOUND:
                    break;
                case GATEWAY_TIMEOUT:
                    break;
                case INTERNAL_SERVER_ERROR:
                    break;
                case BAD_GATEWAY:
                    break;
                case SERVICE_UNAVAILABLE:
                    break;
                default:
                    ex.code = httpException.code();
                    ex.message = "无网络,请重试!";
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponeException(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else {
            ex = new ResponeException(e, INTERNAL_SERVER_ERROR);
            ex.message = "未知错误";
            return ex;
        }
    }
}
