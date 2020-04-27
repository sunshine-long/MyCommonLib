package com.marlon.module.common.customview

import android.content.Context
import android.view.View
import android.widget.TextView
import com.marlon.module.common.base.BaseArrayAdpter
import com.marlon.module.common.javabean.Area
import com.marlon.utils.R


/**
 * @desc
 * @author Marlon
 * @date 2019/12/3
 */
class VillageAdapter(context: Context, resource: Int, datas: List<Area>) :
    BaseArrayAdpter<Area, VillageAdapter.ViewHolder>(context, resource, datas) {


    var checked = -1

    inner class ViewHolder(view: View) {
        val tvVillage = view.findViewById<TextView>(R.id.tv_village)
    }

    override fun initViewData(position: Int, holder: ViewHolder, t: Area?) {
        holder.tvVillage.text = t?.areaName
        if (checked == position){
//            holder.tvVillage.setBackgroundColor(context.resources.getColor(R.color.white))
            holder.tvVillage.setTextColor(context.resources.getColor(R.color.colorAccent))
            holder.tvVillage.setTextSize(16f)
        }else{
//            holder.tvVillage.setBackgroundColor(context.resources.getColor(R.color.bg_grey))
            holder.tvVillage.setTextColor(context.resources.getColor(R.color.black))
            holder.tvVillage.setTextSize(14f)
        }
    }

    override fun initViewHolder(view: View): ViewHolder {
        return ViewHolder(view)
    }
}