package com.uwonders.tobaccomangager.customview

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.marlon.module.common.customview.FilterView
import com.marlon.module.common.javabean.Area
import com.marlon.utils.R
import com.uwonders.db.AreaDao
import com.uwonders.db.DBManager
import com.uwonders.tobaccomangager.R
import com.uwonders.tobaccomangager.adapter.VillageAdapter
import com.uwonders.tobaccomangager.javabean.Area
import com.uwonders.tobaccomangager.utils.ScreenUtils
import java.util.*


/**
 * @desc 两级下拉实现
 * @author Marlon
 * @date 2019/12/5
 */
class SelectVillagePopWindow {
    //选择弹出PopupWindow
    private var selectPopWindow: PopupWindow? = null
    //左边list 适配器
    private var leftVillageAdpter: VillageAdapter? = null
    //右边 list 适配器
    private var rightVillageAdpter: VillageAdapter? = null
    //右边数据
    private var village = ArrayList<Area>()
    //左边数据
    private var group = ArrayList<Area>()
    var linsener: FilterView.onSelectChangeLinsener? = null

    private fun initContextView(view: View, context: Context, select_village: TextView) {
        val leftVillageList: ListView = view.findViewById(R.id.left_list_village)
        val rightVillageList: ListView = view.findViewById(R.id.right_list_village)
        leftVillageAdpter = VillageAdapter(context, R.layout.select_village_list_item, village)
        leftVillageList.adapter = leftVillageAdpter
        rightVillageAdpter = VillageAdapter(context, R.layout.select_village_list_item, group)
        rightVillageList.adapter = rightVillageAdpter
        leftVillageList.setOnItemClickListener { adapterView, view, i, l ->
            if (leftVillageAdpter?.checked == i) {
                selectPopWindow?.dismiss()
            } else {
                leftVillageAdpter?.checked = i
                setRightDatas(village[i].childList as ArrayList<Area>)
                rightVillageAdpter?.checked = -1
            }
            leftVillageAdpter?.notifyDataSetChanged()
            rightVillageAdpter?.notifyDataSetChanged()
            select_village.text = village[i].areaName
            linsener?.onChanged(village[i].areaCode)
        }
        rightVillageList.setOnItemClickListener { adapterView, view, i, l ->
            if (leftVillageAdpter!!.checked == -1) {
                Toast.makeText(context, "请先选择村", Toast.LENGTH_SHORT).show()
            } else {
                rightVillageAdpter?.checked = i
                rightVillageAdpter?.notifyDataSetChanged()
                select_village.text =
                    village[leftVillageAdpter!!.checked].areaName + group[rightVillageAdpter!!.checked].areaName
                selectPopWindow?.dismiss()
                linsener?.onChanged(group[i].areaCode)
            }
        }

        view.findViewById<Button>(R.id.bt_reset).setOnClickListener {
            leftVillageAdpter?.checked = -1
            rightVillageAdpter?.checked = -1
            rightVillageAdpter?.clear()
            rightVillageAdpter?.notifyDataSetChanged()
            select_village.text = "请选择村组"
            initDatas()
            linsener?.onReSetClick()
        }

    }

    fun showPopWindow(context: Context, textView: TextView, view: View) {
        if (selectPopWindow == null) {
            val popContext = LayoutInflater.from(context).inflate(R.layout.pop_select_village, null)
            initContextView(popContext, context, textView)
            selectPopWindow = PopupWindow(
                popContext,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            selectPopWindow?.isOutsideTouchable = true
            selectPopWindow?.isTouchable = true
            selectPopWindow?.setBackgroundDrawable(ColorDrawable(context.resources.getColor(R.color.half_black)))
            selectPopWindow?.animationStyle = R.style.pop_Anim
            popContext.setOnClickListener {
                hidePopWindow()
            }
            selectPopWindow?.setOnDismissListener {
                selectPopWindow?.isFocusable = true
            }
        }
        if (leftVillageAdpter?.checked != -1) {
            group.clear()
            group.addAll(
                DBManager.getDoSession()?.areaDao?._queryArea_ChildList(
                    village.get(
                        leftVillageAdpter?.checked!!
                    ).areaCode
                ) as ArrayList<Area>
            )

        }
        if (Build.VERSION.SDK_INT >= 24) {
            //适配安卓7.0之后popwindow异常问题
            val rect = Rect()
            view.getLocalVisibleRect(rect)
            val height = ScreenUtils.getScreenSizePx().height - rect.bottom
            selectPopWindow?.height = height
        }
        initDatas()
        selectPopWindow?.showAsDropDown(view)
    }

    fun isShowing(): Boolean {
        if (selectPopWindow == null) {
            return false
        }
        return selectPopWindow?.isShowing!!
    }


    fun hidePopWindow() {
        if (selectPopWindow != null && selectPopWindow!!.isShowing) {
            selectPopWindow?.dismiss()
        }
    }

    private fun setLeftDatas(leftData: ArrayList<Area>) {
        village.clear()
        village.addAll(leftData)
        leftVillageAdpter?.notifyDataSetChanged()
    }

    private fun setRightDatas(rightData: ArrayList<Area>) {
        group.clear()
        group.addAll(rightData)
        rightVillageAdpter?.notifyDataSetChanged()
    }

    private fun initDatas() {
        setLeftDatas(
            DBManager.getDoSession()?.areaDao?.queryBuilder()?.where(
                AreaDao.Properties.AreaType.eq("9"))?.list() as ArrayList<Area>
        )
//        setRightDatas(DBManager.getDoSession()?.areaDao?._queryArea_ChildList(village[0].areaCode) as ArrayList<Area>)
    }

}