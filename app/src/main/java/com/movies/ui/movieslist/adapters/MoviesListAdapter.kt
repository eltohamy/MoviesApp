package com.movies.ui.movieslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.movies.R
import com.movies.data.models.movieslist.Movie
import com.movies.databinding.ItemMovieBinding
import com.movies.ui.movieslist.MoviesListFragmentDirections
import com.movies.util.OnMovieClickListener

/**
 * Created by Tohamy on 29/09/2019
 */
class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {
    private lateinit var movies: ArrayList<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMovieBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return if (::movies.isInitialized) movies.size else 0
    }

    fun updateMoviesList(movies: List<Movie>) {
        this.movies = ArrayList()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    fun addMoviesList(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root),
        OnMovieClickListener {
        private val viewModel = MoviesViewModel()

        fun bind(movie: Movie) {
            viewModel.bind(movie)
            binding.viewModel = viewModel
            binding.listener = this
        }

        override fun onMovieClick(view: View, viewModel: MoviesViewModel) {
            val directions =
                MoviesListFragmentDirections.actionMainFragmentToMoviesListFragment(
                    viewModel.getMovie()
                )
            view.findNavController().navigate(directions)
        }
    }
}