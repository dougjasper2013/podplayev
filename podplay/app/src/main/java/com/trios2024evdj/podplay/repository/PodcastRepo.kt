package com.trios2024evdj.podplay.repository

import com.trios2024evdj.podplay.model.Podcast

class PodcastRepo {
    fun getPodcast(feedUrl: String): Podcast? {
        return Podcast(feedUrl, "No Name","No description", "No image")
    }
}
