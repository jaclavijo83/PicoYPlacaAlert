package com.picoyplacaalert.domain.usecase

import com.picoyplacaalert.domain.error.ValidationError
import com.picoyplacaalert.domain.model.PicoPlacaConfig
import com.picoyplacaalert.domain.repository.ConfigRepository

class SavePicoPlacaConfigUseCase(
    private val repository: ConfigRepository
) {

    suspend operator fun invoke(config: PicoPlacaConfig) {
        if (config.rules.isEmpty()) throw ValidationError.EmptyRules
        repository.saveConfig(config)
    }
}
