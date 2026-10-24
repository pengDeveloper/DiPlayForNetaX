package com.shilapi.xcertplay.update

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [29])
class UpdateCatalogTest {
    private val releaseJson = """
        [
          {
            "tag_name": "v0.2.15",
            "draft": true,
            "assets": [
              {"name": "xPlay-0.2.15.apk", "browser_download_url": "https://github.com/shihabal3amri/xPlay/releases/download/v0.2.15/xPlay-0.2.15.apk"},
              {"name": "SHA256SUMS.txt", "browser_download_url": "https://github.com/shihabal3amri/xPlay/releases/download/v0.2.15/SHA256SUMS.txt"}
            ]
          },
          {
            "tag_name": "v0.2.14",
            "draft": false,
            "prerelease": true,
            "assets": [
              {"name": "xPlay-0.2.14.apk", "browser_download_url": "https://github.com/shihabal3amri/xPlay/releases/download/v0.2.14/xPlay-0.2.14.apk"},
              {"name": "SHA256SUMS.txt", "browser_download_url": "https://github.com/shihabal3amri/xPlay/releases/download/v0.2.14/SHA256SUMS.txt"}
            ]
          }
        ]
    """.trimIndent()

    @Test
    fun skipsDraftsAndReturnsTheFirstPublishedRelease() {
        val release = UpdateCatalog.parse(releaseJson)
        assertEquals("v0.2.14", release?.tagName)
        assertEquals("xPlay-0.2.14.apk", release?.apkName)
        assertEquals(
            "https://github.com/shihabal3amri/xPlay/releases/download/v0.2.14/xPlay-0.2.14.apk",
            release?.apkUrl,
        )
        assertEquals(
            "https://github.com/shihabal3amri/xPlay/releases/download/v0.2.14/SHA256SUMS.txt",
            release?.checksumsUrl,
        )
    }

    @Test
    fun returnsNullWithoutAnApkAsset() {
        val json = """
            [
              {"tag_name": "v0.2.14", "draft": false, "assets": [
                {"name": "source.zip", "browser_download_url": "https://example.com/source.zip"}
              ]}
            ]
        """.trimIndent()
        assertNull(UpdateCatalog.parse(json))
    }

    @Test
    fun returnsNullWithoutChecksums() {
        val json = """
            [
              {"tag_name": "v0.2.14", "draft": false, "assets": [
                {"name": "xPlay-0.2.14.apk", "browser_download_url": "https://example.com/xPlay-0.2.14.apk"}
              ]}
            ]
        """.trimIndent()
        assertNull(UpdateCatalog.parse(json))
    }

    @Test
    fun returnsNullForAnEmptyReleaseList() {
        assertNull(UpdateCatalog.parse("[]"))
    }
}
