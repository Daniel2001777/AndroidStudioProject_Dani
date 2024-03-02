package com.example.projekttojas.second

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap

class SecondViewModel(name: String) : ViewModel() {
    var number = 0
    var myName: String = name

    fun name(): String {
        return myName
    }

    val currentNumber: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    private lateinit var timer: CountDownTimer
    val _seconds = MutableLiveData<Int>()
    val currentTimeString = _seconds.map {
        it?.toLong()?.let { it1 -> DateUtils.formatElapsedTime(it1) }
    }

    fun seconds(): LiveData<String?> {
        return currentTimeString
    }
    var finished = MutableLiveData<Boolean>()

    fun startTimer() {
        timer = object : CountDownTimer(5000, 1000) {

            override fun onTick(p0: Long) {
                val timeLeft = p0 / 1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                finished.value = true
            }
        }.start()
    }

    fun stopTimer(){
        timer.cancel()
    }


}