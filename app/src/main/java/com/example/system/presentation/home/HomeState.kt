package com.example.system.presentation.home

data class HomeState(
    var level: Int = 1,
    var strength: Int = 0,
    var agility: Int = 0,
    var intelligence: Int = 0,
    var vitality: Int = 0,
    var title: String = "None"
)