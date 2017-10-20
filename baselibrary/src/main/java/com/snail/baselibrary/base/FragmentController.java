package com.snail.baselibrary.base;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

import com.snail.baselibrary.R;

/**
 * FragmentController
 *
 * @author changsunhaipeng
 */
public class FragmentController {


    public static void addFragment(BaseActivity activity, BaseFragment fragment) {
        addFragment(activity.getSupportFragmentManager(), fragment, fragment.TAG, android.R.id.content, -1, -1);
    }

    public static void addFragment(BaseFragment currentFragment, BaseFragment fragment) {
        addFragment(currentFragment.getFragmentManager(), fragment, fragment.TAG, android.R.id.content, -1, -1);
    }

    public static void addFragment(FragmentManager fragmentManager, BaseFragment fragment, String tag) {
        addFragment(fragmentManager, fragment, tag, android.R.id.content, -1, -1);
    }

    public static void addFragment(FragmentManager fragmentManager, BaseFragment fragment, String tag, int aniIN, int
            aniOut) {
        addFragment(fragmentManager, fragment, tag, android.R.id.content, aniIN, aniOut);
    }

    public static void addFragment(FragmentManager fragmentManager, BaseFragment fragment, String tag, int postion,
                                   int aniIN, int aniOut) {
        if (fragmentManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (fragmentManager.isDestroyed()) {
                    return;
                }
            }
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (aniIN > 0 || aniOut > 0) {
                ft.setCustomAnimations(aniIN, 0, 0, aniOut);
            } else if (aniIN == -1 || aniOut == -1) {
                ft.setCustomAnimations(R.anim.anim_fragment_in, R.anim.anim_fragment_out, R.anim
                        .anim_fragment_close_in, R.anim.anim_fragment_close_out);
            }

            Fragment lastFragment = fragmentManager.findFragmentById(postion);
            if (lastFragment != null) {

                ft.hide(lastFragment);
            }

            ft.add(postion, fragment, tag);

            ft.addToBackStack(tag);
            ft.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void initFragment(BaseActivity baseActivity, Fragment fragment, String tag) {
        initFragment(baseActivity, fragment, tag, android.R.id.content);
    }

    public static void initFragment(BaseActivity baseActivity, Fragment fragment, String tag, int postion) {
        if (baseActivity != null && !baseActivity.isFinishing() && !baseActivity.isDestroyed()) {
            FragmentManager fragmentManager = baseActivity.getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(postion, fragment, tag);
            ft.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment targetFragment, String tag) {
        replaceFragment(fragmentManager, targetFragment, tag, android.R.id.content);
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment targetFragment, String tag, int
            postion) {
        if (fragmentManager != null && !fragmentManager.isDestroyed()) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(postion, targetFragment, tag);
            transaction.commitAllowingStateLoss();
        }
    }

    public static void removeFragment(FragmentManager fragmentManager, Fragment targetFragment) {
        // TODOAuto-generated method stub
        if (fragmentManager != null && !fragmentManager.isDestroyed()) {
            String tag = targetFragment.getTag();

            try {
                if (fragmentManager.findFragmentByTag(tag) != null) {
                    fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            } catch (Exception e) {
                Log.e("FragmentController", e.getMessage());
                try {
                    FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();
                    localFragmentTransaction.remove(targetFragment);
                    localFragmentTransaction.commitAllowingStateLoss();
                    fragmentManager.executePendingTransactions();
                } catch (Exception e2) {
                    Log.e("FragmentController", e.getMessage());
                }
            }

        }
    }

    public static void removeFragment(FragmentManager fragmentManager, String tag) {
        if (fragmentManager != null && !TextUtils.isEmpty(tag) && !fragmentManager.isDestroyed()) {
            Fragment targment = fragmentManager.findFragmentByTag(tag);
            if (targment != null) {
                try {
                    fragmentManager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } catch (Exception e) {
                    Log.e("FragmentController", e.getMessage());
                }
                FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();

                localFragmentTransaction.remove(targment);
                localFragmentTransaction.commitAllowingStateLoss();
                try {
                    fragmentManager.executePendingTransactions();
                } catch (Exception e) {
                    Log.e("FragmentController", e.getMessage());
                }
            }
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null && !fragment.isDetached()) {
                FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();
                localFragmentTransaction.remove(fragment);
                localFragmentTransaction.commitAllowingStateLoss();
                try {
                    fragmentManager.executePendingTransactions();
                } catch (Exception e) {
                    Log.e("FragmentController", e.getMessage());
                }
            }
        }
    }

    public static void show(FragmentManager fm, String tag) {
        try {
            if (!TextUtils.isEmpty(tag) && fm != null && !fm.isDestroyed()) {
                FragmentTransaction ft = fm.beginTransaction();
                for (Fragment fragment : fm.getFragments()) {
                    if (fragment != null) {
                        ft.hide(fragment);
                    }
                }
                Fragment target = fm.findFragmentByTag(tag);
                if (target != null) {
                    ft.show(target);
                }
                ft.commitAllowingStateLoss();
                fm.executePendingTransactions();
            }
        } catch (Exception e) {
            Log.e("FragmentController", e.getMessage());
        }
    }
}
