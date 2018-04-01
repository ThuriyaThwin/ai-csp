abstract class CspExecutor(protected val problem: Problem) {

    fun findFirst(): String? = findFirst(0, problem)

    protected fun findFirst(variableIndex: Int, problem: Problem): String? =
            if (variableIndex == problem.numberOfVariables) {
                problem.currentResult
            } else {
                searchInSubsequentValues(variableIndex, problem)
            }

    protected abstract fun searchInSubsequentValues(variableIndex: Int, problem: Problem): String?

    private var counter: Int = 0

    fun countAll(): Int {
        counter = 0
        count(0, problem)
        return counter
    }

    protected fun count(variableIndex: Int, problem: Problem) {
        if (variableIndex == problem.numberOfVariables) {
            ++counter
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
