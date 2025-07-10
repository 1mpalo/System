package com.example.system.domain.usecases.stats

data class StatsUseCases (
    val setAgility: SetAgility,
    val getAgility: GetAgility,
    val setIntelligence: SetIntelligence,
    val getIntelligence: GetIntelligence,
    val setStrength: SetStrength,
    val getStrength: GetStrength,
    val setVitality: SetVitality,
    val getVitality: GetVitality,
    val setLevel: SetLevel,
    val getLevel: GetLevel
)