import java.util.*

open class MyStack<E>() {

    protected var list = LinkedList<E>()


    fun push(e:E){
        if(list.isEmpty())
            list.add(e)
        else {
            val newList = LinkedList<E>(listOf(e))
            newList.addAll(list)
            list = newList
        }
    }

    fun size() = list.size;

    fun isEmpty() = list.isEmpty()

    fun pop():E?{
        if(list.isEmpty()) return null;
        val temp = list[0]
        list.removeAt(0)
        return temp;
    }

    fun peek():E? {
        if (list.isEmpty()) return null
        return list[0]
    }

}

class MyDeque<E> : MyStack<E>() {

    fun popLast():E?{
        if(list.isEmpty()) return null
        val temp = list[list.size-1]
        list.removeAt(list.size-1)
        return temp;
    }

    fun peekLast():E?{
        if(list.isEmpty()) return null
        return list[list.size-1]
    }

    fun pushLast(e:E) = list.add(e)

}