package com.starwarsplanets.app.presentation.planetList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.starwarsplanets.app.domain.model.PlanetDto
import com.starwarsplanets.app.domain.usecase.GetPlanetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase,
) : ViewModel() {

    private val _planetsStateFlow = MutableStateFlow<PagingData<PlanetDto>>(PagingData.empty())
    val planetsStateFlow: StateFlow<PagingData<PlanetDto>> = _planetsStateFlow

    init {
        fetchPlanets()
    }

    private fun fetchPlanets() {
        viewModelScope.launch {
            getPlanetsUseCase().collect { pagingData ->
                _planetsStateFlow.value = pagingData
            }
        }
    }
}