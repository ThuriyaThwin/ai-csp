class BacktrackingExecutor(private val problem: Problem) : CspExecutor {

    override fun findFirst(): String? = findFirst(0)

    private fun findFirst(variableIndex: Int): String? =
            if (variableIndex == problem.numberOfVariables) {
                problem.currentResult
            } else {
                searchInSubsequentValues(variableIndex)
            }

    private fun searchInSubsequentValues(variableIndex: Int): String? {
        for (value in problem.domains[variableIndex]) {
            problem.setVariable(variableIndex, value)
            if (problem.areConstrainsSatisfied(variableIndex, value)) {
                findFirst(variableIndex + 1)?.let { return it }
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
        if (variableIndex == this.problem.numberOfVariables) {
            ++counter
        } else {
            checkSubsequentValues(variableIndex, problem)
        }
    }

    private fun checkSubsequentValues(variableIndex: Int, problem: Problem) {
        for (value in problem.domains[variableIndex]) {
            checkValue(variableIndex, value, problem)
        }
    }

    private fun checkValue(variableIndex: Int, value: Int, problem: Problem) {
        problem.setVariable(variableIndex, value)
        val newProblem = problem.updateDomains(variableIndex, value)
        if (!problem.someDomainEmpty) {
            count(variableIndex + 1, newProblem)
        }
    }
}
