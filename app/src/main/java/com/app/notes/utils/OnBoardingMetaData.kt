package com.app.notes.utils

import com.app.notes.R
import com.app.notes.model.OnBoardingModel
//// Class to provide onboarding data for the app
class OnBoardingMetaData {
    fun getOnBoardingData(): List<OnBoardingModel> {
        return listOf(
            OnBoardingModel(
                headText = "Welcome to Notefy",
                tailText = "Your ultimate note-taking app",
                icon = R.drawable.notes
            ),
            OnBoardingModel(
                headText = "Organize Your Notes",
                tailText = "Keep your notes organized and easily accessible",
                icon = R.drawable.createfiles
            ),
            OnBoardingModel(
                headText = "Stay Productive",
                tailText = "Boost your productivity with efficient note management",
                icon = R.drawable.save
            ),

        )
    }
}