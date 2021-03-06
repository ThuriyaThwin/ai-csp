package problem

import chooser.Chooser
import copy

data class QueensProblem(
    override val numberOfVariables: Int,
    private val chooser: Chooser = Chooser(numberOfVariables),
    private val board: List<Variable> = normalBoard(numberOfVariables)
) : Problem {

    override val currentResult: String
        get() = board.map { it.value }.toString()

    override val someDomainEmpty: Boolean
        get() = board.any { it.domain.isEmpty() }

    override fun setVariable(variableIndex: Int, value: Int): Problem {
        val actualVariableIndex = chooser.actualVariableIndex(variableIndex)
        val updatedBoard = board.copy {
            this[actualVariableIndex] = this[actualVariableIndex].copy(value = value)
        }
        return copy(board = updatedBoard)
    }

    override fun domainOfVariable(variableIndex: Int): Domain {
        val actualVariableIndex = chooser.actualVariableIndex(variableIndex)
        return board[actualVariableIndex].domain
    }

    override fun areConstrainsSatisfied(variableIndex: Int, value: Int): Boolean {
        val actualVariableIndex = chooser.actualVariableIndex(variableIndex)
        for (otherColumn in (0 until numberOfVariables) - actualVariableIndex) {
            if (board[otherColumn].value != 0) {
                if (board[otherColumn].value == value) return false
                if (Math.abs(actualVariableIndex - otherColumn) == Math.abs(value - board[otherColumn].value)) return false
            }
        }
        return true
    }

    override fun updateDomains(variableIndex: Int, value: Int): Problem {
        val actualVariableIndex = chooser.actualVariableIndex(variableIndex)
        val newBoard = board.copy {
            for (columnIndex in (0 until numberOfVariables) - actualVariableIndex) {
                val columnsDistance = Math.abs(columnIndex - actualVariableIndex)
                val oldDomain = this[columnIndex].domain
                val newDomain = oldDomain - value - (value + columnsDistance) - (value - columnsDistance)
                this[columnIndex] = this[columnIndex].copy(domain = newDomain)
            }
        }
        return copy(board = newBoard)
    }
}
