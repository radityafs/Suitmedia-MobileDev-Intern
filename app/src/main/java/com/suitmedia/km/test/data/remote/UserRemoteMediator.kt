package com.suitmedia.km.test.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState

class UserRemoteMediator(
    private val userApi: UserApi,
): PagingSource<Int, UserDataItem>() {
    override fun getRefreshKey(state: PagingState<Int, UserDataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDataItem> {
        return try {
            val page = params.key ?: 1
            val response = userApi.getUsers(page = page, pageCount = 10)

            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.data.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
