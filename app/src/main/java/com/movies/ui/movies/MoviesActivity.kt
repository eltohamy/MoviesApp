package com.movies.ui.movies

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.movies.R
import com.movies.databinding.ActivityMoviesBinding
import com.movies.ui.base.BaseActivity
import com.movies.ui.movieslist.MoviesListFragmentDirections
import com.movies.util.MoviesViewModelFactory
import kotlinx.android.synthetic.main.activity_movies.*


/**
 * Created by Tohamy on 29/09/2019
 */
class MoviesActivity : BaseActivity() {

    private lateinit var viewModel: MoviesViewModel

    private lateinit var searchAutoComplete: SearchView.SearchAutoComplete

    private lateinit var searchAutoCompleteAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityMoviesBinding>(this, R.layout.activity_movies)
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProviders.of(this, MoviesViewModelFactory(dataManager))
            .get(MoviesViewModel::class.java)
        viewModel.searchKeywords.observe(this, Observer { searchKeywordsList ->
            searchAutoCompleteAdapter.addAll(searchKeywordsList)
            searchAutoCompleteAdapter.notifyDataSetChanged()
            searchAutoComplete.callOnClick()
        })
    }

    //init search view and it's listeners
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main, menu)
        val searchMenu = menu.findItem(R.id.search_item)

        val searchView = searchMenu.actionView as SearchView

        searchAutoComplete =
            searchView.findViewById(R.id.search_src_text) as SearchView.SearchAutoComplete
        searchAutoComplete.setBackgroundColor(Color.WHITE)
        searchAutoComplete.setTextColor(Color.BLACK)
        searchAutoComplete.setHint(R.string.search)
        searchAutoComplete.setDropDownBackgroundResource(R.color.gray)
        searchAutoCompleteAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arrayOf(""))
        searchAutoComplete.setAdapter(searchAutoCompleteAdapter)

        searchAutoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, _, itemIndex, _ ->
                val queryString = adapterView.getItemAtPosition(itemIndex) as String
                searchAutoComplete.setText(queryString)
                toolbar.collapseActionView()
                search(queryString)
            }

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                toolbar.collapseActionView()
                search(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.trim().length > 2)
                    viewModel.loadSearchKeywords(1, newText)
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    //open search fragment
    private fun search(query: String) {
        val directions =
            MoviesListFragmentDirections.actionMainFragmentToMoviesSearchFragment(query)
        findNavController(this, R.id.nav_host_fragment).navigate(directions)
    }

    override fun onSupportNavigateUp() =
        findNavController(this, R.id.nav_host_fragment).navigateUp()
}
