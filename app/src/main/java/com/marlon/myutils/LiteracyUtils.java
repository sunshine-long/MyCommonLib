package com.marlon.myutils;

import java.text.DecimalFormat;

/**
 * @author Marlon
 * @desc 计算两个矩阵的相识度
 * @date 2018/8/17
 */
public class LiteracyUtils {
    public static double cosineSimilarity(float[] A, float[] B){
        if(A.length!=B.length){
            return -1;
        }
        if(A==null||B==null){
            return -1;
        }
        float fenzi=0;
        for(int i=0;i<A.length;i++){
            fenzi+=A[i]*B[i];
        }
        float left=0;
        float right=0;
        for(int i=0;i<A.length;i++){
            left+=A[i]*A[i];
            right+=B[i]*B[i];
        }
        if(left*right==0){
            return -1;
        }
        double result=fenzi/Math.sqrt(left*right);
        DecimalFormat df=new DecimalFormat("#.####");
        return Double.parseDouble(df.format(result));
    }

}
