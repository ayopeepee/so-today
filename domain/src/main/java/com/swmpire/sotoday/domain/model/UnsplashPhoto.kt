package com.swmpire.sotoday.domain.model



data class UnsplashPhoto(
    val id: String,
    val urls: UnsplashPhotoUrls,
) {
    data class UnsplashPhotoUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String
    )
}


