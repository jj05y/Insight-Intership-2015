package com.shimmerresearch.MultiShimmerRecordReview.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.shimmerresearch.MultiShimmerRecordReview.Adapters.ExpandableListAdapter;
import com.shimmerresearch.MultiShimmerRecordReview.Adapters.ViewPagerAdapterReviewByExLabel;
import com.shimmerresearch.MultiShimmerRecordReview.Constants.C;
import com.shimmerresearch.MultiShimmerRecordReview.Interfaces.Linker;
import com.shimmerresearch.MultiShimmerRecordReview.ListItems.ItemForReview;
import com.shimmerresearch.MultiShimmerRecordReview.MiscUtil.ZoomOutPageTransformer;
import com.shimmerresearch.MultiShimmerRecordReview.DatabaseClasses.DatabaseHandler;
import com.shimmerresearch.multishimmerrecordreview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ReviewByExLabelFragment extends Fragment {

    private Linker linker;
    private DatabaseHandler db;
    private ArrayList<ItemForReview> thingsForReview;
    private ViewPager pager;
    private ViewPagerAdapterReviewByExLabel pagerAdapter;
    private List<String> listDataHeaders;
    private HashMap<String, List<String>> listDataChildren;


    public ReviewByExLabelFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myInflatedView = inflater.inflate(R.layout.fragment_review_view_exlabel_pager, container, false);
        linker = (Linker) getActivity();
        db = linker.getDb();
        pager = (ViewPager) myInflatedView.findViewById(R.id.pager_nameEx);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());


        //////////////////////////
        // Data for EXP list view
        //////////////////////////

        listDataHeaders = new ArrayList<>();
        listDataChildren = new HashMap<>();
        for (String s: C.EXERCISES) {
            listDataHeaders.add(s);
        }

        for (String header : listDataHeaders) {
            ArrayList<String> children = new ArrayList<>();
            for (String label: C.LABELS) {
                children.add(label);
            }
            listDataChildren.put(header, children);
        }



        ExpandableListView expListView = (ExpandableListView) myInflatedView.findViewById(R.id.expandable_list_view);
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getActivity(), listDataHeaders, listDataChildren);
        expListView.setAdapter(expandableListAdapter);

/*        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPos, long l) {
                int exercise = groupPos;
                int label = -1;

                thingsForReview = db.getAllWithExerciseAndLabel(exercise, label);

                pagerAdapter = new ViewPagerAdapterReviewByExLabel(thingsForReview, getActivity(), db, C.REVIEW_BY_LABEL);
                pager.setAdapter(pagerAdapter);

                return false;
            }
        });*/

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPos, int childPos, long l) {

                int exercise = groupPos;
                int label = childPos;

                thingsForReview = db.getAllWithExerciseAndLabel(exercise, label);

                pagerAdapter = new ViewPagerAdapterReviewByExLabel(thingsForReview, getActivity(), db, C.REVIEW_BY_LABEL);
                pager.setAdapter(pagerAdapter);

                return true;
            }
        });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Log.d("surfaceTesting", "looking at page " + i);
                pagerAdapter.stopMediaPlayer();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        return myInflatedView;
    }




    @Override
    public void onPause() {
        super.onPause();
    }


}
