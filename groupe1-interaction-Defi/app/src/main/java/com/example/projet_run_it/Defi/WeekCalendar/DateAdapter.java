package com.example.projet_run_it.Defi.WeekCalendar;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projet_run_it.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Fichier Java créé par : Tianchen HAN
 * Groupe : TP5B1 Interaction
 * Intégrateur : [Nom de l'intégrateur]
 * Date de création : 2023-11-25
 * Description : Adapter pour les données hebdomadaires (7 jours) avec des dates sélectionnables.
 * Version : 3.0
 * Dernière modification : 2023-12-05
 */

public class DateAdapter extends BaseAdapter {
    private Context mContext;
    private List<DateBean> mDateList;
    private int mSelectedPosition = -1;

    private Date mMinDate;
    private Date mMaxDate;


    public DateAdapter(Context context, ArrayList<DateBean> dateList) {
        mContext = context;
        mDateList = dateList;
    }

    public void setDateList(List<DateBean> dateList) {
        mDateList = dateList;
    }

    public void setSelection(int position) {
        mSelectedPosition = position;
    }

    public void setMaxMonthAndDay(Date date) {
        mMaxDate = date;
    }

    public void setMinMonthAndDay(Date date) {
        mMinDate = date;
    }

    public DateBean getSelectedDateBean() {
        if (mSelectedPosition < 0 || mSelectedPosition > getCount() - 1) {
            return null;
        }
        return mDateList.get(mSelectedPosition);
    }

    @Override
    public int getCount() {
        if (mDateList == null) {
            return 0;
        }
        return mDateList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mDateList != null && mDateList.size() != 0) {
            return mDateList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.date_list_item_calendar, null);

            holder.containerLayout = convertView.findViewById(R.id.containerLayout);
            holder.bgLayout = convertView.findViewById(R.id.bgLayout);
            holder.dateTV = (TextView) convertView.findViewById(R.id.dateTV);
            //holder.monthTV = (TextView) convertView.findViewById(R.id.monthTV);
            holder.weekTV = (TextView) convertView.findViewById(R.id.weekTV);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        DateBean dateBean = mDateList.get(position);
        holder.dateTV.setText(String.valueOf(dateBean.getDay()));
        holder.weekTV.setText(getWeekStrForPosition(position));
        //holder.monthTV.setText(getCurrentMonth(position) + ".");


        holder.bgLayout.setBackgroundColor(Color.TRANSPARENT);
        holder.dateTV.setTextColor(Color.parseColor("#201D23"));
        holder.weekTV.setTextColor(Color.parseColor("#201D23"));


        if (dateBean.isSelectable()) {
            //可以被选的日期样式
            holder.dateTV.setAlpha(1f);
            holder.weekTV.setAlpha(0.3f);
            holder.containerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelection(position);
                    notifyDataSetChanged();
                }
            });
            if (mSelectedPosition == position) {
                holder.bgLayout.setBackgroundResource(R.color.bleu);
                holder.dateTV.setTextColor(Color.WHITE);
                holder.weekTV.setTextColor(Color.WHITE);
            }
        } else {
            holder.dateTV.setAlpha(0.1f);
            holder.weekTV.setAlpha(0.1f);
            holder.containerLayout.setOnClickListener(null);
        }


        return convertView;
    }


    private String getWeekStrForPosition(int position) {
        switch (position) {
            case 0:
                return "Dim";

            case 1:
                return "Lun";

            case 2:
                return "Mar";

            case 3:
                return "Mer";

            case 4:
                return "Jeu";

            case 5:
                return "Ven";

            case 6:
                return "Sam";

            default:
                return "";
        }
    }

    private class Holder {
        View containerLayout;
        View bgLayout;
        TextView weekTV;
        TextView dateTV;
        //TextView monthTV;
    }
}