package com.picoyplacaalert.domain.model

data class PicoPlacaConfig(
    val rules: List<PicoPlacaRule>,
    val timeWindows: List<TimeWindow>,
    val isEnabled: Boolean
)
