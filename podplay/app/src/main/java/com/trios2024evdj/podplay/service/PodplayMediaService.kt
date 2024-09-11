package com.trios2024evdj.podplay.service

import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat

class PodplayMediaService : MediaBrowserServiceCompat() {

    private lateinit var mediaSession: MediaSessionCompat

    override fun onCreate() {
        super.onCreate()
        createMediaSession()
    }

    override fun onLoadChildren(parentId: String,
                                result: Result<MutableList<MediaBrowserCompat.MediaItem>>) {
        // To be implemented
    }

    override fun onGetRoot(clientPackageName: String,
                           clientUid: Int, rootHints: Bundle?): BrowserRoot? {
        // To be implemented
        return null
    }

    private fun createMediaSession() {
        // 1
        mediaSession = MediaSessionCompat(this, "PodplayMediaService")
        // 2
        setSessionToken(mediaSession.sessionToken)
        // 3
        // Assign Callback
        val callback = PodplayMediaCallback(this, mediaSession)
        mediaSession.setCallback(callback)

    }


}
