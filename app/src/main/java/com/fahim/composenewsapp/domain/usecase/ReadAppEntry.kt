package com.fahim.composenewsapp.domain.usecase

import com.fahim.composenewsapp.domain.manger.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(val localUserManager: LocalUserManager) {

    suspend operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}