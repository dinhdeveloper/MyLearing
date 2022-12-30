package dinhtc.app.mylearning.repository

import dinhtc.app.mylearning.service.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getListCustomer() = apiService.getListCategory()
}