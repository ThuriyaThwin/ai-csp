package chart

import algorithm.BacktrackingExecutor
import algorithm.CspExecutor
import algorithm.ForwardCheckingExecutor
import problem.Problem
import kotlin.system.measureTimeMillis

abstract class ChartDataGenerator {
    private val upperBound: Int = 30
    abstract val problemInitializer: (n: Int) -> Problem

    fun measureTimeForAll() = printData {
        measureTimeMillis {
            it.countAll()
        }
    }

    fun measureTimeForFirst() = printData {
        measureTimeMillis {
            it.findFirst()
        }
    }

    fun measureWorkForAll() = printData {
        it.countAll()
        it.operationsCount
    }

    fun measureWorkForFirst() = printData {
        it.findFirst()
        it.operationsCount
    }

    private fun printData(measure: (CspExecutor) -> Number) {
        println("N,backtracking,forward checking")
        for (n in 1..upperBound) {
            val problem = problemInitializer(n)
            val backtrackingResult = measure(BacktrackingExecutor(problem))
            val forwardCheckingResult = measure(ForwardCheckingExecutor(problem))
            println("$n,$backtrackingResult,$forwardCheckingResult")
        }
    }
}
