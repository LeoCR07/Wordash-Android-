package com.example.betadiccompose.Domain.Game_Provider

import android.content.Context
import android.media.MediaPlayer
import com.example.betadiccompose.Domain.Prefs
import com.example.betadiccompose.data.network_database.model.DataWorld
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.random.Random

class GameProvider @Inject constructor(@ApplicationContext context: Context) {

    val cont = context
    var currentID = 0

    val prefs  = Prefs(cont)

    //Talk and Sort
    fun GetOneWord(lista:List<DataWorld>):DataWorld{
        var word:DataWorld

        val lstSubCats = prefs.GetSubCategory().split(",")
        val lstComplete:ArrayList<DataWorld> = ArrayList()


        //Busca los elementos q tenga la sub categoria y los guardo en una lista
        for (i in 0 until lista.size){
            for (j in 0 until lstSubCats.size){
                if(lista[i].subcat == lstSubCats[j].toInt()){
                    lstComplete.add(lista[i])
                }
            }
        }

        //Genero el unico id
        var indice = Random.nextInt(0,lstComplete.size)
        word = lstComplete[indice]

        return word
    }

    //Music
    fun SoundChoose(lista:List<DataWorld>): ArrayList<DataWorld> {

        var lstIndice:ArrayList<Int> = ArrayList() //Lista de los 6 indices
        val lstSubCats = prefs.GetSubCategory().split(",") //lista de sub catergorias
        val lstComplete:ArrayList<DataWorld> = ArrayList()  //alamcena la lista filtrada
        var listaWord :ArrayList<DataWorld> = ArrayList<DataWorld>() // almacena los datos del data word


        //Busca los elementos q tenga la sub categoria y los guardo en una lista
        for (i in 0 until lista.size){
            for (j in 0 until lstSubCats.size){
                if(lista[i].subcat == lstSubCats[j].toInt()){
                    lstComplete.add(lista[i])
                }
            }
        }

        //Generar al indice de respuesta (indice)
        var id =  Random.nextInt(0,lstComplete.size)
        lstIndice.add(id)

        //Generar 5 indice las otras diferentes opciones
        do{

            var contandor = 0
            var valor = Random.nextInt(0,lstComplete.size)

            for (e in lstIndice){
                if(e!=valor){
                    contandor++
                }
            }

            if(contandor == lstIndice.size){
                lstIndice.add(valor)
            }

        }while (lstIndice.size<6)

        //Asigno los indices con su valor a la lista final
        for (i in 0 until lstIndice.size){
            listaWord.add(lstComplete[lstIndice[i]])
        }

        return listaWord
    }

    fun MakeEasyAndHard(lista:List<DataWorld>) :List<DataWorld>{

        var lstIndice:ArrayList<Int> = ArrayList() //Lista de los 4 indices
        val lstSubCats = prefs.GetSubCategory().split(",") //lista de sub catergorias
        val lstComplete:ArrayList<DataWorld> = ArrayList()  //alamcena la lista filtrada
        var listaWord :ArrayList<DataWorld> = ArrayList<DataWorld>() // lista final


        //Busca los elementos q tenga la sub categoria y los guardo en una lista
        for (i in 0 until lista.size){
            for (j in 0 until lstSubCats.size){

                if(lista[i].subcat == lstSubCats[j].toInt()){
                    println("-----------------------------")
                    lstComplete.add(lista[i])
                    println(lista[i].World_1)

                }
            }
        }


        for(e in lstSubCats){
            println(e)
        }


        //Generar al indice de respuesta (indice)
        //var id =  Random.nextInt(0,lstComplete.size)

        var id =  (lstComplete.indices).random()
        lstIndice.add(id)

        //Generar 3 indice las otras diferentes opciones
        do{

            var contandor = 0
            //var valor = Random.nextInt(0,lstComplete.size)

            var valor = (lstComplete.indices).random()
            for (e in lstIndice){
                if(e!=valor){
                    contandor++
                }
            }

            if(contandor == lstIndice.size){
                lstIndice.add(valor)
            }

        }while (lstIndice.size<4)

        //Asigno los indices con su valor a la lista final
        for (i in 0 until lstIndice.size){
            listaWord.add(lstComplete[lstIndice[i]])
            println(i.toString())
        }

        return listaWord
    }

    //Mal escrito
    fun MakeWrongWritten(lista:List<DataWorld>):List<DataWorld>{

        var lstIndice:ArrayList<Int> = ArrayList() //Lista de los 4 indices
        val lstSubCats = prefs.GetSubCategory().split(",") //lista de sub catergorias
        val lstComplete:ArrayList<DataWorld> = ArrayList()  //alamcena la lista filtrada
        var listaWord :ArrayList<DataWorld> = ArrayList<DataWorld>() // lista final


        //Busca los elementos q tenga la sub categoria y los guardo en una lista
        for (i in 0 until lista.size){
            for (j in 0 until lstSubCats.size){
                if(lista[i].subcat == lstSubCats[j].toInt()){
                    lstComplete.add(lista[i])
                }
            }
        }

        //Generar al indice de respuesta (indice)
        var id =  Random.nextInt(0,lstComplete.size)
        lstIndice.add(id)

        //Generar 3 indice las otras diferentes opciones
        do{

            var contandor = 0
            var valor = Random.nextInt(0,lstComplete.size)

            for (e in lstIndice){
                if(e!=valor){
                    contandor++
                }
            }

            if(contandor == lstIndice.size){
                lstIndice.add(valor)
            }

        }while (lstIndice.size<4)

        //Asigno los indices con su valor a la lista final
        for (i in 0 until lstIndice.size){

            if(i!=0){
                listaWord.add(
                    DataWorld(
                        id = i,
                        World_1 = GenerarTexto(Random.nextInt(0,4),lstComplete[lstIndice[i]].World_1),
                        World_2 = lstComplete[lstIndice[i]].World_2,
                        Img = lstComplete[lstIndice[i]].Img

                    )
                )
            }else{
                listaWord.add(lstComplete[lstIndice[i]])
            }

        }

        return listaWord
    }
    
