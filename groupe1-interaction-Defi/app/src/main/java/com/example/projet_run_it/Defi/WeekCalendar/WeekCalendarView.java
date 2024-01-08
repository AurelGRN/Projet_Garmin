package com.example.projet_run_it.Defi.WeekCalendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * Fichier Java créé par : Tianchen HAN
 * Groupe : TP5B1 Interaction
 * Intégrateur : [Nom de l'intégrateur]
 * Date de création : 2023-11-25
 * Description : Cette classe représente une vue de calendrier hebdomadaire avec la gestion des semaines, la navigation par balayage et la sélection de dates.
 * Version : 3.0
 * Dernière modification : 2023-12-05
 */

public class WeekCalendarView extends ViewFlipper {
    private Calendar calendar;
    private Calendar minDate;
    private Calendar maxDate;

    private DateAdapter dateAdapter;
    private float firstTouchX;

    public WeekCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeCalendar();
        setupDateAdapter();
        addView(createWeekGridView());
    }

    private void initializeCalendar() {
        calendar = Calendar.getInstance();
        resetTime(calendar);
        dateAdapter = new DateAdapter(getContext(), (ArrayList<DateBean>) getWeekDays(calendar));
        dateAdapter.setSelection(calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    private void resetTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    private void setupDateAdapter() {
        dateAdapter = new DateAdapter(getContext(), (ArrayList<DateBean>) getWeekDays(calendar));
        dateAdapter.setSelection(calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    private GridView createWeekGridView() {
        GridView gridView = new GridView(getContext());
        gridView.setNumColumns(7);
        gridView.setGravity(Gravity.CENTER_VERTICAL);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setVerticalSpacing(1);
        gridView.setHorizontalSpacing(1);
        gridView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        gridView.setAdapter(dateAdapter);
        return gridView;
    }

    private List<DateBean> getWeekDays(Calendar cal) {
        List<DateBean> weekDays = new ArrayList<>();
        Calendar tempCalendar = (Calendar) cal.clone();
        int dayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK);
        tempCalendar.add(Calendar.DAY_OF_YEAR, -dayOfWeek);

        for (int i = 0; i < 7; i++) {
            tempCalendar.add(Calendar.DAY_OF_YEAR, 1);
            DateBean dateBean = new DateBean();
            dateBean.setDay(tempCalendar.get(Calendar.DAY_OF_MONTH));
            dateBean.setMonth(tempCalendar.get(Calendar.MONTH) + 1);
            dateBean.setYear(tempCalendar.get(Calendar.YEAR));
            dateBean.setDate(tempCalendar.getTime());
            dateBean.setSelectable(isDateWithinRange(tempCalendar));
            weekDays.add(dateBean);
        }
        return weekDays;
    }

    private boolean isDateWithinRange(Calendar cal) {
        if ((maxDate != null) && cal.after(maxDate)) {
            return false;
        }
        return (minDate == null) || !cal.before(minDate);
    }

    public void setRange(Calendar min, Calendar max) {
        if (min != null && max != null && min.after(max)) {
            throw new IllegalArgumentException("Invalid date range.");
        }
        setMinDate(min);
        setMaxDate(max);
        updateDateAdapter();
    }

    private void setMinDate(Calendar min) {
        if (min != null) {
            minDate = min;
            resetTime(minDate);
        }
    }

    private void setMaxDate(Calendar max) {
        if (max != null) {
            maxDate = max;
            resetTime(maxDate);
        }
    }

    private void updateDateAdapter() {
        dateAdapter.setDateList(getWeekDays(calendar));
        dateAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            firstTouchX = ev.getX();
        }
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (Math.abs(ev.getX() - firstTouchX) > 15) {
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getX() - firstTouchX < -60) { // Swipe left
                navigateWeeks(true);
            } else if (event.getX() - firstTouchX > 60) { // Swipe right
                navigateWeeks(false);
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void navigateWeeks(boolean next) {
        Calendar tempCalendar = (Calendar) calendar.clone();
        int days = next ? 7 : -7;
        tempCalendar.add(Calendar.DATE, days);
        List<DateBean> list = getWeekDays(tempCalendar);

        if (!isWeekNavigable(list, next)) {
            return;
        }

        calendar.add(Calendar.DATE, days);
        dateAdapter.setDateList(list);
        resetSelectionIfNeeded();

        addView(createWeekGridView());
        setAnimation(next);
        showNext();
        removeViewAt(0);
    }

    private boolean isWeekNavigable(List<DateBean> list, boolean next) {
        return next ? list.get(0).isSelectable() : list.get(list.size() - 1).isSelectable();
    }

    private void resetSelectionIfNeeded() {
        if (!dateAdapter.getSelectedDateBean().isSelectable()) {
            dateAdapter.setSelection(0);
        }
    }

    private void setAnimation(boolean next) {
        setInAnimation(getTranslateAnim(next, true));
        setOutAnimation(getTranslateAnim(next, false));
    }

    private TranslateAnimation getTranslateAnim(boolean next, boolean isShowIn) {
        int start, end;
        if (next) {
            start = isShowIn ? 1 : 0;
            end = isShowIn ? 0 : -1;
        } else {
            start = isShowIn ? -1 : 0;
            end = isShowIn ? 0 : 1;
        }
        TranslateAnimation anim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, start, Animation.RELATIVE_TO_SELF, end,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        anim.setDuration(400);
        return anim;
    }
}
