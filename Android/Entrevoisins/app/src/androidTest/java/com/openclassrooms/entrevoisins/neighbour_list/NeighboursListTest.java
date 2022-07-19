
package com.openclassrooms.entrevoisins.neighbour_list;

import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.SelectTabAtPosition;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();

        assertThat(mActivity, notNullValue());
    }


    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours), hasFocus()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * Check if the click on a neighbour launches details activity
     */

    @Test
    public void checkIfClickOnNeighbourLaunchDetailsActivity() {
        onView(allOf(withId(R.id.list_neighbours), hasFocus())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.details_activity_favourite_name_textview)).check(matches(isDisplayed()));

    }

    /**
     * check if the Textview displaying the neighbour name on details activity shows the right text
     */

    @Test
    public void checkIfDetailsNameTextViewShowsRightText() {
        String neighbourName = "Caroline";

        onView(allOf(withId(R.id.list_neighbours), hasFocus())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.details_activity_favourite_name_textview)).check(matches(isDisplayed()));

        onView(withId(R.id.details_activity_favourite_name_textview)).check(matches(withText(neighbourName)));

    }


    //test vérifiant que l’onglet Favoris n’affiche que les voisins marqués comme
    //favoris.

    /**
     * We test that the favorite neighbours tab displays only favorites neighbours
     */

    @Test
    public void checkIfFavoriteTabShowsFavoritesOnly() {
        //check that the neighbour list tab recyclerview contains 12 items
        onView(allOf(withId(R.id.list_neighbours), hasFocus())).check(withItemCount(ITEMS_COUNT));

        String neighbourName = "Caroline";

        //perform click on the neighbour named caroline
        onView(withText(neighbourName)).perform(click());


        //adding caroline to favorites neighbours
        onView(withId(R.id.details_activity_favourite_button)).perform(click());

        //turn back to neighbour list tab
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        //selecting the favorites tab
        onView(withId(R.id.tabs)).perform(new SelectTabAtPosition(1));


        //check that the favorite tab recyclerview contains only 1 item
        onView(allOf(withId(R.id.list_neighbours), hasFocus())).check(withItemCount(1));

        //check that the favorite tab recyclerview contains the neighbour added
        onView(allOf(withId(R.id.list_neighbours), hasFocus())).check(matches(hasDescendant(withText(neighbourName))));


        onView(withId(R.id.tabs)).perform(new SelectTabAtPosition(0));

    }

    /**
     * We test that when we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {



        onView(allOf(withId(R.id.list_neighbours), hasFocus())).check(withItemCount(ITEMS_COUNT));

        onView(allOf(withId(R.id.list_neighbours), hasFocus())).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        onView(allOf(withId(R.id.list_neighbours), hasFocus())).check(withItemCount(ITEMS_COUNT - 1));
    }


}