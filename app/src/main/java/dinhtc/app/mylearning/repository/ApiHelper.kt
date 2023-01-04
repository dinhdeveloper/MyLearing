package dinhtc.app.mylearning.repository

import dinhtc.app.mylearning.model.AccountModel
import dinhtc.app.mylearning.model.BranchModel

interface ApiHelper {

    suspend fun getAccount(accString :String): AccountModel

    suspend fun getBranches(accString :String,detail: String): List<BranchModel>

}