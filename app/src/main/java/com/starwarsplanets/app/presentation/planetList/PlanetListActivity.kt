package com.starwarsplanets.app.presentation.planetList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.starwarsplanets.app.databinding.ActivityPlanetListBinding
import com.starwarsplanets.app.presentation.planetDetail.PlanetDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlanetListActivity : AppCompatActivity() {

    private val viewModel: PlanetViewModel by viewModels()
    private lateinit var binding: ActivityPlanetListBinding
    private lateinit var adapter: PlanetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanetListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        observeData()
        loadingState()
    }

    private fun initAdapter() {
        adapter = PlanetAdapter(
            onItemClick = {
                startActivity(PlanetDetailActivity.newIntent(this, it))
            }
        )
        binding.rcvPlanetList.adapter = adapter
        binding.rcvPlanetList.setHasFixedSize(true)

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.planetsStateFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun loadingState() {
        adapter.addLoadStateListener { loadState ->

            val isInitialLoad =
                loadState.source.refresh is LoadState.Loading && !binding.swipeRefreshLayout.isRefreshing
            binding.progressBar.visibility = if (isInitialLoad) View.VISIBLE else View.GONE

            val isRefreshing =
                loadState.source.refresh is LoadState.Loading && binding.swipeRefreshLayout.isRefreshing
            binding.swipeRefreshLayout.isRefreshing = isRefreshing

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.source.refresh as? LoadState.Error

            errorState?.let {
                Toast.makeText(this, "Error: ${it.error.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}