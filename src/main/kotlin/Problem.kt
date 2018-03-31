interface Problem {
    val numberOfVariables: Int
    val domains: List<Domain>
    val currentResult: String
    val someDomainEmpty: Boolean

    fun setVariable(variableIndex: Int, value: Int)
    fun resetVariable(variableIndex: Int, value: Int)
    fun areConstrainsSatisfied(variableIndex: Int, value: Int): Boolean
    fun updateDomains(variableIndex: Int, value: Int): Problem
}

typealias Domain = List<Int>