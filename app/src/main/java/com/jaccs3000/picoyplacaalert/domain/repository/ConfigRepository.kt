package com.picoyplacaalert.domain.repository

import com.picoyplacaalert.domain.model.PicoPlacaConfig
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {

    suspend fun saveConfig(config: PicoPlacaConfig)

    fun getConfig(): Flow<PicoPlacaConfig?>
}
