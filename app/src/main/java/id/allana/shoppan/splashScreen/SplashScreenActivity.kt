package id.allana.shoppan.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import id.allana.shoppan.databinding.ActivitySplashScreenBinding
import id.allana.shoppan.ui.onboarding.OnboardingActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imgShoppan = binding.ivShoppan

        val splashScreenAnimation = createFadeInAnimation()

        splashScreenAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                navigateToOnboarding()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        imgShoppan.startAnimation(splashScreenAnimation)
    }

    private fun createFadeInAnimation(): AlphaAnimation {
        val fadeInAnimation = AlphaAnimation(0f, 1f)
        fadeInAnimation.duration = 3000
        return fadeInAnimation
    }

    private fun navigateToOnboarding() {
        val moveToOnboarding = Intent(this@SplashScreenActivity, OnboardingActivity::class.java)
        startActivity(moveToOnboarding)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}
