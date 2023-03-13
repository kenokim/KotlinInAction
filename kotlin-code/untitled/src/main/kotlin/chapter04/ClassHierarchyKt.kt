package chapter04

class ClassHierarchyKt {
    interface Clickable {
        fun showOff() = println("Clickable")
    }

    interface Focusable {
        fun showOff() = println("Focusable")
    }

    class Button : Clickable, Focusable {
        override fun showOff() {
            super<Clickable>.showOff()
        }
    }

}