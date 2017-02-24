package com.xogrp.john.ziqtoollibrary.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.xogrp.john.ziqtoollibrary.R;
import com.xogrp.planner.R;
import com.xogrp.planner.base.BaseFragment;
import com.xogrp.planner.base.BaseManagerFragment;
import com.xogrp.planner.base.XOAbstractFragment;
import com.xogrp.planner.event.PlannerEvent;
import com.xogrp.planner.fragment.AbstractPlannerFragment;
import com.xogrp.planner.fragment.VendorChecklistFragment;
import com.xogrp.planner.listener.Drawer;
import com.xogrp.planner.listener.FragmentNavigator;
import com.xogrp.planner.manager.ABTestingManager;
import com.xogrp.planner.mvp.ui.home.DashboardFragment;
import com.xogrp.planner.mvp.ui.home.DashboardPageFragment;
import com.xogrp.planner.mvp.ui.vendorProfile.category.VendorCategoryFragment;
import com.xogrp.planner.mvp.ui.vendorProfile.vendorlist.AbstractVenueSortingFragment;
import com.xogrp.planner.repository.CommonVarsRepository;
import com.xogrp.planner.repository.VendorEventRepository;
import com.xogrp.planner.repository.VenueSortingEventRepository;
import com.xogrp.planner.widget.FragmentTabHost;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by albert on 2016/7/9.
 */

public class FragmentTabHostDelegate {

    private static final String TAG = "FragmentTabHostDelegate";
    private static final boolean DEBUG = false;
    private OnBottomTabListener mOnBottomTabListener;
    private FragmentTabHost mTabHost;
    private TabWidget mTabWidget;
    private View mViewRealContent;
    private String mCurrentTag = TAG_HOME;
    private FragmentManager mFragmentManager;

    private static final String EVENT_SELECTION_DASHBOARD = "dashboard";
    private static final String EVENT_SELECTION_CHECKLIST = "checklist";
    private static final String EVENT_SELECTION_VENDORS = "vendors";
    private static final String TAG_HOME = TabManagerFragment.HomeManagerFragment.TAG;
    private static final String TAG_CHECKLIST = TabManagerFragment.ChecklistManagerFragment.TAG;
    private static final String TAG_VENDOR = TabManagerFragment.VendorsManagerFragment.TAG;

    public static final BottomTabItem TAB_HOME = new BottomTabItem(TAG_HOME, EVENT_SELECTION_DASHBOARD);
    public static final BottomTabItem TAB_CHECKLIST = new BottomTabItem(TAG_CHECKLIST, EVENT_SELECTION_CHECKLIST);
    public static final BottomTabItem TAB_VENDORS = new BottomTabItem(TAG_VENDOR, EVENT_SELECTION_VENDORS);

    private final BottomTabItem[] BOTTOM_TAB_ITEMS = new BottomTabItem[]{
            TAB_HOME.setTabInfo(new HomeTabInfo()),
            TAB_CHECKLIST.setTabInfo(new CheckListTabInfo()),
            TAB_VENDORS.setTabInfo(new VendorsTabInfo())
    };


    public FragmentTabHostDelegate(FragmentManager manager, final FragmentTabHost tabHost, @IdRes  int realContentId){
        mFragmentManager = manager;
        mTabHost = tabHost;
        mViewRealContent = tabHost.findViewById(R.id.fl_content);
        final Context context = tabHost.getContext();
        tabHost.setup(context, manager, realContentId);

        TabInfo tabInfo;
        for (BottomTabItem bottomTabItem: BOTTOM_TAB_ITEMS) {
            tabInfo = bottomTabItem.mTabInfo;
            tabHost.addTab(tabHost.newTabSpec(tabInfo.mTag).setIndicator(tabInfo.createTabView(context, tabHost)),
                    tabInfo.mFragment, tabInfo.mBundle);
        }
        mTabWidget = tabHost.getTabWidget();
        ViewCompat.setElevation(mTabWidget, context.getResources().getDimensionPixelSize(R.dimen.d_bottom_tab_elevation));
        for(int i = 0 ; i < mTabWidget.getChildCount() ; i++){
            View view = mTabWidget.getChildAt(i);
            view.setOnClickListener(BOTTOM_TAB_ITEMS[i].mTabInfo);
        }
        tabHost.setCurrentTab(0);
    }

    public void setOnBottomTabListener(OnBottomTabListener onBottomTabListener) {
        this.mOnBottomTabListener = onBottomTabListener;
    }

    public void setDrawer(Drawer drawer){
        mDrawer = drawer;
    }

    public void setFragmentNavigator(FragmentNavigator navigator) {
        mNavigator =  navigator;
    }

    void setCurrentTabState() {
        View currentTab = mTabHost.getCurrentTabView();
        if(currentTab != null){
            currentTab.setSelected(true);
        }
    }

