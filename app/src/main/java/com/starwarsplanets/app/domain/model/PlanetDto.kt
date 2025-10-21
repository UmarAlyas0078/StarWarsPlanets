package com.starwarsplanets.app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlanetDto(
    val name: String,
    val climate: String,
    val orbitalPeriod: String,
    val gravity: String,
    val thumbnail: String,
    val highResolution: String,
) : Parcelable