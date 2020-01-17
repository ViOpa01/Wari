package com.iisysgroup.payvice.dialogs

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wizarpos.emvsample.R
import kotlinx.android.synthetic.main.fragment_plan_list_dialog.*

typealias Selector = ((Int) -> Unit)

const val PLAN_LIST = "plan_list"

abstract class BasePlanDialogFragment<T : RecyclerView.ViewHolder> : BottomSheetDialogFragment() {

    var selector: Selector? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_plan_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = getAdapter()
    }

    abstract fun getAdapter(): RecyclerView.Adapter<T>
}