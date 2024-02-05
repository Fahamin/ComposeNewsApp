package com.fahim.composenewsapp.domain.usecase

import com.fahim.composenewsapp.domain.manger.LocalUserManager

class SaveEntry(val localUserManager: LocalUserManager) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}