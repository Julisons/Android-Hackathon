package fragments;

import com.safaricom.androidhackathon.R;
import com.safaricom.util.Global;
import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;

public class WeekFragment {

    /**
     * Performs Unit Test On method isInteger return true if argument is integer
     * {@link WeekFragment#isInteger_CorrectIntegerString_ReturnsTrue()}.
     */
    @Test
    public void isInteger_CorrectIntegerString_ReturnsTrue() {
        assertThat(Global.isInteger("5")).isTrue();

        /**
        *  Uncomment for failure tests
        *  assertThat(Global.isInteger("f")).isTrue();
         *
         */
    }

}
