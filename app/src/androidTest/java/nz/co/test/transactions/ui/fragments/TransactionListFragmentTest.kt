package nz.co.test.transactions.ui.fragments

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import nz.co.test.transactions.R
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.util.launchFragmentInHiltContainer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.math.BigDecimal
import java.math.RoundingMode

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class TransactionListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun checkIfTransactionListIsDisplayed() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<TransactionListFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.transactionList)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun clickOnTransactionItem_navigateToTransactionDetailsFragment() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<TransactionListFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.transactionList)).perform(swipeUp(), click()).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>
            (0, click()))

        val bundle = Bundle()
        bundle.putParcelable("transactionDetails", Transaction(1, "2021-08-31T15:47:10", "Hackett, Stamm and Kuhn", BigDecimal("9379.55"), BigDecimal("0")))
        verify(navController).navigate(
            R.id.actionShowTransactionDetails, bundle
        )
    }
}