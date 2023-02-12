package com.example.mvvm_retrofit.adapter

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.mvvm_retrofit.R
import com.example.mvvm_retrofit.model.DataItem
import kotlinx.android.synthetic.main.item_pics.view.*

/**
 * @Description:
 * @author zouji
 * @date 2023/2/12
 */
class PicsAdapter : RecyclerView.Adapter<PicsAdapter.PicsViewHolder>() {

    inner class PicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            if (!TextUtils.equals(oldItem.url, newItem.url)) {
                return false
            }
            if (!TextUtils.equals(oldItem.author, newItem.author)) {
                return false
            }
            if (!TextUtils.equals(oldItem.downloadUrl, newItem.downloadUrl)) {
                return false
            }
            if (oldItem.width != newItem.width) {
                return false
            }
            if (oldItem.height != newItem.height) {
                return false
            }
            return true
        }

        /**
         * getChangePayload在areContentsTheSame()返回false时被调用。
         *  实现了getChangePayload之后，onBindViewHolder会增加一个入参，就是getChangePayload返回的item中有变更的内容。
         * @param oldItem
         * @param newItem
         * @return
         */
        override fun getChangePayload(oldItem: DataItem, newItem: DataItem): Any {
            // 把有改变的字段存入bundle返回
            val bundle = Bundle()
            if (!TextUtils.equals(oldItem.url, newItem.url)) {
                bundle.putString("url", newItem.url)
            }
            if (!TextUtils.equals(oldItem.author, newItem.author)) {
                bundle.putString("author", newItem.author)
            }
            if (!TextUtils.equals(oldItem.downloadUrl, newItem.downloadUrl)) {
                bundle.putString("downloadUrl", newItem.downloadUrl)
            }
            if (oldItem.width != newItem.width) {
                bundle.putInt("width", newItem.width)
            }
            if (oldItem.height != newItem.height) {
                bundle.putInt("height", newItem.height)
            }
            return bundle
        }
    }
    val diff = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicsViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_pics, parent, false)
        return PicsViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: PicsViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val bundle = payloads[0] as Bundle
            holder.itemView.apply {
                ivImage.load(bundle.getString("downloadUrl").takeIf {
                    !TextUtils.isEmpty(it)
                })
                tvImageId.text = bundle.getString("id").takeIf {
                    !TextUtils.isEmpty(it)
                }
                tvImageSize.text = "${bundle.getString("width")} x ${bundle.getString("height")}".takeIf {
                    !TextUtils.isEmpty(bundle.getString("width")) && !TextUtils.isEmpty(bundle.getString("height"))
                }
                tvImageAuthor.text = bundle.getString("author").takeIf {
                    !TextUtils.isEmpty(bundle.getString("author"))
                }
            }
        }
    }

    override fun onBindViewHolder(holder: PicsViewHolder, position: Int) {
        val picItem = diff.currentList[position]
        holder.itemView.apply {
            ivImage.load(picItem.downloadUrl)
            tvImageId.text = picItem.id
            tvImageSize.text = "${picItem.width} x ${picItem.height}"
            tvImageAuthor.text = picItem.author
        }
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }
}