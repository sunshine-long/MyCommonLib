package com.unht.myutils.mpandroidchart;

import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.unht.myutils.app.App;
import com.uwonders.smokemonitor.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marlon
 * @desc
 * @date 2018/10/17
 */
public class LineChartManager {
    private LineChart lineChart;
    private YAxis leftYAxis;
    private YAxis rightYAxis;
    private XAxis xAxis;
    private LineData lineData;
    private LineDataSet lineDataSet;
    private List<ILineDataSet> lineDataSets = new ArrayList<>();
    private SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式  

    //一条曲线
    public LineChartManager(LineChart mLineChart, String name, int color) {
        this.lineChart = mLineChart;
        leftYAxis = lineChart.getAxisLeft();
        rightYAxis = lineChart.getAxisRight();
        xAxis = lineChart.getXAxis();
        initLineChart();
        initLineDataSet(name, color);
    }

    //多条曲线
    public LineChartManager(LineChart mLineChart, List<String> names, List<Integer> colors) {
        this.lineChart = mLineChart;
        leftYAxis = lineChart.getAxisLeft();
        rightYAxis = lineChart.getAxisRight();
        xAxis = lineChart.getXAxis();
        initLineChart();
        initLineDataSet(names, colors);
    }

    /**
     * 初始化LineChar
     */
    private void initLineChart() {
        //是否设置表格背景
        lineChart.setDrawGridBackground(false);
        //显示边界
        lineChart.setDrawBorders(false);
        lineChart.setDescription(null);
//        lineChart.setDoubleTapToZoomEnabled(false);//双击屏幕缩放
//        lineChart.setScaleEnabled(true);
//        lineChart.setScaleXEnabled(true);
//        lineChart.setScaleYEnabled(false);
        //折线图例 标签 设置
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        legend.setFormSize(18.5f);
        legend.setFormLineWidth(10);
        legend.setTextColor(App.getInstance().getResources().getColor(R.color.colorLineCharLegendText));
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        setXAxis(0f, 130f, 10f, 6, Color.TRANSPARENT, 0,
                App.getInstance().getResources().getColor(R.color.colorLineChatText), 11,
                App.getInstance().getResources().getColor(R.color.colorLineChatGrid));
        setYAxis(0f, 70f, 10f, 6, Color.TRANSPARENT, 0,
                App.getInstance().getResources().getColor(R.color.colorLineChatText), 11,
                App.getInstance().getResources().getColor(R.color.colorLineChatGrid));
    }

    /**
     * 设置Y轴相关属性
     *
     * @param minimum       最小值
     * @param maximum       最大值
     * @param granularity   间隔距离
     * @param lablecount    坐标个数
     * @param axislinecolor 轴的颜色
     * @param axislinewidth 轴的宽
     * @param textColor     设置值得颜色
     * @param textSize      设置值得字体大小
     * @param gridColor     设置网格线的颜色
     */
    public void setYAxis(float minimum,
                         float maximum,
                         float granularity,
                         int lablecount,
                         int axislinecolor,
                         int axislinewidth,
                         int textColor,
                         int textSize,
                         int gridColor) {
        //设置左边
        leftYAxis.setAxisMinimum(minimum);
//        leftYAxis.setAxisMaximum(maximum);
//        leftYAxis.setGranularity(granularity);
        leftYAxis.setAxisLineWidth(axislinewidth);
        leftYAxis.setAxisLineColor(axislinecolor);
        leftYAxis.setLabelCount(lablecount, false);
        leftYAxis.setTextColor(textColor);
        leftYAxis.setTextSize(textSize);
        leftYAxis.setTypeface(Typeface.DEFAULT_BOLD);
        leftYAxis.setGridColor(gridColor);
        leftYAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf((int) value)/*.concat("℃")*/;
            }
        });
        //设置右边
        rightYAxis.setAxisMinimum(minimum);
