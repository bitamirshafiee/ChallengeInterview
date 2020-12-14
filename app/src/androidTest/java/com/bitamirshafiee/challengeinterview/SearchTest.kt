package com.bitamirshafiee.challengeinterview

import androidx.fragment.app.commit
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.bitamirshafiee.challengeinterview.common.Consts.baseUrlProvider
import com.bitamirshafiee.challengeinterview.questionlist.QuestionAdapter
import com.bitamirshafiee.challengeinterview.questionlist.SearchFragment
import com.bitamirshafiee.challengeinterview.rule.OkHttpIdlingResourceRule
import com.google.android.material.internal.ContextUtils.getActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class SearchTest {

    @get:Rule
    var rule = OkHttpIdlingResourceRule()

    private val mockWebServer = MockWebServer()
    private val portNumber = 8080
    private val responseBody = "{" +
            "  \\\"items\\\": [" +
            "    {" +
            "      \\\"tags\\\": [" +
            "        \\\"android\\\"," +
            "        \\\"google-api\\\"," +
            "        \\\"youtube-data-api\\\"" +
            "      ]," +
            "      \\\"owner\\\": {" +
            "        \\\"reputation\\\": 11," +
            "        \\\"user_id\\\": 14812670," +
            "        \\\"user_type\\\": \\\"registered\\\"," +
            "        \\\"profile_image\\\": \\\"https://lh3.googleusercontent.com/a-/AOh14GgGGDAuc2ktDOSeFHlRTVtGYCPH674Cys37nyR2Ng=s96-c=k-s128\\\"," +
            "        \\\"display_name\\\": \\\"M Demirel\\\"," +
            "        \\\"link\\\": \\\"https://stackoverflow.com/users/14812670/m-demirel\\\"" +
            "      }," +
            "      \\\"is_answered\\\": false," +
            "      \\\"view_count\\\": 28," +
            "      \\\"answer_count\\\": 0," +
            "      \\\"score\\\": 1," +
            "      \\\"last_activity_date\\\": 1607878263," +
            "      \\\"creation_date\\\": 1607774034," +
            "      \\\"last_edit_date\\\": 1607878263," +
            "      \\\"question_id\\\": 65264575," +
            "      \\\"content_license\\\": \\\"CC BY-SA 4.0\\\"," +
            "      \\\"link\\\": \\\"https://stackoverflow.com/questions/65264575/how-to-create-credential-for-my-android-apps\\\"," +
            "      \\\"title\\\": \\\"How to create Credential for my Android Apps\\\"" +
            "    }," +
            "    {" +
            "      \\\"tags\\\": [" +
            "        \\\"android\\\"," +
            "        \\\"unit-testing\\\"," +
            "        \\\"android-studio\\\"," +
            "        \\\"parameterized\\\"," +
            "        \\\"parameterized-unit-test\\\"" +
            "      ]," +
            "      \\\"owner\\\": {" +
            "        \\\"reputation\\\": 25591," +
            "        \\\"user_id\\\": 3286489," +
            "        \\\"user_type\\\": \\\"registered\\\"," +
            "        \\\"accept_rate\\\": 60," +
            "        \\\"profile_image\\\": \\\"https://i.stack.imgur.com/yTcB3.png?s=128&g=1\\\"," +
            "        \\\"display_name\\\": \\\"Elye\\\"," +
            "        \\\"link\\\": \\\"https://stackoverflow.com/users/3286489/elye\\\"" +
            "      }," +
            "      \\\"is_answered\\\": true," +
            "      \\\"view_count\\\": 97793," +
            "      \\\"accepted_answer_id\\\": 57309718," +
            "      \\\"answer_count\\\": 16," +
            "      \\\"score\\\": 89," +
            "      \\\"last_activity_date\\\": 1607878190," +
            "      \\\"creation_date\\\": 1432708154," +
            "      \\\"question_id\\\": 30474767," +
            "      \\\"content_license\\\": \\\"CC BY-SA 3.0\\\"," +
            "      \\\"link\\\": \\\"https://stackoverflow.com/questions/30474767/no-tests-found-for-given-includes-error-when-running-parameterized-unit-test-in\\\"," +
            "      \\\"title\\\": \\\"No tests found for given includes Error, when running Parameterized Unit test in Android Studio\\\"" +
            "    }," +
            "    {" +
            "      \\\"tags\\\": [" +
            "        \\\"android-studio\\\"," +
            "        \\\"kotlin\\\"," +
            "        \\\"retrofit2\\\"," +
            "        \\\"rx-java2\\\"" +
            "      ]," +
            "      \\\"owner\\\": {" +
            "        \\\"reputation\\\": 39," +
            "        \\\"user_id\\\": 12452565," +
            "        \\\"user_type\\\": \\\"registered\\\"," +
            "        \\\"profile_image\\\": \\\"https://www.gravatar.com/avatar/9885de0ad095cb593b09f13f37ebe753?s=128&d=identicon&r=PG&f=1\\\"," +
            "        \\\"display_name\\\": \\\"ProcgerXacker\\\"," +
            "        \\\"link\\\": \\\"https://stackoverflow.com/users/12452565/procgerxacker\\\"" +
            "      }," +
            "      \\\"is_answered\\\": false," +
            "      \\\"view_count\\\": 6," +
            "      \\\"answer_count\\\": 0," +
            "      \\\"score\\\": 0," +
            "      \\\"last_activity_date\\\": 1607877889," +
            "      \\\"creation_date\\\": 1607877274," +
            "      \\\"last_edit_date\\\": 1607877889," +
            "      \\\"question_id\\\": 65278004," +
            "      \\\"content_license\\\": \\\"CC BY-SA 4.0\\\"," +
            "      \\\"link\\\": \\\"https://stackoverflow.com/questions/65278004/how-to-use-rxjava2-and-retrofit2-in-android-kotlin\\\"," +
            "      \\\"title\\\": \\\"How to use RxJava2 and Retrofit2 in Android Kotlin\\\"" +
            "    }," +
            "    {" +
            "      \\\"tags\\\": [" +
            "        \\\"android\\\"," +
            "        \\\"android-context\\\"" +
            "      ]," +
            "      \\\"owner\\\": {" +
            "        \\\"reputation\\\": 2433," +
            "        \\\"user_id\\\": 2484327," +
            "        \\\"user_type\\\": \\\"registered\\\"," +
            "        \\\"accept_rate\\\": 35," +
            "        \\\"profile_image\\\": \\\"https://i.stack.imgur.com/159Yf.jpg?s=128&g=1\\\"," +
            "        \\\"display_name\\\": \\\"Ritesh\\\"," +
            "        \\\"link\\\": \\\"https://stackoverflow.com/users/2484327/ritesh\\\"" +
            "      }," +
            "      \\\"is_answered\\\": false," +
            "      \\\"view_count\\\": 15," +
            "      \\\"answer_count\\\": 0," +
            "      \\\"score\\\": 0," +
            "      \\\"last_activity_date\\\": 1607877332," +
            "      \\\"creation_date\\\": 1607796246," +
            "      \\\"last_edit_date\\\": 1607877332," +
            "      \\\"question_id\\\": 65268162," +
            "      \\\"content_license\\\": \\\"CC BY-SA 4.0\\\"," +
            "      \\\"link\\\": \\\"https://stackoverflow.com/questions/65268162/existing-annotation-to-check-if-class-is-dependent-on-android-framework\\\"," +
            "      \\\"title\\\": \\\"Existing annotation to check if class is dependent on android framework?\\\"" +
            "    }," +
            "    {" +
            "      \\\"tags\\\": [" +
            "        \\\"android\\\"," +
            "        \\\"api\\\"" +
            "      ]," +
            "      \\\"owner\\\": {" +
            "        \\\"reputation\\\": 1," +
            "        \\\"user_id\\\": 14813548," +
            "        \\\"user_type\\\": \\\"registered\\\"," +
            "        \\\"profile_image\\\": \\\"https://lh4.googleusercontent.com/-0UUebUitrxQ/AAAAAAAAAAI/AAAAAAAAAAA/AMZuuclqbUHwGw7LAyG0v4BS7JxgBXJgbA/s96-c/photo.jpg?sz=128\\\"," +
            "        \\\"display_name\\\": \\\"user14813548\\\"," +
            "        \\\"link\\\": \\\"https://stackoverflow.com/users/14813548/user14813548\\\"" +
            "      }," +
            "      \\\"is_answered\\\": false," +
            "      \\\"view_count\\\": 8," +
            "      \\\"answer_count\\\": 0," +
            "      \\\"score\\\": -1," +
            "      \\\"last_activity_date\\\": 1607877067," +
            "      \\\"creation_date\\\": 1607877067," +
            "      \\\"question_id\\\": 65277965," +
            "      \\\"content_license\\\": \\\"CC BY-SA 4.0\\\"," +
            "      \\\"link\\\": \\\"https://stackoverflow.com/questions/65277965/looking-for-book-api-for-android-application\\\"," +
            "      \\\"title\\\": \\\"Looking for book API for android application\\\"" +
            "    }" +
            "  ]," +
            "  \\\"has_more\\\": true," +
            "  \\\"quota_max\\\": 10000," +
            "  \\\"quota_remaining\\\": 9990" +
            "}"

    @Before
    fun setup() {
//
//        IdlingRegistry.getInstance().register(
//            OkHttp3IdlingResource.create(
//                "okhttp",
//                getOkHttp()
//            ))

        mockWebServer.start(portNumber)
        baseUrlProvider = mockWebServer.url("/").toString()

        ActivityScenario.launch(MainActivity::class.java)
            .onActivity {
                it.supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(R.id.container, SearchFragment.newInstance())
                }
            }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSearch() {

        onView(withId(R.id.editTextSearch)).perform(typeText("android"))

        onView(withId(R.id.button)).perform(click())

        val mockResponse =
            MockResponse()
                .setBody(responseBody)
                .setResponseCode(200)
                .setBodyDelay(1, TimeUnit.SECONDS)
        mockWebServer.enqueue(mockResponse)



        onView(withId(R.id.recyclerView))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

    }
}