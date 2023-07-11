package com.suitmedia.km.test.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.suitmedia.km.test.data.remote.UserApi
import com.suitmedia.km.test.data.remote.UserDataItem
import com.suitmedia.km.test.data.remote.UserDto
import com.suitmedia.km.test.data.remote.UserRemoteMediator
import javax.inject.Inject
import com.suitmedia.km.test.utils.Result
import kotlinx.coroutines.flow.Flow

class UserRepository @Inject constructor(
    private val userApi: UserApi,
) {

    fun getUser(): Flow<PagingData<UserDataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                UserRemoteMediator(userApi)
            }
        ).flow
    }

}