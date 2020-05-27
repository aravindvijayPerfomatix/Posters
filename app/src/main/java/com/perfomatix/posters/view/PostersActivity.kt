package com.perfomatix.posters.view

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.perfomatix.posters.R
import com.perfomatix.posters.base.BaseActivity
import com.perfomatix.posters.databinding.PostersActivityBinding
import com.perfomatix.posters.model.Content
import com.perfomatix.posters.model.PageData
import com.perfomatix.posters.utils.*
import com.perfomatix.posters.view.adapter.PosterAdapter
import kotlinx.android.synthetic.main.appbar_layout.*
import java.io.BufferedReader

class PostersActivity : BaseActivity() {

    private lateinit var mBinding: PostersActivityBinding
    private var mPosterAdapter = PosterAdapter(this)
    private var mLoading = false
    private var mPageCount = 1

    companion object {
        const val DRAWABLE_RIGHT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.posters_activity)
        mBinding.rvPosterList.adapter = mPosterAdapter

        initView()

        ivSearchIcon.setRxOnClickListener {
            llBack.visibility = GONE
            tvAppBarHeading.visibility = GONE
            etSearch.visibility = VISIBLE
            etSearch.requestFocus()
            ivSearchIcon.visibility = GONE
            Utils.showKeyboard(this)
        }

        etSearch.setOnTouchListener(View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= etSearch.right - etSearch.compoundDrawables[DRAWABLE_RIGHT].bounds.width()
                ) {
                    Utils.hideKeyboard(this)
                    ivSearchIcon.visibility = VISIBLE
                    llBack.visibility = VISIBLE
                    tvAppBarHeading.visibility = VISIBLE
                    etSearch.visibility = GONE
                    etSearch.text?.clear()
                    initView()
                    return@OnTouchListener true
                }
            }
            false
        })

        llBack.setRxOnClickListener {
            this.finish()
        }

        etSearch.setRxOnEditTextChangeAfter {
            if (it.length >= 3) {
                searchCatalogueByText(it)
            }
            if (it.isEmpty()) {
                initView()
            }
        }

    }


    /**
     * To initialize the view and load data to adapter
     */
    private fun initView() {
        mPageCount = 1
        mBinding.rvPosterList.visibility = VISIBLE
        mBinding.txtViewNoData.visibility = GONE
        mPosterAdapter.setPosterList(generateData())

        mBinding.rvPosterList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val gridLayoutManager = recyclerView
                    .layoutManager as GridLayoutManager?

                if (!mLoading && gridLayoutManager!!.itemCount <= gridLayoutManager.findLastVisibleItemPosition() + 2) {
                    mLoading = true
                    mPosterAdapter.addMoreData(generateData())
                }
            }


        })
    }

    /**
     * Function to fetch JSON data from file and parse that
     */
    private fun generateData(): List<Content> {

        val listOfPoster = mutableListOf<Content>()

        if (mPageCount == 1) {
            val inputStream = this.assets.open(PAGE1)
            val fileString = inputStream.bufferedReader().use(BufferedReader::readText)
            val imagePageList = Gson().fromJson(fileString, PageData::class.java)
            listOfPoster.addAll(imagePageList.page.content_items.content)
            tvAppBarHeading.text = imagePageList.page.title
            mLoading = false
        }
        if (mPageCount == 2) {
            val inputStream = this.assets.open(PAGE2)
            val fileString = inputStream.bufferedReader().use(BufferedReader::readText)
            val imagePageList = Gson().fromJson(fileString, PageData::class.java)
            listOfPoster.addAll(imagePageList.page.content_items.content)
            tvAppBarHeading.text = imagePageList.page.title
            mLoading = false
        }
        if (mPageCount == 3) {
            val inputStream = this.assets.open(PAGE3)
            val fileString = inputStream.bufferedReader().use(BufferedReader::readText)
            val imagePageList = Gson().fromJson(fileString, PageData::class.java)
            listOfPoster.addAll(imagePageList.page.content_items.content)
            tvAppBarHeading.text = imagePageList.page.title
            mLoading = true
        }
        mPageCount++
        return listOfPoster
    }

    /**
     * Function to search the list
     * @param searchQuery
     */
    private fun searchCatalogueByText(searchQuery: String) {
        val filterList = mPosterAdapter.getPosterList().filter {
            it.name.contains(searchQuery, true)
        }
        if (filterList.isNotEmpty()) {
            mPosterAdapter.setPosterList(filterList)
            mBinding.rvPosterList.visibility = VISIBLE
            mBinding.txtViewNoData.visibility = GONE
        } else {
            mBinding.rvPosterList.visibility = GONE
            mBinding.txtViewNoData.visibility = VISIBLE
        }

    }

}
