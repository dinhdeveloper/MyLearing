package dinhtc.app.mylearning.adapter

import androidx.recyclerview.widget.DiffUtil
import dinhtc.app.mylearning.model.AccountModel
import io.reactivex.rxjava3.annotations.Nullable

class UserDiffUtil(
    private val mOldUsers: List<AccountModel>,
    private val mNewUsers: List<AccountModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldUsers.size
    }

    override fun getNewListSize(): Int {
        return mNewUsers.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUsers[oldItemPosition].id === mNewUsers[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUserName = mOldUsers[oldItemPosition].name
        val newUserName = mNewUsers[newItemPosition].name
        return oldUserName == newUserName
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}