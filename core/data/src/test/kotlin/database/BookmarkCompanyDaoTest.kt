package database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.yuta0124.wantedlyapp.core.data.database.BookmarkCompanyDao
import com.yuta0124.wantedlyapp.core.data.database.BookmarkCompanyDatabase
import com.yuta0124.wantedlyapp.core.data.database.BookmarkCompanyTable
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("NonAsciiCharacters")
@RunWith(AndroidJUnit4::class)
class BookmarkCompanyDaoTest {
    private lateinit var database: BookmarkCompanyDatabase
    private lateinit var dao: BookmarkCompanyDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, BookmarkCompanyDatabase::class.java
        ).build()
        dao = database.bookmarkCompanyDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun `insertCompany_データが挿入されること`() = runTest {
        val data = BookmarkCompanyTable(
            0,
            "title",
            "companyName",
            false,
            "thumbnailUrl",
            "companyLogoImage",
        )

        dao.insertCompany(data)
        val actual = dao.getBookmarkCompanies().first().first()

        assertThat(actual).isEqualTo(data)
    }

    @Test
    fun `deleteBookmarkById_特定のidを持つデータ削除ができること`() = runTest {
        val data1 = BookmarkCompanyTable(
            1,
            "title",
            "companyName",
            false,
            "thumbnailUrl",
            "companyLogoImage",
        )
        val data2 = data1.copy(id = 2)

        dao.insertCompany(data1)
        dao.insertCompany(data2)

        dao.deleteBookmarkById(data1.id)
        val actual = dao.getBookmarkCompanies().first()

        assertThat(actual).doesNotContain(data1)
    }
}
