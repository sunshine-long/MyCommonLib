package com.uwonders.tobaccomangager.customview.dialog

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import com.uwonders.db.AreaDao
import com.uwonders.db.DBManager
import com.uwonders.tobaccomangager.javabean.Area
import com.uwonders.tobaccomangager.utils.ToastUtils

/**
 * @desc 选择行政区域的fragment
 * @author Marlon
 * @date 2020/3/26
 */
class SelectAreaDialog(var mContext:Context) {
    var onAreaSelectLinsener:OnAreaSelectLinsener?=null

    fun show(manager:FragmentManager){
        var areaBean: Area? = null
        var areas = DBManager.getDoSession()?.areaDao?.queryBuilder()?.where(
            AreaDao.Properties.AreaType.eq("9")
        )?.list()
        var strs = ArrayList<String>()
        if (areas != null) {
            for (area in areas) {
                strs.add(area.areaName)
            }
        }
        val listDialogFragment: ListDialogFragment? =
            ListDialogFragment.newInstance("请选择行政区域", strs)
        listDialogFragment?.linsener = object : OnListClickLinsener {
            override fun onClick(string: String, position: Int) {
                areaBean = areas?.get(position)
                onAreaSelectLinsener?.onSelectArea(areaBean,string,position)
                areas = areas?.get(position)?.childList
                strs.clear()
                if (areas != null && areas?.size!! > 0) {
                    for (area in areas!!) {
                        strs.add(area.areaName)
                    }
                    listDialogFragment?.setDatas(strs)
                    listDialogFragment?.notifyDatas()
                } else {
                    listDialogFragment?.dismiss()
                }
                listDialogFragment?.setAddBtnText("重新选择")
            }

            override fun onAddClick(view: View) {
                listDialogFragment?.setAddBtnVisible(View.GONE)
                if (areaBean == null) {
                    ToastUtils.show(mContext,"你已在最上层")
                } else {
                    areaBean = null
                    areas = DBManager.getDoSession()?.areaDao?.queryBuilder()?.where(
                        AreaDao.Properties.AreaType.eq("9")
                    )?.list()
                    strs.clear()
                    if (areas != null) {
                        for (area in areas!!) {
                            strs.add(area.areaName)
                        }
                        listDialogFragment?.setDatas(strs)
                        listDialogFragment?.notifyDatas()
                    }
                }

            }
        }
        listDialogFragment?.show(manager, "AreaLIST")
    }

    interface OnAreaSelectLinsener{
        fun onSelectArea(area:Area?,string: String, position: Int)
    }
}