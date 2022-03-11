package ua.tabarkevych.composemvi.navigation

import ua.tabarkevych.composemvi.navigation.Screen.Gif.Args.GIF_URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {

    object Gifs : Screen("gifs_screen")

    object Gif : Screen("gif_screen/{$GIF_URL}") {

        fun setUrl(gifUrl: String): String {
            val encodedUrl = URLEncoder.encode(gifUrl, StandardCharsets.UTF_8.toString())
            return this.route.replace(oldValue = "{$GIF_URL}", newValue = encodedUrl)
        }

        object Args {

            const val GIF_URL = "gif_url"
        }
    }

    object Theming : Screen("theming_screen")
}