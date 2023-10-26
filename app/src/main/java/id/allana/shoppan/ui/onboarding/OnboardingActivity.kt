package id.allana.shoppan.ui.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import id.allana.shoppan.R
import id.allana.shoppan.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private var _binding: ActivityOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
    }

    private fun initViewPager() {
        val onboardingFragmentAdapter = OnboardingViewPagerAdapter(supportFragmentManager, lifecycle)
        onboardingFragmentAdapter.addFragment(
            OnboardingHolderFragment(
                "",
                ""
            )
        )
        // LANJUTKAN PEMBUATAN SISA 2 SCREEN ONBOARDING, PASTIKAN 2 PARAMETER TITLE DAN DESCRIPTION TERISI

        binding.vpOnboarding.apply {
            adapter = onboardingFragmentAdapter
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    when (position) {
                        0 -> {
                            binding.apply {
                                indicatorFirst.setCardBackgroundColor(ContextCompat.getColor(context, R.color.blue_primary))
                                indicatorSecond.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                                indicatorThird.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                            }
                        }
                        1 -> {
                            TODO("LANJUTKAN")
                        }
                        2 -> {
                            TODO("LANJUTKAN")
                        }
                    }
                }
            })
        }

        binding.fabNextOnboarding.setOnClickListener {
            if (getNextIndex() != -1) {
                val nextIndex = getNextIndex()
                val maxPages = binding.vpOnboarding.adapter?.itemCount ?: 0

                if (nextIndex >= maxPages) {
                    TODO("INTENT KE AUTH ACTIVITY")
                } else {
                    binding.vpOnboarding.setCurrentItem(nextIndex, true)
                }
            }
        }
    }

    private fun getNextIndex(): Int {
        val maxPages = binding.vpOnboarding.adapter?.itemCount ?: 0
        val currentPage = binding.vpOnboarding.currentItem
        return currentPage.plus(1).coerceAtMost(maxPages - 1)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}