    public void setCurrentTab(FragmentTabHostDelegate.BottomTabItem bottomTabItem) {
        checkNeedPopBackMenuFragment();
        mTabHost.setCurrentTabByTag(bottomTabItem.mTag);
        mCurrentTag = bottomTabItem.mTag;
    }

    public void setCurrentTab(){
        checkNeedPopBackMenuFragment();
        mCurrentTag = mTabHost.getCurrentTabTag();
        mTabHost.setCurrentTabByTag(mCurrentTag);
    }

    void clearTabState() {
        TabWidget tabWidget = mTabHost.getTabWidget();
        for(int i = 0 ; i < tabWidget.getChildCount() ; i++){
            View view = tabWidget.getChildAt(i);
            view.setSelected(false);
        }
        BaseManagerFragment managerFragment = getManagerFragment(getCurrentTab());
        if(managerFragment != null) {
            AbstractPlannerFragment current = (AbstractPlannerFragment) managerFragment.getCurrentFragment();
            if (current != null) {
                current.onBottomTabFragmentPause();
            }
        }
    }

    void showTab() {
        setTabVisibility(View.VISIBLE);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mViewRealContent.getLayoutParams();
        params.bottomMargin = mTabWidget.getResources().getDimensionPixelSize(R.dimen.d_bottom_tab_h);
        mViewRealContent.setLayoutParams(params);
    }

    private void setTabVisibility(int visibility) {
        TabWidget tabWidget = mTabHost.getTabWidget();
        if(tabWidget != null){
            tabWidget.setVisibility(visibility);
            tabWidget.setTranslationY(0);
        }
    }

    void hideTab() {
        setTabVisibility(View.GONE);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mViewRealContent.getLayoutParams();
        params.bottomMargin = 0;
        mViewRealContent.setLayoutParams(params);
    }

    private void checkNeedPopBackMenuFragment() {
        if(mDrawer.hasItemChecked()){
            mDrawer.clearItemCheckedState();
            setCurrentTabState();
        }
    }

    BottomTabItem getCurrentTab() {
        for (BottomTabItem bottomTabItem: BOTTOM_TAB_ITEMS) {
            if(bottomTabItem.mTag.equals(mCurrentTag)){
                return bottomTabItem;
            }
        }
        return null;
    }

