interface Problem {
    val numberOfVariables: Int
    val domains: List<Domain>
    val currentResult: String
    fun setVariable(variableIndex: Int, value: Int)
    fun resetVariable(variableIndex: Int, value: Int)
    fun areConstrainsSatisfied(variableIndex: Int, value: Int): Boolean
}

typealias Domain = List<Int>