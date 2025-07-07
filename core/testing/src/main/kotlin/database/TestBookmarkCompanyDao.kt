package database

import com.yuta0124.wantedlyapp.core.data.database.BookmarkCompanyDao
import com.yuta0124.wantedlyapp.core.data.database.BookmarkCompanyTable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestBookmarkCompanyDao : BookmarkCompanyDao {
    override fun getBookmarkCompanies(): Flow<List<BookmarkCompanyTable>> =
        flow { emit(listOf()) }

    override suspend fun insertCompany(target: BookmarkCompanyTable) {
        /* noop */
    }

    override suspend fun deleteTargetCompany(target: BookmarkCompanyTable) {
        /* noop */
    }
}
