package com.safaricom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.safaricom.androidhackathon.R;

import java.util.ArrayList;
import java.util.List;
import com.safaricom.model.Week;

@SuppressWarnings({"all"})
public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.WeekViewHolder>
{

    private AppCompatActivity mActivity;
    private List<Week> weeks = new ArrayList<>();

    public WeekAdapter(AppCompatActivity mActivity, List<Week> weeks) {
        this.mActivity = mActivity;
        this.weeks.addAll(weeks);
    }


    @Override
    public WeekViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.week_item, null);
        return new WeekViewHolder(view);
    }

    /**
     *
     * Method resfreshData invalidated the adapter data
     * {@link WeekAdapter#resfreshData(List<Week>)}.
     *
     */
    public void resfreshData(List<Week> weeks){
        this.weeks.clear();
        this.weeks.addAll(weeks);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final WeekViewHolder holder, final int position) {
        final Week week =  getItem(position);
        holder.mTotalText.setText(week.getTotal()+"");
        holder.mDepositText.setText(week.getDeposit()+"");
        holder.mWeekText.setText(week.getWeek());

    }

    public Week getItem(int position) {
        return weeks.get(position);
    }

    @Override
    public int getItemCount() {
        return weeks.size();
    }


    @Override
    public long getItemId ( int position){
        return position;
    }

    /**
     *
     *  ViewHolder class declaration
      * {@link WeekAdapter#WeekViewHolder}.
     *
     */
    public static class WeekViewHolder extends RecyclerView.ViewHolder {
        private TextView mWeekText;
        private TextView mDepositText;
        private TextView mTotalText;

        public WeekViewHolder(View itemView)
        {
            super(itemView);

            mWeekText = itemView.findViewById(R.id.week);
            mDepositText =itemView.findViewById(R.id.deposit);
            mTotalText = itemView.findViewById(R.id.total);
        }
    }
}
