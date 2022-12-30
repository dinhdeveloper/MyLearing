package dinhtc.app.mylearning.service

import dinhtc.app.mylearning.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("category/list")
    suspend fun getListCategory(): List<UserModel>

    @GET("users/{username}")
    fun getAccount(@Path("username") username: String): UserModel

}