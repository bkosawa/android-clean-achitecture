package com.bkosawa.samples.cleancodearchitecture.home.domain

import com.bkosawa.samples.cleancodearchitecture.home.data.Cake
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShowHomeUseCase {
    operator fun invoke(): Flow<List<Cake>> = flow {
        emit(
            listOf(
                Cake(
                    0,
                    "Chocolate Cake",
                    "€15.00",
                    "https://bkosawa.github.io/data/cake/0/pic.png"
                ),
                Cake(
                    1,
                    "Chocolate Cake",
                    "€20.00",
                    "https://bkosawa.github.io/data/cake/1/pic.png"
                ),
                Cake(
                    2,
                    "Chocolate Cake",
                    "€20.00",
                    "https://bkosawa.github.io/data/cake/2/pic.png"
                ),
                Cake(
                    3,
                    "Chocolate Cake",
                    "€20.00",
                    "https://bkosawa.github.io/data/cake/3pic.png"
                ),
                Cake(
                    4,
                    "Chocolate Cake",
                    "€20.00",
                    "https://bkosawa.github.io/data/cake/4/pic.png"
                ),
                Cake(
                    5,
                    "Chocolate Cake",
                    "€20.00",
                    "https://bkosawa.github.io/data/cake/5/pic.png"
                ),
                Cake(
                    6,
                    "Chocolate Cake",
                    "€20.00",
                    "https://bkosawa.github.io/data/cake/6/pic.png"
                )
            )
        )
    }
}