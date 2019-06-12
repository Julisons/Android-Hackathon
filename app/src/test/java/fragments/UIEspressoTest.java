package fragments;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.safaricom.androidhackathon.MainActivity;
import com.safaricom.androidhackathon.R;
import com.safaricom.androidhackathon.SplashScreen;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UIEspressoTest {

    public UIEspressoTest(){
        super();
        Robolectric.setupActivity(MainActivity.class);
    }

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void runUIEspressoTest() {

        //Espresso.onView(withId(R.id.saving_amount)).perform(typeText("34"));
        //Espresso.onView(withId(R.id.greet_button)).perform(click());
        Espresso.onView(withText("Withdraw Funds")).check(matches(isDisplayed()));
    }
}
