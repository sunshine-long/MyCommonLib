package com.unht.myutils.mpandroidchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.unht.myutils.R;
import com.unht.myutils.app.App;

/**
 * @author Marlon
 * @desc
 * @date 2018/11/1
 */
public class LineChartMarkerView extends MarkerView {
    public static final int ARROW_SIZE = 40; // 箭头的大小
    private static final float CIRCLE_OFFSET = 10;//因为我这里的折点是圆圈，所以要偏移，防止直接指向了圆心
    private static final float STOKE_WIDTH = 2;//这里对于stroke_width的宽度也要做一定偏移
    private final TextView mTvTime;
    private final TextView mTvTemperture;
    private Paint paint = new Paint();
    private boolean isSetting = false;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */
    public LineChartMarkerView(Context context) {
        super(context, R.layout.markerview_layout);
        mTvTemperture = findViewById(R.id.tv_temperature);
        mTvTime = findViewById(R.id.tv_time);
    }

    public void isSetting(boolean setting) {
        isSetting = setting;
    }

    /* 每次画 MakerView 时都会触发 Callback 方法，通常会在此方法内更新 View 的內容 */
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        int index = highlight.getDataSetIndex();
        String temperture = e.getY() + "";
        String time = e.getX() + "";
        int color ;
        if (isSetting){
            if (index == 0) {
                color = App.getInstance().getResources().getColor(R.color.colorBlueLine);
            } else {
                color = App.getInstance().getResources().getColor(R.color.colorRedLine);
            }
        }else {
            if (index == 0) {
                color = App.getInstance().getResources().getColor(R.color.colorRedLine);
            } else {
                color = App.getInstance().getResources().getColor(R.color.colorBlueLine);
            }
        }
        paint.setColor(color);
        paint.setAlpha(130);
        mTvTime.setTextColor(color);
        mTvTemperture.setTextColor(color);
        mTvTemperture.setText("温度：" + temperture + "℃");
        mTvTime.setText("时间：" + time + "h");
    }

/*    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight() - 10);
    }*/


    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        MPPointF offset = getOffset();
        Chart chart = getChartView();
        float width = getWidth();
        float height = getHeight();
        // posY \posX 指的是markerView左上角点在图表上面的位置
        //处理Y方向
        // 如果点y坐标小于markerView的高度，如果不处理会超出上边界，处理了之后这时候箭头是向上的，我们需要把图标下移一个箭头的大小
        if (posY <= height + ARROW_SIZE) {
            offset.y = ARROW_SIZE;
        } else {
            //否则属于正常情况，因为我们默认是箭头朝下，然后正常偏移就是，需要向上偏移markerView高度和arrow size，再加一个stroke的宽度，因为你需要看到对话框的上面的边框
            offset.y = -height - ARROW_SIZE - STOKE_WIDTH; // 40 arrow height   5 stroke width
        }
        //处理X方向，分为3种情况，1、在图表左边 2、在图表中间 3、在图表右边
        if (posX > chart.getWidth() - width) {
            //如果超过右边界，则向左偏移markerView的宽度
            offset.x = -width;
        } else {
            //默认情况，不偏移（因为是点是在左上角）
            offset.x = 0;
            if (posX > width / 2) {
                //如果大于markerView的一半，说明箭头在中间，所以向右偏移一半宽度
                offset.x = -(width / 2);
            }
        }
        return offset;
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        //绘制边框的画笔
        paint.setStrokeWidth(STOKE_WIDTH);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        //绘制底色白色的画笔
        Paint whitePaint = new Paint();
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setColor(getResources().getColor(R.color.colorChartMarkerBg));

        Chart chart = getChartView();
        float width = getWidth();
        float height = getHeight();

        MPPointF offset = getOffsetForDrawingAtPoint(posX, posY);
        int saveId = canvas.save();
        //处理超过上边界
        Path path = new Path();
        if (posY < height + ARROW_SIZE) {
            path = new Path();
            path.moveTo(0, 0);
            //超过右边界
            if (posX > chart.getWidth() - width) {
                path.lineTo(width - ARROW_SIZE, 0);
                path.lineTo(width, -ARROW_SIZE + CIRCLE_OFFSET);
                path.lineTo(width, 0);
            } else {
                //在图表中间
                if (posX > width / 2) {
                    path.lineTo(width / 2 - ARROW_SIZE / 2, 0);
                    path.lineTo(width / 2, -ARROW_SIZE + CIRCLE_OFFSET);
                    path.lineTo(width / 2 + ARROW_SIZE / 2, 0);
                } else {//超过左边界
                    path.lineTo(0, -ARROW_SIZE + CIRCLE_OFFSET);
                    path.lineTo(0 + ARROW_SIZE, 0);
                }
            }
            path.lineTo(0 + width, 0);
            path.lineTo(0 + width, 0 + height);
            path.lineTo(0, 0 + height);
            path.lineTo(0, 0);
            path.offset(posX + offset.x, posY + offset.y);
        } else {//没有超过上边界
            path = new Path();
            path.moveTo(0, 0);
            path.lineTo(0 + width, 0);
            path.lineTo(0 + width, 0 + height);
            if (posX > chart.getWidth() - width) {
                path.lineTo(width, height + ARROW_SIZE - CIRCLE_OFFSET);
                path.lineTo(width - ARROW_SIZE, 0 + height);
                path.lineTo(0, 0 + height);
            } else {
                if (posX > width / 2) {
                    path.lineTo(width / 2 + ARROW_SIZE / 2, 0 + height);
                    path.lineTo(width / 2, height + ARROW_SIZE - CIRCLE_OFFSET);
                    path.lineTo(width / 2 - ARROW_SIZE / 2, 0 + height);
                    path.lineTo(0, 0 + height);
                } else {
                    path.lineTo(0 + ARROW_SIZE, 0 + height);
                    path.lineTo(0, height + ARROW_SIZE - CIRCLE_OFFSET);
                    path.lineTo(0, 0 + height);
                }
            }
            path.lineTo(0, 0);
            path.offset(posX + offset.x, posY + offset.y);
        }

        // translate to the correct position and draw
        canvas.drawPath(path, whitePaint);
        canvas.drawPath(path, paint);
        canvas.translate(posX + offset.x, posY + offset.y);
        draw(canvas);
        canvas.restoreToCount(saveId);
    }
}
