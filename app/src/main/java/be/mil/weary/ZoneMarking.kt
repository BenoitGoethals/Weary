package be.mil.weary

enum class ZoneMarking(val rgb: Int) {
    APANELS(1),
    BPYRO(2),
    CSMOK(3),
    DNONE(4);

    override fun toString(): String{
        return "ZoneMarking(rgb=$rgb)"
    }


}