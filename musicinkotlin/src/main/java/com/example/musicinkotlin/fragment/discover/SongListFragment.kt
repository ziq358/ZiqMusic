package com.example.musicinkotlin.fragment.discover

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.example.musicinkotlin.R
import com.example.musicinkotlin.adapter.SongListAdapter
import com.example.musicinkotlin.fragment.discover.MVP_Song_List.SongListContract
import com.example.musicinkotlin.fragment.discover.MVP_Song_List.SongListItemModel
import com.example.musicinkotlin.fragment.discover.MVP_Song_List.SongListPresenterImpl
import com.example.musicinkotlin.fragment.discover.MVP_Song_List.SongListProviderImpl

/**
 * Created by john on 06/12/2017.
 */
class SongListFragment : Fragment(), SongListContract.SongListViewRenderer {


    @BindView(R.id.rv_song_list)
    lateinit var mRecyclerView: RecyclerView

    private var presenter: SongListPresenterImpl? = null
    private var songListAdapter: SongListAdapter? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = SongListPresenterImpl(SongListProviderImpl(getContext()), this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_song_list, container, false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view!!)
        songListAdapter = SongListAdapter(context)
        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val type = songListAdapter!!.getItemViewType(position)
                val isOneLine = type == SongListAdapter.INT_ONE_ITEM_VIEW_TYPE || type == SongListAdapter.INT_LOAD_MORE_VIEW_TYPE
                return if (isOneLine) 2 else 1
            }
        }
        mRecyclerView.layoutManager = gridLayoutManager
        mRecyclerView.adapter = songListAdapter
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == recyclerView.adapter.itemCount - 1) {
                    if (presenter != null) presenter!!.loadMore()
                }
            }
        })

        mRecyclerView.addItemDecoration(SongListItemDecoration())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter?.init()
    }

    override fun showSongList(songList: MutableList<SongListItemModel>?) {
        songListAdapter?.removeDataList(songListAdapter!!.getItemCount() - 1)
        songListAdapter?.addDataList(songList)
    }

    override fun unableLoadMore() {
        songListAdapter?.removeDataList(songListAdapter!!.getItemCount() - 1)
        songListAdapter?.removeLoadMore()
        songListAdapter?.notifyDataSetChanged()
    }


    private inner class SongListItemDecoration : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            val totalCount = parent.adapter.itemCount
            val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
            outRect.top = 20
            if (itemPosition != totalCount - 1) {
                if (itemPosition % 2 == 0) {
                    outRect.left = 20
                    outRect.right = 20
                } else {
                    outRect.left = 20
                }
            }

        }
    }
}