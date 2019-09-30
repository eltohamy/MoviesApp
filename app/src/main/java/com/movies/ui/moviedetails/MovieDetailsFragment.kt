package com.movies.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.movies.R
import com.movies.databinding.MovieDetailsFragmentBinding
import com.movies.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_movies.*

/**
 * Created by Tohamy on 29/09/2019
 */
class MovieDetailsFragment : BaseFragment() {

    private lateinit var binding: MovieDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.movie_details_fragment, container, false)
        val view = binding.root
        view.isClickable = true
        view.requestFocus()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        baseActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        baseActivity?.supportActionBar?.setHomeButtonEnabled(true)
        baseActivity?.toolbar!!.collapseActionView()
        baseActivity?.toolbar!!.menu.findItem(R.id.search_item).isVisible = false;
        val args = MovieDetailsFragmentArgs.fromBundle(arguments!!)
        binding.movie = args.movie
        baseActivity?.supportActionBar?.title = args.movie.title
    }
}