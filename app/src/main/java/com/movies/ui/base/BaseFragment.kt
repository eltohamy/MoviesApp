package com.movies.ui.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.movies.AppControl
import com.movies.data.DataManager
import com.movies.di.component.ActivityComponent
import javax.inject.Inject

/**
 * Created by Tohamy on 29/09/2019
 */
abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var dataManager: DataManager
    var baseActivity: BaseActivity? = null

    private val activityComponent: ActivityComponent?
        get() = if (baseActivity != null) {
            baseActivity!!.activityComponent()
        } else null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppControl[activity!!].component!!.inject(this)
        if (activityComponent != null)
            activityComponent!!.inject(this)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.baseActivity = activity
        }
    }


    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

}
