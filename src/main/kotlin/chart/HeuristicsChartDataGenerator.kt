package chart

import algorithm.CspExecutor
import problem.Problem
import problem.QueensProblem

abstract class HeuristicsChartDataGenerator : ChartDataGenerator() {
    abstract val executorInitializer: (Problem) -> CspExecutor

    override fun printData(measure: (CspExecutor) -> Number) {
        println("N,kolejno,ze środka")
        for (n in 1..upperBound) {
            val normalProblem = QueensProblem(n)
            val problemWithHeuristics = problemInitializer(n)
            val normalResult = measure(executorInitializer(normalProblem))
            val heuristicsResult = measure(executorInitializer(problemWithHeuristics))
            println("$n,$normalResult,$heuristicsResult")
        }
    }
}
