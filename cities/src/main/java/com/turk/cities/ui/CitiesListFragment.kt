package com.turk.cities.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.turk.cities.BR
import com.turk.cities.R
import com.turk.cities.action.CitiesAction
import com.turk.cities.databinding.CitiesListFragmentBinding
import com.turk.cities.state.CitiesState
import com.turk.cities.viewmodel.CitiesViewModel
import com.turk.common.base.BaseFragment
import com.turk.common.base.BaseViewModel
import com.turk.common.base.GeneralAdapter
import com.turk.dtos.city.City
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitiesListFragment :BaseFragment<CitiesListFragmentBinding,CitiesState,CitiesAction>() {

    private val citiesViewModel: CitiesViewModel by viewModel()

    override fun layoutResourceId(): Int =R.layout.cities_list_fragment

    override fun getViewModel(): BaseViewModel<CitiesState, CitiesAction> =citiesViewModel
    private val adapter = GeneralAdapter(BR.city, R.layout.city_item, City.DIFF_CALLBACK,mutableListOf(R.id.favouriteBtn))

    override fun initialize(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        binding.viewModel=citiesViewModel
        binding.adapter=adapter
        adapter.clickListenerSpecific={city,view,index->

            when(view.id){

                R.id.favouriteBtn->{
                    city.isFavourite=!city.isFavourite
                    dispatchIntent(CitiesAction.UpdateCityFavouriteStatus(city))
                    adapter.notifyItemChanged(index)
                }
            }

        }
        dispatchIntent(CitiesAction.FetchCities)

        binding.swipeRefresh.setOnRefreshListener {
            dispatchIntent(CitiesAction.FetchCities)
        }

        launchOnLifecycleScope {

            citiesViewModel.cities.collectLatest {

                adapter.submitList(it)
            }
        }

    }

    private lateinit var optionMenu: Menu
    private lateinit var searcItem: MenuItem
    private lateinit var reset_menu: MenuItem
    private var searchView: SearchView? = null

    override fun handleState(state: CitiesState) {

        when(state){

          is  CitiesState.Loading->{

              binding.swipeRefresh.isRefreshing=true
          }

            is CitiesState.Cities->{

                binding.swipeRefresh.isRefreshing=false

            }
        }
    }

    private fun initSearch() {

        searchView?.run {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (!isIconified) {

                        dispatchIntent(CitiesAction.SearchCity(query))

                    }

                    return true
                }

                @SuppressLint("DefaultLocale")
                override fun onQueryTextChange(newText: String): Boolean {


                    return false
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        optionMenu = menu
        activity?.menuInflater?.inflate(R.menu.weather_menu, menu)
        searcItem = optionMenu.findItem(R.id.action_search)
        reset_menu = optionMenu.findItem(R.id.reset_menu)
        searchView = searcItem.actionView as SearchView
        reset_menu.isVisible = false
        initSearch()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.action_search->{

                reset_menu.isVisible = true
            }
            R.id.reset_menu -> {
                searchView?.onActionViewCollapsed()
                dispatchIntent(CitiesAction.FetchCities)
                reset_menu.isVisible=false
            }
        }
        return super.onOptionsItemSelected(item)
    }
}