//        rightYAxis.setAxisMaximum(maximum);
//        rightYAxis.setGranularity(granularity);
        rightYAxis.setAxisLineWidth(axislinewidth);
        rightYAxis.setAxisLineColor(axislinecolor);
        rightYAxis.setLabelCount(lablecount, false);
        rightYAxis.setTypeface(Typeface.DEFAULT_BOLD);
        rightYAxis.setTextColor(textColor);
        rightYAxis.setTextSize(textSize);
        rightYAxis.setGridColor(gridColor);
        rightYAxis.setLabelCount(lablecount, false);
    }


    /**
     * 设置X轴相关属性
     *
     * @param minimum       最小值
     * @param maximum       最大值
     * @param granularity   间隔距离
     * @param lablecount    坐标个数
     * @param axislinecolor 轴的颜色
     * @param axislinewidth 轴的宽
     * @param textColor     设置值得颜色
     * @param textSize      设置值得字体大小
     * @param gridColor     设置网格线的颜色
     */
    public void setXAxis(float minimum,
                         float maximum,
                         float granularity,
                         int lablecount,
                         int axislinecolor,
                         int axislinewidth,
                         int textColor,
                         int textSize,
                         int gridColor) {
        //值：BOTTOM,BOTH_SIDED,BOTTOM_INSIDE,TOP,TOP_INSIDE
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(minimum);
//        xAxis.setAxisMaximum(maximum);
//        xAxis.setGranularity(granularity);
        xAxis.setLabelCount(lablecount, false);
        xAxis.setAxisLineWidth(axislinewidth);
        xAxis.setAxisLineColor(axislinecolor);
        xAxis.setTextColor(textColor);
        xAxis.setTextSize(textSize);
        xAxis.setGridColor(gridColor);
        xAxis.setTypeface(Typeface.DEFAULT_BOLD);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
//                return df.format(new Date((long) value));
                return String.valueOf((int) value);
            }
        });
    }


    /**
     * 初始化折线(一条线)
     *
     * @param name
     * @param color
     */
    private void initLineDataSet(String name, int color) {
        lineDataSet = new LineDataSet(null, name);
        lineDataSet.setColor(color);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setLineWidth(2);
        //设置曲线填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setValueTextSize(8f);
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //添加一个空的 LineData
        lineData = new LineData();
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    /**
     * 初始化折线（多条线）
     *
     * @param names
     * @param colors
     */
    private void initLineDataSet(List<String> names, List<Integer> colors) {
        //添加一个空的 LineData
        lineData = new LineData();
        for (int i = 0; i < names.size(); i++) {
            lineDataSet = new LineDataSet(null, names.get(i));
            lineDataSet.setColor(colors.get(i));
            lineDataSet.setLineWidth(2);
            lineDataSet.setDrawValues(false);
            lineDataSet.setDrawCircles(true);
            lineDataSet.setCircleRadius(3);
            lineDataSet.setCircleColor(colors.get(i));
            lineDataSet.setCircleHoleRadius(2);
            if (i == 1) {
                lineDataSet.enableDashedLine(10f, 5f, 0f);
            }
            lineDataSets.add(lineDataSet);
            lineData.addDataSet(lineDataSet);
        }
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    /**
     * 批量添加数据
     *
     * @param entries      数据源列表
     * @param dataSetIndex 要添加的数据的线的ID
     */
    public void addEntrys(List<Entry> entries, int dataSetIndex) {
        for (Entry entry : entries) {
            addEntry(entry, dataSetIndex);
        }
    }

    public void removeAllDatas() {
        for (int i = 0; i < lineDataSets.size(); i++) {
            lineDataSets.get(i).clear();
        }
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
    }

    public void removeLineDatas(int dataSetIndex) {
        lineDataSets.get(dataSetIndex).clear();
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
    }


    /**
     * 添加单个数据
     *
     * @param entry        数据源
     * @param dataSetIndex 要添加数据的线
     */
    public void addEntry(Entry entry, int dataSetIndex) {
        LineDataSet lineDataSet = (LineDataSet) lineDataSets.get(dataSetIndex);
        lineDataSet.addEntry(entry);
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        //设置在曲线图中显示的最大数量
//        lineChart.setVisibleXRangeMaximum(6);
        //移到某个位置
        lineChart.moveViewToX(lineDataSet.getEntryCount() - 1);
    }

    /**
     * 添加单个数据
     *
     * @param entry        数据源
     * @param dataSetIndex 要添加数据的线
     */
    public void addEntryOrdered(Entry entry, int dataSetIndex) {
        LineDataSet lineDataSet = (LineDataSet) lineDataSets.get(dataSetIndex);
        lineDataSet.addEntryOrdered(entry);
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        //设置在曲线图中显示的最大数量
//        lineChart.setVisibleXRangeMaximum(6);
        //移到某个位置
        lineChart.moveViewToX(lineDataSet.getEntryCount() - 1);
    }


    public List<Entry> getEntrys(int dataSetIndex) {
        LineDataSet lineDataSet = (LineDataSet) lineDataSets.get(dataSetIndex);
        return lineDataSet.getValues();
    }

    public List<Entry> getEntrys() {
        return getEntrys(0);
    }

    public int getEntryIndex(Entry entry, int dataSetIndex) {
        LineDataSet lineDataSet = (LineDataSet) lineDataSets.get(dataSetIndex);
        return lineDataSet.getEntryIndex(entry);
    }

    public int getEntryIndex(Entry entry) {
        return getEntryIndex(entry, 0);
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftYAxis.addLimitLine(hightLimit);
        lineChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftYAxis.addLimitLine(hightLimit);
        lineChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        lineChart.setDescription(description);
        lineChart.invalidate();
    }
}
