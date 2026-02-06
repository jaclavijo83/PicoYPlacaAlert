package com.picoyplacaalert.domain.usecase

import com.picoyplacaalert.domain.repository.ConfigRepository

class GetPicoPlacaConfigUseCase(
    private val repository: ConfigRepository
) {

    operator fun invoke() = repository.getConfig()
}
