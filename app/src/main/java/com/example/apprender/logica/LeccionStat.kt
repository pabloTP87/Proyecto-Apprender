package com.example.apprender.logica

class LeccionStat {

    fun puntajeTotal(listaPuntaje: ArrayList<Int>) : Int{

        return  listaPuntaje.sum()
    }

    fun cantidadCorrectas(listaAciertos: ArrayList<Boolean>) : Int{

        var correctas = 0

        for (acierto in listaAciertos){

            if (acierto){
                correctas++
            }
        }
        return correctas
    }

    fun cantidadIncorrectas(listaAciertos: ArrayList<Boolean>) : Int{

        var incorrectas = 0

        for (acierto in listaAciertos){

            if (!acierto){
                incorrectas++
            }
        }
        return incorrectas
    }
}