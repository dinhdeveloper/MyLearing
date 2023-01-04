package dinhtc.app.mylearning.service

import dinhtc.app.mylearning.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("users/{username}")
    suspend fun getAccount(@Path("username") username: String): AccountModel

    @GET("repos/{username}/{detail}/branches")
    fun getBranches(
        @Path("username") username: String,
        @Path("detail") detail: String
    ): List<BranchModel>

    @GET("repos/{username}/{detail}")
    fun getRepoDetail(
        @Path("username") username: String,
        @Path("detail") detail: String
    ): ReposDetailModel

    @GET("users/{username}/repos")
    fun getRepository(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): List<RepositoriesModel>

    @GET("user/repos")
    fun getAllRepository(
        @Query("access_token") access_token: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Call<List<RepositoriesModel>>

}