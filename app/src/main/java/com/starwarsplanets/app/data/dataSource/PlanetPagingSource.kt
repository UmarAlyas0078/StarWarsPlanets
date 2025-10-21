package com.starwarsplanets.app.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.starwarsplanets.app.data.remote.Planet
import com.starwarsplanets.app.data.remote.PlanetApi
import javax.inject.Inject

class PlanetPagingSource @Inject constructor(
    private val api: PlanetApi
) : PagingSource<Int, Planet>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Planet> {
        val page = params.key ?: 1
        return try {
            val response = api.getPlants(page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.next == null) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Planet>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}