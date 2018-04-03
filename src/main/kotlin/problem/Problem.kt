package problem

interface Problem {
    val numberOfVariables: Int
    val currentResult: String
    val someDomainEmpty: Boolean

    fun domainOfVariable(variableIndex: Int): Domain
    fun setVariable(variableIndex: Int, value: Int): Problem
    fun areConstrainsSatisfied(variableIndex: Int, value: Int): Boolean
    fun updateDomains(variableIndex: Int, value: Int): Problem
}
