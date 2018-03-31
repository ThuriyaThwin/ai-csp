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
