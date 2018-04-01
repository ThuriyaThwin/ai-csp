class BacktrackingExecutor(private val problem: Problem) : CspExecutor {

    override fun printName() {
        println("Backtracking: ")
    }

    override fun findFirst(): String? = findFirst(0, problem)

    private fun findFirst(variableIndex: Int, problem: Problem): String? =
            if (variableIndex == problem.numberOfVariables) {
                problem.currentResult
            } else {
                searchInSubsequentValues(variableIndex, problem)
            }

    private fun searchInSubsequentValues(variableIndex: Int, problem: Problem): String? {
        for (value in problem.domainOfVariable(variableIndex)) {
            val newProblem = problem.setVariable(variableIndex, value)
            if (newProblem.areConstrainsSatisfied(variableIndex, value)) {
                findFirst(variableIndex + 1, newProblem)?.let { return it }
            }
        }
        return null
    }

    private var counter: Int = 0

    override fun countAll(): Int {
        counter = 0
        count(0, problem)
        return counter
    }

    private fun count(variableIndex: Int, problem: Problem) {
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

    private fun checkValue(variableIndex: Int, value: Int, problem: Problem) {
        val newProblem = problem.setVariable(variableIndex, value)
        if (newProblem.areConstrainsSatisfied(variableIndex, value)) {
            count(variableIndex + 1, newProblem)
        }
    }
}
