package com.unlim.lib

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlim.lib.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityMainBinding

    private var mData = arrayListOf(
        arrayListOf(
            "1",
            "2",
            "3"
        ),
        arrayListOf(
            "4",
            "5",
            "6"
        ),
        arrayListOf(
            "7",
            "8",
            "9"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)

        mViewBinding.vTab.addTab(mViewBinding.vTab.newTab().setText("tab0"))
        mViewBinding.vTab.addTab(mViewBinding.vTab.newTab().setText("tab1"))
        mViewBinding.vTab.addTab(mViewBinding.vTab.newTab().setText("tab2"))

        mViewBinding.vPage.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mViewBinding.vPage.adapter = AdapterPage(this, mData)
    }

    class AdapterPage(
        private val context: Context,
        private val pageData: ArrayList<ArrayList<String>>
    ) :
        RecyclerView.Adapter<VhPage>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhPage {
            return VhPage(
                LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return pageData.size
        }

        override fun onBindViewHolder(holder: VhPage, position: Int) {
            holder.vOnePage.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            holder.vOnePage.adapter = AdapterItem(pageData[position])
        }
    }

    class VhPage(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vOnePage: RecyclerView = itemView.findViewById(R.id.vOnePage)
    }

    class AdapterItem(private val itemData: ArrayList<String>) :
        RecyclerView.Adapter<VhItem>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhItem {
            return VhItem(
                LayoutInflater.from(parent.context).inflate(R.layout.item_func, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return itemData.size
        }

        override fun onBindViewHolder(holder: VhItem, position: Int) {
            holder.vItem.text = itemData[position]
        }
    }

    class VhItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vItem: AppCompatTextView = itemView.findViewById(R.id.vItem)
    }
}