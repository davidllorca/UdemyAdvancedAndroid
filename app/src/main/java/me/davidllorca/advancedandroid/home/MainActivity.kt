package me.davidllorca.udemyadvancedandroid

import com.bluelinelabs.conductor.Controller
import me.davidllorca.advancedandroid.base.BaseActivity
import me.davidllorca.advancedandroid.trending.TrendingReposController

class MainActivity : BaseActivity() {

    override fun layoutRes() = R.layout.activity_main

    override fun initialScreen(): Controller {
        return TrendingReposController()
    }


}
