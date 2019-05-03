package com.blinkslabs.blinkist.android.challenge.ui.sorter

import com.blinkslabs.blinkist.android.challenge.data.model.Books

inline class Title(val value: String)

inline class Year(val value: Int)

typealias WeeklyGroup = Map<Title, Books>

typealias YearlyGroup = Map<Year, WeeklyGroup>

typealias AlphabeticGroup = Map<Title, Books>