package com.uwonders.tobaccomangager.customview.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.marlon.utils.R

import java.util.*

/**
 * @desc 列表选择框
 * @author Marlon
 * @date 2019/12/13
 */
class ListDialogFragment : DialogFragment(), View.OnClickListener {
    //列表选择标题
    private lateinit var title: TextView
    //添加选择按钮
    private  var addBtn: Button? = null
    //适配器
    private lateinit var madapter: DialogListAdpter
    //数据list
    private var lists = Array List<String>()
    //点击事件
    lateinit var linsener: OnListClickLinsener
    //标题
    private  var  titleString:String? = null

    companion object {
        fun newInstance(title: String, datas: ArrayList<String>): ListDialogFragment? {
            val args = Bundle()
            args.putString("title", title)
            args.putStringArrayList("datas", datas)
            val fragment = ListDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = inflater.inflate(R.layout.layout_dialog_listview, null)
        title = view.findViewById(R.id.tv_select_title)
        titleString = arguments?.getString("title")
        title.text = titleString
        addBtn = view.findViewById(R.id.bt_add)
        addBtn?.setOnClickListener(this)
        val closeBtn = view.findViewById<ImageView>(R.id.bt_close)
        closeBtn.setOnClickListener(this)
        val listView = view.findViewById<ListView>(R.id.list_view)
        lists.clear()
        lists.addAll(arguments?.getStringArrayList("datas")!!)
        madapter = DialogListAdpter(context!!, lists)
        listView.adapter = madapter
        listView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (title.text.toString() == titleString){
                    title.text = ""
                }
                title.append(madapter.getItem(p2))
//                dismiss()
                linsener.onClick(lists[p2], p2)
            }
        })
        madapter.notifyDataSetChanged()
        return view
    }

    fun setDatas(datas: List<String>) {
        lists.clear()
        lists.addAll(datas)
    }

    fun setTitle(string: String) {
        title.text = string
    }

    fun notifyDatas() {
        madapter.notifyDataSetChanged()
    }

    fun setAddBtnText(text: String) {
        if (addBtn != null){
            setAddBtnVisible(View.VISIBLE)
            addBtn?.text = text
        }
    }

    fun setAddBtnVisible(visible:Int){
        addBtn?.visibility = visible
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.bt_close -> {
                dismiss()
            }

            R.id.bt_add -> {
                title.text = titleString
                linsener.onAddClick(p0)

            }
        }
    }

}

interface OnListClickLinsener {
    fun onClick(string: String, position: Int)
    fun onAddClick(view: View) {}
}