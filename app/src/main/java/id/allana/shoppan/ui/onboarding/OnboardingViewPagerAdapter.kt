package id.allana.shoppan.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    private val listFragmentOnboarding: MutableList<Fragment> = mutableListOf()

    fun addFragment(fragment: Fragment) {
        if (fragment.isAdded && listFragmentOnboarding.contains(fragment)) {
            return
        }
        listFragmentOnboarding.add(fragment)
    }
    override fun getItemCount(): Int {
        return listFragmentOnboarding.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragmentOnboarding[position]
    }
}