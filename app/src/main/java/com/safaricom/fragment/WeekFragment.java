package com.safaricom.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.safaricom.androidhackathon.R;
import com.safaricom.util.Global;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.safaricom.adapter.WeekAdapter;
import it.gmariotti.recyclerview.adapter.SlideInLeftAnimatorAdapter;
import com.safaricom.model.Week;

import static com.safaricom.util.Global.NUMBER_OF_WEEKS;

@SuppressWarnings({"all"})
public class WeekFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = (AppCompatActivity) getActivity();
        weeks = new ArrayList<>();
    }
    /**
     * WeekAdapter declaration
     */
    private WeekAdapter mAdapter;

    /**
     * AppCompatActivity declaration
     */
    private AppCompatActivity mActivity;

    /**
     * weeks declaration
     *
     */
    private List<Week> weeks;


    /**
     * variable declaration
     *
     */
    private int CURRENT_WEEK =1;

    private long mAmount;
    private TextView mTotalSaved;
    private EditText mSavingAmount;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTotalSaved = view.findViewById(R.id.savings);
        mSavingAmount = view.findViewById(R.id.saving_amount);
        mAdapter = new WeekAdapter(mActivity,weeks);
        mRecyclerView =  view.findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(new SlideInLeftAnimatorAdapter(mAdapter, mRecyclerView));
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity,2));
        /**
         *
         * mSavingAmount Text Change responsiveness
         *
         */
        mSavingAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                /**
                 *
                 * Avoids NumberFormatException and NullPointerException
                 *
                 */

                int MAX = 50000000;

                Log.d("@@@", (Integer.parseInt(s.toString()) <= MAX)+"----");

                if(!TextUtils.isEmpty(s)){
                    if(Integer.parseInt(s.toString()) <= MAX) {
                        mAmount = Long.parseLong(s.toString());
                        calculateWeeksAmount(Long.parseLong(s.toString()));
                    }else{
                        mSavingAmount.setText("50000000");
                        Toast.makeText(mActivity, "Cant exceed limit 50000000", Toast.LENGTH_LONG).show();
                    }
                }else  calculateWeeksAmount(0l);

            }
        });
        /**
         *
         * DEFAULT SAVING AMOUNT
         *
         */
        mSavingAmount.setText(SAVING_AMOUNT+"");
    }

    private long SAVING_AMOUNT = 50;
    private long TOTAL_AMOUNT;
    private long DEPOSIT_AMOUNT = 0;
    private int PREVIOUS_WEEK = 0;


    /**
     * Method calculates the final tatal amount and updates the UI
     * {@link WeekFragment#calculateWeeksAmount(int amount)}.
     */
    private void calculateWeeksAmount(long amount){
        CURRENT_WEEK = 1;
        SAVING_AMOUNT = amount;
        TOTAL_AMOUNT = amount;
        DEPOSIT_AMOUNT = amount;
        weeks.clear();
        /**
         *
         * intialize the starting week
         *
         */
        Week week = new Week();
        week.setDeposit(DEPOSIT_AMOUNT);
        week.setTotal(TOTAL_AMOUNT);
        week.setWeek(CURRENT_WEEK+"");
        weeks.add(week);

        /**
         *
         * start at week two
         *
         */
        for(int i=1; i<NUMBER_OF_WEEKS; i++){
            CURRENT_WEEK =CURRENT_WEEK+1;
             PREVIOUS_WEEK = 0;
            if (i == 1)
                PREVIOUS_WEEK = 0;
            else
                PREVIOUS_WEEK = i - 1;

            if(weeks.size() > 0) {
                DEPOSIT_AMOUNT = SAVING_AMOUNT + weeks.get(PREVIOUS_WEEK).getDeposit();
                TOTAL_AMOUNT = SAVING_AMOUNT + weeks.get(PREVIOUS_WEEK).getTotal() + weeks.get(PREVIOUS_WEEK).getDeposit();
            }
            /**
             *
             * add objects to weeks list
             *
             */
            week = new Week();
            week.setDeposit(DEPOSIT_AMOUNT);
            week.setTotal(TOTAL_AMOUNT);
            week.setWeek(CURRENT_WEEK+"");
            weeks.add(week);
        }


        mTotalSaved.setText("KES "+TOTAL_AMOUNT+"");
        mAdapter.resfreshData(weeks);
    }
}
