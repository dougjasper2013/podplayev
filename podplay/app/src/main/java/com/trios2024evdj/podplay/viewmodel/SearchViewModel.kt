package com.trios2024evdj.podplay.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.trios2024evdj.podplay.repository.ItunesRepo
import com.trios2024evdj.podplay.service.PodcastResponse
import com.trios2024evdj.podplay.util.DateUtils

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    var iTunesRepo: ItunesRepo? = null

    data class PodcastSummaryViewData(
        var name: String? = "",
        var lastUpdated: String? = "",
        var imageUrl: String? = "",
        var feedUrl: String? = "",
        var country: String? = "")

    private fun itunesPodcastToPodcastSummaryView(
        itunesPodcast: PodcastResponse.ItunesPodcast):
            PodcastSummaryViewData {
        return PodcastSummaryViewData(
            itunesPodcast.collectionCensoredName,
            DateUtils.jsonDateToShortDate(itunesPodcast.releaseDate),
            itunesPodcast.artworkUrl30,
            itunesPodcast.feedUrl,
            itunesPodcast.country)
    }

    // 1
    suspend fun searchPodcasts(term: String): List<PodcastSummaryViewData> {
        // 2
        val results = iTunesRepo?.searchByTerm(term)

        // 3
        if (results != null && results.isSuccessful) {
            // 4
            val podcasts = results.body()?.results
            // 5
            if (!podcasts.isNullOrEmpty()) {
                // 6
                return podcasts.map { podcast ->
                    itunesPodcastToPodcastSummaryView(podcast)
                }
            }
        }
        // 7
        return emptyList()
    }



}
