package com.networkedassets.gherkin.runner.specification

import groovy.lang.Closure
import com.networkedassets.gherkin.runner.gherkin.StepKeyword
import com.networkedassets.gherkin.runner.gherkin.StepKeyword.*

open class FeatureSpecification {
    val stepDefs = mutableMapOf<Pair<StepKeyword, String>, Closure<Any>>()

    private var lastType: StepKeyword = GIVEN

    fun given(stepText: String, closure: Closure<Any>) {
        stepDefs.put(Pair(GIVEN, stepText), closure)
        lastType = GIVEN
    }

    fun `when`(stepText: String, closure: Closure<Any>) {
        stepDefs.put(Pair(WHEN, stepText), closure)
        lastType = WHEN
    }

    fun then(stepText: String, closure: Closure<Any>) {
        stepDefs.put(Pair(THEN, stepText), closure)
        lastType = THEN
    }

    fun and(stepText: String, closure: Closure<Any>) {
        when (lastType) {
            GIVEN -> given(stepText, closure)
            WHEN -> `when`(stepText, closure)
            THEN -> then(stepText, closure)
        }
    }

    fun clearStepDefs() {
        stepDefs.clear()
    }
}