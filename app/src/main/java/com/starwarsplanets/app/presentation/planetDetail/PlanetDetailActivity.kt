package com.starwarsplanets.app.presentation.planetDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.starwarsplanets.app.R
import com.starwarsplanets.app.databinding.ActivityPlanetDetailBinding
import com.starwarsplanets.app.domain.model.PlanetDto
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanetDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlanetDetailBinding
    private val viewModel: PlanetDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationIconTint(resources.getColor(android.R.color.white, theme))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed() // Navigate back()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun initData() {
        with(viewModel) {
            val progressDrawable = CircularProgressDrawable(this@PlanetDetailActivity).apply {
                strokeWidth = 5f
                centerRadius = 30f
                start()
            }

            binding.apply {
                tvPlanetName.text = getString(R.string.planet_name, planet?.name.orEmpty())
                tvOrbitalPeriod.text = getString(R.string.orbit, planet?.orbitalPeriod.orEmpty())
                tvGravity.text = getString(R.string.gravity, planet?.gravity.orEmpty())

                ivThumbnail.load(planet?.highResolution) {
                    placeholder(progressDrawable)
                    error(R.drawable.error_placeholder)
                    crossfade(true)
                }
            }
        }
    }

    companion object {
        const val EXTRA_PLANET = "planet"

        fun newIntent(context: Context, planet: PlanetDto): Intent {
            return Intent(context, PlanetDetailActivity::class.java).apply {
                putExtra(EXTRA_PLANET, planet)
            }
        }
    }
}