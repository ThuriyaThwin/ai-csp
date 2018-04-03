package problem

import copy

class LatinSquareProblem(
        private val n: Int,
        private val square: List<List<Variable>> = List(n) { List(n) { Variable(0, List(n) { it + 1 }) } }
) : Problem {

    override val numberOfVariables = n * n

    override val currentResult: String
        get() = square.map { it.map { it.value } }.toString()

    override val someDomainEmpty: Boolean
        get() = square.any { it.any { it.domain.isEmpty() } }

    override fun domainOfVariable(variableIndex: Int): Domain {
        val rowIndex = variableIndex / n
        val columnIndex = variableIndex % n
        return square[rowIndex][columnIndex].domain
    }

    override fun setVariable(variableIndex: Int, value: Int): Problem {
        val mutableSquare = square.map { it.toMutableList() }.toMutableList()
        mutableSquare[variableIndex / n][variableIndex % n] = mutableSquare[variableIndex / n][variableIndex % n].copy(value = value)
        return LatinSquareProblem(n, mutableSquare)
    }

    override fun areConstrainsSatisfied(variableIndex: Int, value: Int): Boolean {
        val rowIndex = variableIndex / n
        val columnIndex = variableIndex % n
        return !alreadyInRow(rowIndex, columnIndex, value) && !alreadyInColumn(rowIndex, columnIndex, value)
    }

    private fun alreadyInRow(rowIndex: Int, columnIndex: Int, value: Int): Boolean {
        for (previousColumns in 0 until columnIndex) {
            if (square[rowIndex][previousColumns].value == value) {
                return true
            }
        }
        return false
    }

    private fun alreadyInColumn(rowIndex: Int, columnIndex: Int, value: Int): Boolean {
        for (previousRow in 0 until rowIndex) {
            if (square[previousRow][columnIndex].value == value) {
                return true
            }
        }
        return false
    }

    override fun updateDomains(variableIndex: Int, value: Int): Problem { // todo:
        val rowIndex = variableIndex / n
        val columnIndex = variableIndex % n
        val newDomains = square.toMutableList()
        newDomains[rowIndex] = newDomainsInRow(rowIndex, columnIndex, value)
        val square = updateDomainsInColumn(rowIndex, columnIndex, value, newDomains)
        return LatinSquareProblem(n, square)
    }

    private fun newDomainsInRow(rowIndex: Int, columnIndex: Int, valueToRemove: Int) =
            square[rowIndex].mapIndexed { index, element ->
                if (index == columnIndex) element
                else element.copy(domain = element.domain - valueToRemove)
            }

    private fun updateDomainsInColumn(rowIndex: Int, columnIndex: Int, value: Int, square: List<List<Variable>>) =
            square.mapIndexed { index, list ->
                if (index == rowIndex) list
                else updateRow(list, columnIndex, value)
            }

    private fun updateRow(row: List<Variable>, columnIndex: Int, value: Int): List<Variable> =
            row.copy { this[columnIndex] = this[columnIndex].copy(domain = this[columnIndex].domain - value) }
}
