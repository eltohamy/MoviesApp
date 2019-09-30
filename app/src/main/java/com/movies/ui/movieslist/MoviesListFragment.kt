package com.movies.ui.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.malinskiy.superrecyclerview.OnMoreListener
import com.movies.R
import com.movies.databinding.MoviesListFragmentBinding
import com.movies.ui.base.BaseFragment
import com.movies.ui.movieslist.adapters.MarginDecoration
import com.movies.util.MoviesListViewModelFactory
import kotlinx.android.synthetic.main.activity_movies.*

/**
 * Created by Tohamy on 29/09/2019
 */
class MoviesListFragment : BaseFragment(), OnMoreListener {

    private lateinit var viewModel: MoviesListViewModel
    private lateinit var binding: MoviesListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movies_list_fragment, container, false)
        val view = binding.root
        view.isClickable = true
        view.requestFocus()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        baseActivity!!.activityComponent()!!.inject(this)
        baseActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        baseActivity?.supportActionBar?.setHomeButtonEnabled(false)
        baseActivity?.supportActionBar?.title = getString(R.string.app_name)
        val linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.moviesRecycler.setLayoutManager(linearLayoutManager)
        binding.moviesRecycler.addItemDecoration(MarginDecoration(activity!!))
        binding.moviesRecycler.setupMoreListener(this, 1)
        viewModel = ViewModelProviders.of(this, MoviesListViewModelFactory(dataManager))
            .get(MoviesListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null)
                showError(errorMessage) else hideError()
        })
        viewModel.loadMoreVisibility.observe(this, Observer {
            finishLoading()
        })
        binding.viewModel = viewModel
    }

    //show error message
    private fun showError(@StringRes errorMessage: Int) {
        binding.textError.setText(errorMessage)
        binding.group.visibility = View.VISIBLE
    }

    //hide error message
    private fun hideError() {
        binding.group.visibility = View.GONE
    }

    // hide recycler progress view after load all pages
    private fun finishLoading() {
        binding.moviesRecycler.moreProgressView.visibility = View.GONE
    }

    override fun onMoreAsked(
        overallItemsCount: Int,
        itemsBeforeMore: Int,
        maxLastVisiblePosition: Int
    ) {
        viewModel.loadMoreMovies()
    }

    // show hidden search item
    override fun onResume() {
        if (baseActivity != null && baseActivity?.toolbar!!.menu.findItem(R.id.search_item) != null)
            baseActivity?.toolbar!!.menu.findItem(R.id.search_item).isVisible = true;
        super.onResume()
    }
}
