package com.marlon.module.common.utils;

import android.graphics.Rect;

public class RectUitls {

    public static int getAreas(Rect rect) {
        return Math.abs(rect.right - rect.left) * (rect.bottom - rect.top);
    }

    public static int diffArea(Rect rectA, Rect rectB) {
        int area = Math.abs(getAreas(rectA) - getAreas(rectB));
        return area;
    }

    public static double diffCenterPointDistance(Rect rectA, Rect rectB) {
        int diffX = Math.abs(rectA.centerX() - rectB.centerX());
        int diffY = Math.abs(rectA.centerY() - rectB.centerY());
        double distance = Math.sqrt(diffX * diffX + diffY * diffY);
        return distance;
    }
}
