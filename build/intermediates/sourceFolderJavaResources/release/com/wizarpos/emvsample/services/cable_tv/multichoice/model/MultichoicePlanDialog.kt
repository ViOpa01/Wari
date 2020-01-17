package com.iisysgroup.payvice.dialogs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.itex.richard.payviceconnect.model.DstvModel
import com.wizarpos.emvsample.R
import kotlinx.android.synthetic.main.fragment_plan_list_dialog_item.view.*


class MultichoicePlanDialog : BasePlanDialogFragment<MultichoicePlanDialog.ViewHolder>() {
    var list: List<DstvModel.Data> = ArrayList()

    override fun getAdapter() = Adapter(list)


    inner class ViewHolder internal constructor(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.fragment_plan_list_dialog_item, parent, false)) {

        private val descriptionTextView = itemView.descriptionText
        private val amountTextView = itemView.amountText

        init {
            itemView.setOnClickListener {

                selector?.invoke(adapterPosition)
            }
        }

        fun bindView(item: DstvModel.Data) {
            descriptionTextView.text = item.name
            amountTextView.text = "\u20A6 ${item.amount}"
        }
    }

    inner class Adapter(private val list: List<DstvModel.Data>) :
            RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bindView(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }


    }


    companion object {
        fun newInstance(dataList: List<DstvModel.Data>,
                        selectionHandler: (Int) -> Unit): MultichoicePlanDialog =
                MultichoicePlanDialog().apply {
                    this.list = dataList
                    this.selector = selectionHandler
                }

    }
}
