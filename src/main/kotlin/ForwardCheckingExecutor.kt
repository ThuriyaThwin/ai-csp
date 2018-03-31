class ForwardCheckingExecutor(private val problem: Problem) : CspExecutor {

    override fun findFirst(): String? {
        return findFirst(0, problem)
    }

    private fun findFirst(variableIndex: Int, problem: Problem): String? =
            if (variableIndex == problem.numberOfVariables) {
                problem.currentResult
            } else {
                searchInSubsequentValues(problem, variableIndex)
            }

    private fun searchInSubsequentValues(problem: Problem, variableIndex: Int): String? {
        for (value in problem.domains[variableIndex]) {
            problem.setVariable(variableIndex, value)
            val newProblem = problem.updateDomains(variableIndex, value)
            if (!problem.someDomainEmpty) {
                val result = findFirst(variableIndex + 1, newProblem)
                result?.let { return it }
            }
        }
        return null
    }

    private var counter: Int = 0

    override fun countAll(): Int {
        counter = 0
        count(0)
        return counter
    }

    private fun count(variableIndex: Int) {
        if (variableIndex == problem.numberOfVariables) {
            ++counter
        } else {
            checkSubsequentValues(variableIndex)
        }
    }

    private fun checkSubsequentValues(variableIndex: Int) {
        for (value in problem.domains[variableIndex]) {
            checkValue(variableIndex, value)
        }
    }

    private fun checkValue(variableIndex: Int, value: Int) {
        problem.setVariable(variableIndex, value)
        if (problem.areConstrainsSatisfied(variableIndex, value)) {
            count(variableIndex + 1)
        }
    }
}
