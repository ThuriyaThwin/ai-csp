import algorithm.BacktrackingExecutor
import algorithm.CspExecutor
import algorithm.ForwardCheckingExecutor
import problem.LatinSquareProblem
import problem.Problem
import problem.QueensProblem
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    CspCli().serveUser()
}

class CspCli {

    fun serveUser() {
        try {
            tryToServeUser()
        } catch (e: Exception) {
            println(e)
            serveUser()
        }
    }

    private fun tryToServeUser() {
        println("Usage: queens/square n first/all")
        val line = readLine()!!.split(' ')
        val (problemName, nString, mode) = line
        val problem = problem(problemName, nString)
        val ifFirst = isFirstMode(mode)
        printResults(problem, ifFirst)
        serveUser()
    }

    private fun isFirstMode(mode: String): Boolean {
        val firstLetter = mode[0]
        return firstLetter == 'f' || firstLetter == 'F'
    }

    private fun problem(problemName: String, nString: String): Problem {
        val firstLetter = problemName[0]
        val n = nString.toInt()
        return when (firstLetter) {
            'q', 'Q' -> QueensProblem(n)
            else -> LatinSquareProblem(n)
        }
    }

    private fun printResults(problem: Problem, first: Boolean) {
        val backtrackingExecutor = BacktrackingExecutor(problem)
        printResult(backtrackingExecutor, first) // todo: problem immutable?
        val forwardCheckingExecutor = ForwardCheckingExecutor(problem)
        printResult(forwardCheckingExecutor, first)
    }

    private fun printResult(executor: CspExecutor, first: Boolean) {
        executor.printName()
        val method = if (first) executor::findFirst else executor::countAll
        val time = measureTimeMillis {
            println("result: ${method()}")
        }
        println("time: $time ms")
        println("operations count: ${executor.operationsCount}")
    }
}
