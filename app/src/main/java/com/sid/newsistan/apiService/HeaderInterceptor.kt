package com.sid.newsistan.apiService

import com.sid.newsistan.utils.Utils
import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("X-Api-Key", Utils.API_KEY)
                .build()
        )
    }
}