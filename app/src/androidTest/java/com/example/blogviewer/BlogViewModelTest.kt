package com.example.blogviewer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.blogviewer.io.api.ApiAdapter
import com.example.blogviewer.io.model.BlogModel
import com.example.blogviewer.io.model.UserModel
import com.example.blogviewer.io.viewmodel.BlogViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONArray
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class BlogViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var blogViewModel: BlogViewModel

    @Mock
    private lateinit var blogObserver: Observer<List<BlogModel>>

    @Mock
    private lateinit var userObserver: Observer<List<UserModel>>

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        blogViewModel = BlogViewModel()
        blogViewModel.getBlogList().observeForever(blogObserver)
        blogViewModel.getUserList().observeForever(userObserver)

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun readBlogSampleJsonFile() {
        val reader = MockResponseFileReader("blog_success_response.json")
        assertNotNull(reader.content)
    }

    @Test
    fun readUserSampleJsonFile() {
        val reader = MockResponseFileReader("user_success_response.json")
        assertNotNull(reader.content)
    }

    @Test
    fun checkBlogPostSuccess() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("blog_success_response.json").content)
        mockWebServer.enqueue(response)
        val mockResponse = response.getBody()?.readUtf8()
        val actualResponse =
            ApiAdapter.apiClient.getBlogList(/*coroutinesTestRule.testDispatcherProvider*/)
        assertEquals(
            mockResponse?.let { parseMockedJsonResponse(it) },
            actualResponse.body()?.get(1)?.title
        )
    }

    private fun parseMockedJsonResponse(mockResponse: String): String {
        val response = JSONArray(mockResponse)
        val jsonObj = response.optJSONObject(1)//"id": 2,"title": "qui est esse"
        return jsonObj.optString("title")
    }

    @After
    fun tearDown() {
        blogViewModel.getBlogList().removeObserver(blogObserver)
        blogViewModel.getUserList().removeObserver(userObserver)
        mockWebServer.shutdown()
    }
}