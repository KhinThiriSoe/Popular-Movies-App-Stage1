package com.khinthirisoe.popularmoviesappstage1.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.popularmoviesappstage1.GlideApp
import com.khinthirisoe.popularmoviesappstage1.R
import com.khinthirisoe.popularmoviesappstage1.core.service.ApiUrl
import com.khinthirisoe.popularmoviesappstage1.ui.detail.DetailActivity
import com.khinthirisoe.popularmoviesappstage1.ui.main.model.Result
import kotlinx.android.synthetic.main.list_movie.view.*

class MovieAdapter(private val mContext: Context, private var mListData: MutableList<Result>?) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    private val Context.layoutInflater get() = LayoutInflater.from(this)

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var poster = itemView.img_poster!!

    }

    fun addItems(items: MutableList<Result>) {
        this.mListData?.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = mContext.layoutInflater
            .inflate(R.layout.list_movie, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (mListData != null) {
            GlideApp.with(mContext)
                .load(ApiUrl.POSTER_PATH + mListData!![position].getPosterPath())
                .placeholder(R.drawable.ic_movie)
                .into(holder.poster)

            holder.poster.setOnClickListener {
                mContext.startActivity(
                    Intent(mContext, DetailActivity::class.java)
                        .putExtra("data", mListData!![position])
                )
            }
        }
    }

    override fun getItemCount(): Int {
        if (mListData == null) return 0
        return mListData!!.size
    }
}
