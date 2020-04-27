package com.marlon.module.common.customview

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import com.marlon.module.common.javabean.Area
import com.marlon.utils.R
import com.uwonders.tobaccomangager.customview.SelectVillagePopWindow
import java.util.*

/**
 * @desc 多级下拉列表
 * @author Marlon
 * @date 2020/3/11
 */
class FilterView : ConstraintLayout {
    private val selectVillagePopWindow = SelectVillagePopWindow()
    var villageList = ArrayList<Area>()
    var groupList = ArrayList<Area>()
    var linsener: onSelectChangeLinsener? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    fun setOnSelectChangeLinsener(linsener: onSelectChangeLinsener) {
        this.linsener = linsener
        selectVillagePopWindow.linsener = linsener
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.filter_layout, this)
        searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                linsener?.onTextChanged(p0, p1, p2, p3)
            }
        })
        select_village.setOnClickListener {
            if (selectVillagePopWindow.isShowing()) {
                selectVillagePopWindow.hidePopWindow()
            } else {
                selectVillagePopWindow.showPopWindow(
                    context,
                    select_village,
                    layout_filter
                )
            }
        }
    }


    interface onSelectChangeLinsener {
        fun onChanged(areaCode: String)
        fun onReSetClick()
        fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
    }

}