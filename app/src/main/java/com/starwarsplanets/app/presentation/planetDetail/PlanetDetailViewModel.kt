package com.starwarsplanets.app.presentation.planetDetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.starwarsplanets.app.domain.model.PlanetDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val planet = savedStateHandle.get<PlanetDto>(PlanetDetailActivity.EXTRA_PLANET)

    init {
        Log.d("TAG", "Planet: $planet")
    }
}