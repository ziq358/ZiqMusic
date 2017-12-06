package com.example.musicinkotlin.fragment.discover

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.example.musicinkotlin.R
import com.example.musicinkotlin.adapter.RecommendAdapter

/**
 * Created by john on 06/12/2017.
 */
class RecommendFragment: Fragment() {

    @BindView(R.id.rv_new_song)
    lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_recommend, container, false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view!!)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = RecommendAdapter(context)
    }
}