package id.allana.shoppan.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import id.allana.shoppan.R
import id.allana.shoppan.databinding.ActivityOnboardingBinding
import id.allana.shoppan.ui.auth.AuthActivity

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
                "Selamat Datang Di Shoppan!",
                "Shoppan adalah wadah bagi para pelajar yang ingin mengembangkan usaha nya"
            )
        )
        onboardingFragmentAdapter.addFragment(
            OnboardingHolderFragment(
                "Produk Unggulan",
                "Rekomendasi produk unggulan yang ditawarkan  dari para pelajar yang memiliki usaha dapat kamu temukan disini"
            )
        )
        onboardingFragmentAdapter.addFragment(
            OnboardingHolderFragment(
                "Kemudahan Transaksi",
                "Shoppan memberikan akses langsung ke Whatsapp saat ingin bertransaksi, sehingga transaksi jual beli lebih mudah dan fleksibel"
            )
        )

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
                            binding.apply {
                                indicatorFirst.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                                indicatorSecond.setCardBackgroundColor(ContextCompat.getColor(context, R.color.blue_primary))
                                indicatorThird.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                            }
                        }
                        2 -> {
                            binding.apply {
                                indicatorFirst.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                                indicatorSecond.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                                indicatorThird.setCardBackgroundColor(ContextCompat.getColor(context, R.color.blue_primary))
                            }
                        }
                    }
                }
            })
        }

        binding.fabNextOnboarding.setOnClickListener {
            if (getNextIndex() != -1) {
                val nextIndex = getNextIndex()
                val maxPages = binding.vpOnboarding.adapter?.itemCount ?: 0

                if (nextIndex == maxPages) {
                    Log.d("INTENT", "CLICKED!")
                    val navigate = Intent(this@OnboardingActivity, AuthActivity::class.java)
                    startActivity(navigate)
                    finish()
                } else {
                    binding.vpOnboarding.setCurrentItem(nextIndex, true)
                }
            }
        }
    }

    private fun getNextIndex(): Int {
        val currentPage = binding.vpOnboarding.currentItem
        return currentPage.plus(1)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}