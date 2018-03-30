class BacktrackingExecutor(private val problem: Problem) {

    fun findFirst(): String? = findFirst(0)

    private fun findFirst(variableIndex: Int): String? {
        if (variableIndex == problem.numberOfVariables) {
            return problem.currentResult
        }
        for (value in problem.domains[variableIndex]) {
            problem.setVariable(variableIndex, value)
            if (problem.areConstrainsSatisfied(variableIndex, value)) {
                findFirst(variableIndex + 1)?.let { return it }
            }
            problem.resetVariable(variableIndex, value)
        }
        return null
    }

    var counter = 0

    fun countAll(): Int {
        counter = 0
        count(0)
        return counter
    }

    private fun count(variableIndex: Int) {
        if (variableIndex == problem.numberOfVariables) {
            ++counter
            return
        }
        for (value in problem.domains[variableIndex]) {
            problem.setVariable(variableIndex, value)
            if (problem.areConstrainsSatisfied(variableIndex, value)) {
                count(variableIndex + 1)
            }
            problem.resetVariable(variableIndex, value)
        }
    }
}