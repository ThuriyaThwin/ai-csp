package algorithm

import problem.Problem

abstract class CspExecutor(protected val problem: Problem) {

    var operationsCount: Int = 0
        protected set

    fun findFirst(): String? {
        operationsCount = 0
        return findFirst(0, problem)
    }

    protected fun findFirst(variableIndex: Int, problem: Problem): String? {
        ++operationsCount
        return if (variableIndex == problem.numberOfVariables) {
            problem.currentResult
        } else {
            searchInSubsequentValues(variableIndex, problem)
        }
    }

    protected abstract fun searchInSubsequentValues(variableIndex: Int, problem: Problem): String?

    private var resultsCount: Int = 0

    fun countAll(): Int {
        resultsCount = 0
        operationsCount = 0
        count(0, problem)
        return resultsCount
    }

    protected fun count(variableIndex: Int, problem: Problem) {
        ++operationsCount
        if (variableIndex == problem.numberOfVariables) {
            ++resultsCount
        } else {
            checkSubsequentValues(variableIndex, problem)
        }
    }

    private fun checkSubsequentValues(variableIndex: Int, problem: Problem) {
        for (value in problem.domainOfVariable(variableIndex)) {
            checkValue(variableIndex, value, problem)
        }
    }

    protected abstract fun checkValue(variableIndex: Int, value: Int, problem: Problem)

    abstract fun printName()
}
