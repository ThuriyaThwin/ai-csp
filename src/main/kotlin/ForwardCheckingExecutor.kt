class ForwardCheckingExecutor(private val problem: Problem) : CspExecutor {

    override fun findFirst(): String? {
        return findFirst(0)
    }

    fun findFirst(variableIndex: Int): String? {
//        if (variableIndex == problem.numberOfVariables) {
//            return problem.currentResult
//        }
//        for (value in problem.domains[variableIndex]) {
//            problem.setVariable(variableIndex, value)
//            newProblem = problem.updateDomains(variableIndex, value)
//            if (!problem.isSomeDomainEmpty $$ findFirst(variableIndex++, newProblem) != null) {
//                return it
//            }
//        }
//        return null
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun countAll(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
