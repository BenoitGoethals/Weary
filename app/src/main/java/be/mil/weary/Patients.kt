package be.mil.weary

data class Patients(val immediate:Int, val urgent:Int,val priority:Int, val routine:Int) {

    override fun toString(): String {
        return "Patients(immediate=$immediate, urgent=$urgent, priority=$priority, routine=$routine)"
    }
}