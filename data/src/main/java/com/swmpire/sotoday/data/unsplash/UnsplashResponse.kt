package com.swmpire.sotoday.data.unsplash

import com.swmpire.sotoday.domain.model.UnsplashPhoto

data class UnsplashResponse(
    val results: List<UnsplashPhoto>
)
