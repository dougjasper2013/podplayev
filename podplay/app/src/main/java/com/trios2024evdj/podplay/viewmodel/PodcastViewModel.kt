package com.trios2024evdj.podplay.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.trios2024evdj.podplay.model.Episode
import com.trios2024evdj.podplay.model.Podcast
import com.trios2024evdj.podplay.repository.PodcastRepo
import java.util.Date

class PodcastViewModel(application: Application) : AndroidViewModel(application) {

    var podcastRepo: PodcastRepo? = null
    var activePodcastViewData: PodcastViewData? = null

    data class PodcastViewData(
        var subscribed: Boolean = false,
        var feedTitle: String? = "",
        var feedUrl: String? = "",
        var country: String? = "",
        var imageUrl: String? = "",
        var episodes: List<EpisodeViewData>
    )

    data class EpisodeViewData (
        var guid: String? = "",
        var title: String? = "",
        var description: String? = "",
        var mediaUrl: String? = "",
        var releaseDate: Date? = null,
        var duration: String? = ""
    )

    private fun episodesToEpisodesView(episodes: List<Episode>): List<EpisodeViewData> {
        return episodes.map {
            EpisodeViewData(
                it.guid,
                it.title,
                it.description,
                it.mediaUrl,
                it.releaseDate,
                it.duration
            )
        }
    }

    private fun podcastToPodcastView(podcast: Podcast): PodcastViewData {
        return PodcastViewData(
            false,
            podcast.feedTitle,
            podcast.feedUrl,
            podcast.country,
            podcast.imageUrl,
            episodesToEpisodesView(podcast.episodes)
        )
    }

    // 1
    fun getPodcast(podcastSummaryViewData: SearchViewModel.PodcastSummaryViewData): PodcastViewData? {
        // 2
        val repo = podcastRepo ?: return null
        val feedUrl = podcastSummaryViewData.feedUrl ?: return null
        // 3
        val podcast = repo.getPodcast(feedUrl)
        // 4
        podcast?.let {
            // 5
            it.feedTitle = podcastSummaryViewData.name ?: ""
            // 6
            it.imageUrl = podcastSummaryViewData.imageUrl ?: ""

            // 6
            it.country = podcastSummaryViewData.country ?: ""

            // 7
            activePodcastViewData = podcastToPodcastView(it)
            // 8
            return activePodcastViewData
        }
        // 9
        return null
    }


}
