package com.bkosawa.samples.cleancodearchitecture.home.usecases

import com.bkosawa.samples.cleancodearchitecture.home.entity.DataClass

class ShowHomeUseCase {
    operator fun invoke(): DataClass = DataClass(1, "value")
}