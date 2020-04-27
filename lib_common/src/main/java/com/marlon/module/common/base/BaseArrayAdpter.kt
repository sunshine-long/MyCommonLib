package com.marlon.module.common.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

/**
 * @desc
 * @author Marlon
 * @date 2019/12/3
 */
abstract class BaseArrayAdpter<T, H >(
    context: Context,
    resource: Int,
    datas: List<T>
) :
    ArrayAdapter<T>(context, resource, datas) {
    private val resource = resource
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val t: T? = getItem(position)
        val holder: H
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(this.resource, parent, false)
            holder = initViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as H
        }
        initViewData(position, holder, t)
        return view
    }
    protected abstract fun initViewData(position: Int, holder: H, t: T?)
    protected abstract fun initViewHolder(view: View):H
}
