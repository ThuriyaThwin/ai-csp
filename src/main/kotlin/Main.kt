import algorithm.BacktrackingExecutor
import problem.QueensProblem
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    println("N,backtracking(czas),forward checking(czas)")
    for (n in 1..13) {
        val problem = QueensProblem(n)
        val backtracking = BacktrackingExecutor(problem)
        val backtrackingTime = measureTimeMillis {
            backtracking.countAll()
        }
        val forwardChecking = BacktrackingExecutor(problem)
        val forwardCheckingTime = measureTimeMillis {
            forwardChecking.countAll()
        }
        println("$n,$backtrackingTime,$forwardCheckingTime")
    }
}