package problem

import chooser.Chooser
import chooser.MiddleChooser

class MiddleIterator(private val list: List<Int>) : Iterator<Int> {
    var index: Int = 0
    var chooser: Chooser = MiddleChooser(list.size)

    override fun hasNext(): Boolean = index < list.size

    override fun next(): Int {
        val actualIndex = chooser.actualVariableIndex(index)
        ++index
        return list[actualIndex]
    }
}