    void reset() {
        AbstractPlannerFragment current = mNavigator.getCurrentFragment();
        if(current != null){
            Fragment parentFragment = current.getParentFragment();
            if(parentFragment instanceof BaseManagerFragment){
                BaseManagerFragment currentManager = (BaseManagerFragment) parentFragment;
                currentManager.reset();
                mNavigator.popBackStackImmediate(currentManager.getInitFragmentTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
    }

    void clearTabFragmentExcludeInitFragment() {
        if(mNavigator != null){
            mNavigator.popBackStackImmediate(getCurrentTab().mTabInfo.mInitFragmentTag, 0);
        }
    }

    View getBottomTabView() {
        return mTabWidget;
    }

    boolean isShowing() {
        TabWidget tabWidget = mTabHost.getTabWidget();
        return tabWidget != null && tabWidget.getVisibility() == View.VISIBLE && tabWidget.getTranslationY() == 0;
    }

    View getContentView() {
        return mViewRealContent;
    }

    public BaseManagerFragment getManagerFragment(BottomTabItem tabItem) {
        return (BaseManagerFragment) mFragmentManager.findFragmentByTag(tabItem.mTag);
    }

    public int getVenueSortingViewedPos(){
        try {
            BaseManagerFragment managerFragment = getManagerFragment(TAB_VENDORS);
            XOAbstractFragment current = managerFragment.getCurrentFragment();
            if(current instanceof AbstractVenueSortingFragment){
                return ((AbstractVenueSortingFragment)current).getCurrentTabViewedPos();
            }
        }catch (Exception e){
        }
        return 0;
    }

    public interface TabHostDelegateListener{
        int getVenueSortingViewedPos();
        BaseManagerFragment getManagerFragment(BottomTabItem bottomTabItem);
        void setCurrentTab(FragmentTabHostDelegate.BottomTabItem bottomTabItem);
        void setCurrentTab();
    }

    View getTabHost() {
        return mTabHost;
    }

    public interface OnBottomTabListener{
        boolean onTabSelected(BottomTabItem bottomTabItem);
    }

    private abstract class TabInfo implements View.OnClickListener{
        private Class<? extends BaseManagerFragment> mFragment;
        private Bundle mBundle;
        private String mTag;
        private @DrawableRes int mDrawableResId;
        private String mInitFragmentTag;

        private TabInfo(Class<? extends BaseManagerFragment> fragment, String tag, String initFragmentTag, int drawableResId) {
            this(fragment, null, tag, initFragmentTag, drawableResId);
        }

        private TabInfo(Class<? extends BaseManagerFragment> fragment, Bundle bundle, String tag, String initFragmentTag, int drawableResId) {
            this.mFragment = fragment;
            this.mBundle = bundle;
            this.mTag = tag;
            this.mInitFragmentTag = initFragmentTag;
            this.mDrawableResId = drawableResId;
        }

        private View createTabView(Context context, TabHost tabHost){
            View view = LayoutInflater.from(context).inflate(R.layout.tab_view, tabHost.getTabWidget(), false);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                view.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_selectable_item));
            }
            ImageView ivTab = (ImageView)view.findViewById(R.id.iv_tab);
            Drawable drawable = ContextCompat.getDrawable(context, mDrawableResId);
            ivTab.setImageDrawable(drawable);
            TextView tvTabTitle = (TextView) view.findViewById(R.id.tv_tab_title);
            tvTabTitle.setText(mTag);
            return view;
        }

        @Override
        public final void onClick(View view) {
            if(DEBUG) Log.d(TAG, "onClick: " + mTag);
            checkNeedPopBackMenuFragment();
            if(!dispatchOnBottomTabListener()){
                onClickTab(view);
            }
        }

        protected void onClickTab(View view){
            VenueSortingEventRepository venueSortingEventRepository = VenueSortingEventRepository.getInstance();
            VendorEventRepository vendorEventRepository = VendorEventRepository.getInstance();
            if (venueSortingEventRepository.isVenueSortingPage()) {
                PlannerEvent.getVenueSortingViewedEvent(venueSortingEventRepository.getSearchLocation(),
                        VenueSortingEventRepository.EVENT_TRACK_CATEGORY,
                        venueSortingEventRepository.isAlreadyFilter(),
                        getVenueSortingViewedPos(),
                        PlannerEvent.EVENT_PROP_VENDOR_CATEGORY_VIEWED_WITH_LEFT_FROM_BOTTOM_NAV,
                        venueSortingEventRepository.getSortType(),
                        CommonVarsRepository.getInstance().getLastMarketCode()).track();
                venueSortingEventRepository.setVenueSortingPage(false);
            } else if (vendorEventRepository.isVendorPage()) {
                vendorEventRepository.setClickBottomTab(true);
            }

            if(mCurrentTag.equalsIgnoreCase(mTag)){
                if (mCurrentTag.equalsIgnoreCase(TAG_VENDOR)){
                    vendorEventRepository.setClickVendorTabAgain(true);
                }
                dispatchOnClickTabAgain();
                onClickTabAgain();
            } else {
                dispathOnClickOtherBottomTab();
            }
            mTabHost.setCurrentTabByTag(mTag);
            mCurrentTag = mTag;
            clearFragmentAnimation();
            if(mDrawer != null){
                mDrawer.popBackCurrentDrawerManagerFragment();
            }
            dispatchOnClickBottomTab();
        }

        private boolean dispatchOnBottomTabListener() {
            boolean consumed = false;
            if(mOnBottomTabListener != null) {
                switch (mTag) {
                    case TabManagerFragment.HomeManagerFragment.TAG:
                        consumed = mOnBottomTabListener.onTabSelected(TAB_HOME);
                        break;
                    case TabManagerFragment.ChecklistManagerFragment.TAG:
                        consumed = mOnBottomTabListener.onTabSelected(TAB_CHECKLIST);
                        break;
                    case TabManagerFragment.VendorsManagerFragment.TAG:
                        consumed = mOnBottomTabListener.onTabSelected(TAB_VENDORS);
                        break;
                }
            }
            return consumed;
        }

        protected void onClickTabAgain() {
        }

        /**
         * fixed strange animation when change bottom tab
         */
        private void clearFragmentAnimation() {
            if(mNavigator != null){
                List<Fragment> fragments = mNavigator.getFragments();
                Fragment temp;
                Field tempFiled;
                for (int i = 0, len = fragments == null? 0 : fragments.size(); i < len; i++){
                    temp = fragments.get(i);
                    if(temp != null){
                        try {
                            Class<?> clazz = temp.getClass().getSuperclass();
                            while (clazz != Fragment.class){
                                clazz = clazz.getSuperclass();
                            }
                            tempFiled = clazz.getDeclaredField("mNextAnim");
                            tempFiled.setAccessible(true);
                            tempFiled.setInt(temp, 0);
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }

    private void dispatchOnClickTabAgain() {
        try {
            BottomTabItem currentTab = getCurrentTab();
            AbstractPlannerFragment current = (AbstractPlannerFragment) getManagerFragment(currentTab).getCurrentFragment();
            current.onTapBottomTabAgain(currentTab);
        }catch (Exception e){
        }
    }

    private void dispatchOnClickBottomTab() {
        try {
            BottomTabItem currentTab = getCurrentTab();
            AbstractPlannerFragment current = (AbstractPlannerFragment) getManagerFragment(currentTab).getCurrentFragment();
            current.onClickBottomTab(currentTab);
            current.onBottomTabFragmentResume();
            current.checkIsRestoreBottomTabStatusOnBackPressed();
        }catch (Exception e){
        }
    }

    private void dispathOnClickOtherBottomTab(){
        try {
            BottomTabItem currentTab = getCurrentTab();
            AbstractPlannerFragment current = (AbstractPlannerFragment) getManagerFragment(currentTab).getCurrentFragment();
            current.onTapOtherBottomTab(currentTab);
        }catch (Exception ignored){
        }
    }

    private class HomeTabInfo extends TabInfo{
        private HomeTabInfo(){
            super(TabManagerFragment.HomeManagerFragment.class,
                    TabManagerFragment.HomeManagerFragment.TAG,
                    TabManagerFragment.HomeManagerFragment.INIT_FRAGMENT_TAG,
                    R.drawable.selector_tab_home);
        }

        @Override
        protected void onClickTabAgain() {
            super.onClickTabAgain();
            clearTabFragmentExcludeInitFragment();
        }
    }

    private class CheckListTabInfo extends TabInfo{
        private CheckListTabInfo(){
            super(TabManagerFragment.ChecklistManagerFragment.class,
                    TabManagerFragment.ChecklistManagerFragment.TAG,
                    TabManagerFragment.ChecklistManagerFragment.INIT_FRAGMENT_TAG,
                    R.drawable.selector_tab_check_list);
        }

        @Override
        protected void onClickTabAgain() {
            super.onClickTabAgain();
            clearTabFragmentExcludeInitFragment();
        }
    }

    private class VendorsTabInfo extends TabInfo{
        private VendorsTabInfo(){
            super(TabManagerFragment.VendorsManagerFragment.class,
                    TabManagerFragment.VendorsManagerFragment.TAG,
                    TabManagerFragment.VendorsManagerFragment.INIT_FRAGMENT_TAG,
                    R.drawable.selector_tab_vendor);
        }

        @Override
        protected void onClickTabAgain() {
            super.onClickTabAgain();
            clearTabFragmentExcludeInitFragment();
        }
    }

    /**
     * Created by albert on 2016/7/9.
     */

    private abstract static class TabManagerFragment extends BaseManagerFragment {

        @Override
        public boolean isPageCanGoBack() {
            boolean consumed = false;
            int backStackCount = getBackStackCount();
            BaseFragment currentFragment = getCurrentFragment();
            if(currentFragment != null && currentFragment.isPageCanGoBack() && backStackCount > 1){
                consumed = popBackStackImmediate();
            }
            return consumed;
        }

        public static class ChecklistManagerFragment extends TabManagerFragment {

            private static final String TAG = "Checklist";
            private static final String INIT_FRAGMENT_TAG = VendorChecklistFragment.CHECKLIST_FRAGMENT_TRANSACTION_TAG;

            @Override
            public String getTransactionTag() {
                return TAG;
            }

            @Override
            public String getInitFragmentTag() {
                return INIT_FRAGMENT_TAG;
            }

            @Override
            protected XOAbstractFragment getInitFragment() {
                return new VendorChecklistFragment();
            }
        }

        public static class HomeManagerFragment extends TabManagerFragment {
            private static final String TAG = "Home";
            private static final String INIT_FRAGMENT_TAG = DashboardFragment.DASHBOARD_FRAGMENT_TAG;

            @Override
            public String getTransactionTag() {
                return TAG;
            }

            @Override
            public String getInitFragmentTag() {
                return INIT_FRAGMENT_TAG;
            }

            @Override
            protected XOAbstractFragment getInitFragment() {
                return  !ABTestingManager.getInstance().isOldDashboard()? new DashboardPageFragment() : new DashboardFragment();
            }
        }

        public static class VendorsManagerFragment extends TabManagerFragment {

            private static final String TAG = "Vendors";
            private static final String INIT_FRAGMENT_TAG = VendorCategoryFragment.VENDOR_CATEGORY_FRAGMENT_TAG;

            @Override
            public String getTransactionTag() {
                return TAG;
            }

            @Override
            public String getInitFragmentTag() {
                return INIT_FRAGMENT_TAG;
            }

            @Override
            protected XOAbstractFragment getInitFragment() {
                return new VendorCategoryFragment();
            }
        }
    }

    public static class BottomTabItem{
        String mTag;
        public String mEventSelection;
        TabInfo mTabInfo;

        private BottomTabItem(String mTag, String mEventSelection){
            this.mEventSelection = mEventSelection;
            this.mTag = mTag;
        }

        private BottomTabItem setTabInfo(TabInfo tabInfo) {
            this.mTabInfo = tabInfo;
            return this;
        }
    }
}
