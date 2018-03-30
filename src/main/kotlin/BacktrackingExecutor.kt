class BacktrackingExecutor(private val problem: Problem) {

    fun findFirst(): String? {
        return findFirst(0)
    }

    private fun findFirst(variableIndex: Int): String? {
        if (variableIndex == problem.domains.size) {
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
}