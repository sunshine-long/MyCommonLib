package com.marlon.myutils;

public class MyRectangle {
    public int top;
    public int left;
    public int right;
    public int bottom;
    public int width;
    public int height;
    public int x;
    public int y;

    public MyRectangle()
    {

    }

    public MyRectangle(int top,int left,int right,int bottom)
    {
        this.top = top;
        this.left = left;
        this.top = x;
        this.left = y;
        this.right = right;
        this.bottom = bottom;
        this.width = bottom - top;
        this.height = right - left;
    }

    public int getArea()
    {
        return this.height * this.width;
    }

    public static int getOverLappingArea(MyRectangle a,MyRectangle b)
    {
        int overLappingArea = 0;

        int startX = Math.min(a.x,b.x);
        int endX = Math.max(a.x + a.width, b.x + b.width);
        int overLappingWidth = a.width + b.width - (endX - startX);

        int startY = Math.min(a.y, b.y);
        int endY = Math.max(a.y + a.height, b.y + b.height);
        int overLappingHeight = a.height + b.height - (endY - startY);

        if(overLappingWidth <= 0 || overLappingHeight <= 0)
        {
            overLappingArea = 0;
        }
        else
        {
            overLappingArea = overLappingWidth * overLappingHeight;
        }
        return overLappingArea;

    }

    public static double getOverLappingRate(MyRectangle a,MyRectangle b)
    {
        double overLappingRate = 0.0;
        int overLappingArea = getOverLappingArea(a,b);
        if(overLappingArea == 0)
        {
            overLappingRate = 0.0;
        }
        else
        {
            int areaA = a.getArea();
            int areaB = b.getArea();
            overLappingRate = (double)overLappingArea / (double)(areaA + areaB - overLappingArea);
        }
        return overLappingRate;
    }
}

