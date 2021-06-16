package dev.masterdeveloper.telecurso2000

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenario: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var mainActivity: MainActivity

    @Before
    fun setup() {

        activityScenario.scenario.onActivity {
            mainActivity = it
        }

    }

    @Test
    fun testTitle() {

        onView(withText("Telecurso 2000"))
            .check(matches(
                isDisplayed()
            ))

    }

    @Test
    fun testContadorIsZero() {

        onView(withId(R.id.tvContador))
            .check(matches(allOf(
                isDisplayed(), withText("0")
            )))

    }

    @Test
    fun testBtnIniciar() {

        onView(withId(R.id.btnIniciar))
            .check(matches(allOf(
                isDisplayed(), isClickable(), isEnabled(), withText("INICIAR")
            )))

    }

    @Test
    fun testBtnPausar() {

        onView(withId(R.id.btnPausar))
            .check(matches(allOf(
                isDisplayed(), isClickable(), not(isEnabled()), withText("PAUSAR")
            )))

    }

    @Test
    fun testBtnReiniciar() {

        onView(withId(R.id.btnResetar))
            .check(matches(allOf(
                isDisplayed(), isClickable(), not(isEnabled()), withText("REINICIAR")
            )))

    }

    @Test
    fun testStartContador() {

        onView(withId(R.id.tvContador))
            .check(matches(
                withText("0")
            ))

        onView(withId(R.id.btnIniciar))
            .perform(click())

        runBlocking { delay(500L) }

        onView(withId(R.id.tvContador))
            .check(matches(
                not(withText("0"))
            ))

        onView(withId(R.id.btnIniciar))
            .check(matches(
                not(isEnabled())
            ))

        onView(withId(R.id.btnPausar))
            .check(matches(
                isEnabled()
            ))

        onView(withId(R.id.btnResetar))
            .check(matches(
                not(isEnabled())
            ))

        assertFalse(mainActivity.viewModel.contador.value == 0)
        assertEquals(MainViewModel.Status.INICIADO, mainActivity.viewModel.status.value)

    }

    @Test
    fun testPausarContador() {

        onView(withId(R.id.btnIniciar))
            .perform(click())



    }

}