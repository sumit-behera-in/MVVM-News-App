package apps.sumit.apitest.features.data.remote

import apps.sumit.apitest.common.Constants
import apps.sumit.apitest.features.data.remote.dto.ApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "in",
        @Query("apiKey")
        apiKey: String = Constants.API_KEY,
    ): ApiResponseDto

    @GET("/v2/everything")
    suspend fun search(
        @Query("q")
        keyWord: String,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY,
    ): ApiResponseDto
}