    fun GenerarTexto(i:Int,palabra:String): String {
      val valor =  when(i){
            0->EliminarWord(palabra)
            1->AgregarWord(palabra)
            2->RemplazarVowel(palabra)
            3->ReemplazarWord(palabra)
          else-> ""
        }

        return valor
    }
    
    private fun ReemplazarWord(valor: String):String {


        val lstalphabet:List<String> = listOf("q","w","r","t","y","p","s","d","f","g","h","j","k"
            ,"l","ñ","z","x","c","v","b","n","m")

        var total = ""
        var palabra = valor.lowercase().trim()

        var lstconsonantWord:ArrayList<Char> = arrayListOf()
        var lstconsonantfilter:ArrayList<String> = arrayListOf()
        var lstconsonantIndex:ArrayList<Int> = arrayListOf()

        var indice :Int

        //To know if it has a consonante
        for (i in 0..palabra.length-1) {
            for(j in 0..lstalphabet.count()-1){
                if(palabra[i]+"" == lstalphabet[j]){
                    lstconsonantWord.add(palabra[i])
                    lstconsonantIndex.add(i)
                }
            }
        }

        if(lstconsonantIndex.isNotEmpty() ){
            //Take the consonant
            val aux = Random.nextInt(0,lstconsonantIndex.count())
            indice = lstconsonantIndex[aux]

            //filter the consonant
            for(j in 0..lstalphabet.count()-1){
                if(palabra[indice]+"" != lstalphabet[j]){
                    lstconsonantfilter.add(lstalphabet[j])
                }
            }

            //chose the new vowel
            val temp = Random.nextInt(0,lstconsonantfilter.count())
            val vowel = lstconsonantfilter[temp]

            //iterate and show the word with the changes
            for(i in 0..palabra.length-1){
                if(indice == i){
                    total+=vowel
                }else{
                    total+=palabra[i]
                }

            }

        }else{
            println("Cambiar de metodo")
        }

        return total
    }

    private fun EliminarWord(valor: String) :String{

        var palabra = valor.trim()
        var indice = Random.nextInt(0, palabra.length)
        var total = ""

        for (i in 0..palabra.length - 1) {
            if (indice != i) {
                total+=palabra[i]
            }
        }

        return total
    }

    private fun AgregarWord(valor: String):String {

        var palabra = valor.trim()
        var indice = Random.nextInt(0, palabra.length)
        var total = ""

        for (i in 0..palabra.length - 1) {
            if (indice == i) {
                total+=palabra[i]
            }
            total+=palabra[i]
        }
        return total
    }

    private fun RemplazarVowel(valor: String): String {

        val lstvowels :List<String> = listOf("a","e","i","o","u")

        var palabra = valor.lowercase().trim()
        var total = ""
        var lstvowelsWord:ArrayList<Char> = arrayListOf()
        var lstvowefilter:ArrayList<String> = arrayListOf()
        var lstvowelsIndex:ArrayList<Int> = arrayListOf()

        var indice :Int

        //To know if it has a vowel
        for (i in 0..palabra.length-1) {
            for(j in 0..lstvowels.count()-1){
                if(palabra[i]+"" == lstvowels[j]){
                    lstvowelsWord.add(palabra[i])
                    lstvowelsIndex.add(i)
                }
            }
        }

        if(lstvowelsIndex.isNotEmpty()){
            //Take the vowel
            val aux = Random.nextInt(0,lstvowelsIndex.count())
            indice = lstvowelsIndex[aux]

            //filter the vowels
            for(j in 0..lstvowels.count()-1){
                if(palabra[indice]+"" != lstvowels[j]){
                    lstvowefilter.add(lstvowels[j])
                }
            }

            //chose the new vowel
            val temp = Random.nextInt(0,lstvowefilter.count())
            val vowel = lstvowefilter[temp]

            //iterate and show the word with the changes
            for(i in 0..palabra.length-1){
                if(indice == i){
                    total += vowel
                }else{
                    total += palabra[i]
                }

            }

        }else{
            println("Cambiar de metodo")
        }

        return total
    }

    private fun RandomText(lst:List<DataWorld>): MutableList<DataWorld> {

        var lista = lst.toMutableList()
        //var lista = emptyList<DataWorld>()

        var randomvalue :Int
        var aux:DataWorld

        for (i in (0 until lst.size)){
            randomvalue = (lista.indices).random()
            aux = lista[randomvalue]
            lista[randomvalue] = lista[i]
            lista[i] = aux
        }

       return lista

    }

    fun KeySound(beat :Int){
        var mediaPlayer = MediaPlayer.create(cont, beat)
        mediaPlayer.start()
    }
}