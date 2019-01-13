package com.anwesh.uiprojects.linkedsqsiderectmoverview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.sqsiderectmoverview.SqSideRectMoverView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SqSideRectMoverView.create(this)
    }
}
