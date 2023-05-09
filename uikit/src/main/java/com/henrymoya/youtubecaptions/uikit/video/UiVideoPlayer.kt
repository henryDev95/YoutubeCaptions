package com.henrymoya.youtubecaptions.uikit.video

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun UiVideoPlayer(
    modifier: Modifier = Modifier,
    videoId: String,
    context: Context,
) {

    val youtubePlayer = remember {
        YouTubePlayerView(context).apply {
            addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                }
            )
        }
    }

    AndroidView(
        {
            youtubePlayer
        }, modifier = modifier
    )

}