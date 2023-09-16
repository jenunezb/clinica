package co.edu.uniquindio.proyecto.modelo.enums;

public enum Alergia {
    ALERGIA_AL_POLEN("Alergia al polen"),
    ALERGIA_AL_POLVO("Alergia al polvo"),
    ALERGIA_ALIMENTARIA("Alergia alimentaria"),
    ALERGIA_AL_LÁTEX("Alergia al látex"),
    ALERGIA_A_LOS_ÁCAROS("Alergia a los ácaros"),
    ALERGIA_AL_SOL("Alergia al sol"),
    ALERGIA_A_LOS_GATOS("Alergia a los gatos"),
    ALERGIA_A_LOS_PERROS("Alergia a los perros"),
    ALERGIA_AL_MARISCO("Alergia al marisco"),
    ALERGIA_AL_CACAHUETE("Alergia al cacahuete"),
    ALERGIA_A_LOS_FRUTOS_SECOS("Alergia a los frutos secos"),
    ALERGIA_A_LAS_AVISPAS("Alergia a las avispas"),
    ALERGIA_AL_POLLO("Alergia al pollo"),
    ALERGIA_AL_HUEVO("Alergia al huevo"),
    ALERGIA_A_LOS_MEDICAMENTOS("Alergia a los medicamentos"),
    ALERGIA_A_LA_SOJA("Alergia a la soja"),
    ALERGIA_AL_TOMATE("Alergia al tomate"),
    ALERGIA_AL_PESCADO("Alergia al pescado"),
    ALERGIA_AL_TRIGO("Alergia al trigo"),
    ALERGIA_AL_MANÍ("Alergia al maní");

    private String nombre;

    Alergia(String nombre) {
        this.nombre = nombre;
    }

}

