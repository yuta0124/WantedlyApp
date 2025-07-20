package com.yuta0124.wantedlyapp.core.data.database

import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookmarkCompanyDatabaseMigrationTest {
    private val TEST_DB = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        instrumentation = InstrumentationRegistry.getInstrumentation(),
        databaseClass = BookmarkCompanyDatabase::class.java,
    )

    @Test
    fun version1_To_version2() {
        helper.createDatabase(
            name = TEST_DB,
            version = 1,
        ).apply {
            execSQL("INSERT INTO bookmark_company_table (id) VALUES (1)")
            close()
        }

        val db = helper.runMigrationsAndValidate(
            name = TEST_DB,
            version = 2,
            validateDroppedTables = true,
        )

        val cursor = db.query("SELECT * FROM bookmark_company_table ORDER BY id")
        cursor.use {
            assert(it.count == 1)

            // レコードを確認
            it.moveToFirst()
            assert(it.getInt(it.getColumnIndexOrThrow("id")) == 1)
            assert(it.getString(it.getColumnIndexOrThrow("title")) == "")
            assert(it.getString(it.getColumnIndexOrThrow("companyName")) == "")
            assert(it.getInt(it.getColumnIndexOrThrow("canBookMark")) == 1)
            assert(it.getString(it.getColumnIndexOrThrow("thumbnailUrl")) == "")
            assert(it.isNull(it.getColumnIndexOrThrow("companyLogoImage")))
        }

        db.close()
    }
}
