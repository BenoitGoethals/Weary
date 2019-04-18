package be.mil.weary

public class Nationality(val natoMilitary:Int, val natoCivilian:Int, val nonNatoMilitary:Int, val nonNatoCivilian:Int, val detainee:Int, val embeddedInterpreter:Int, val cIVCAScausedbyNATO:Int, val child:Int) {
    override fun toString(): String {
        return "Nationality(natoMilitary=$natoMilitary, natoCivilian=$natoCivilian, nonNatoMilitary=$nonNatoMilitary, nonNatoCivilian=$nonNatoCivilian, detainee=$detainee, embeddedInterpreter=$embeddedInterpreter, cIVCAScausedbyNATO=$cIVCAScausedbyNATO, child=$child)"
    }
}