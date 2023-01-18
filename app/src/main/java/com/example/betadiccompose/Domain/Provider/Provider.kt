package com.example.betadiccompose.Domain.Provider

import android.content.Context
import android.content.res.AssetManager
import com.example.betadiccompose.R
import com.example.betadiccompose.data.network.model.DataMenu
import com.example.betadiccompose.data.network.model.DataVocabulary
import com.example.betadiccompose.data.network.model.DataWorld
import java.io.InputStream

class Provider (val ass: AssetManager, val context: Context) {

    val prefs: Prefs = Prefs(context)

    fun createListMenu(): ArrayList<DataMenu> {

        val i1 = 0
        var lst: ArrayList<DataMenu> = ArrayList()
        val inputStream: InputStream = ass.open("Menu")

        val inputString = inputStream.reader().use { it.readText() }
        val data: List<String> = inputString.split('\n')

        for (i in 1 until data.size) {
            val opcion: String = data[i].split(";")[i1]
            val id = (i - 1)
            lst.add(DataMenu(Id = id, Menu_1 = opcion, ImgMenu = R.drawable.ic_launcher_foreground))
        }

        return lst
    }

    fun createlistvocabulary() {
        var lst: ArrayList<DataVocabulary> = ArrayList()

        val i1 = 2
        val i2 = 1
/*

        val inputStream: InputStream = ass.open("Categorias")
        val inputString = inputStream.reader().use { it.readText() }

        val data: List<String> = inputString.split('\n')

        for (i in 1 until data.size) {
            val fila1: String = data[i].split(";")[i1]
            val fila2: String = data[i].split(";")[i2]
            val id = i - 1

            lst.add(
                DataVocabulary(
                    id,
                    fila1,
                    fila2,
                    "https://d1i3grysbjja6f.cloudfront.net/IMG/Diccionario/$id.jpg"
                )
            )

        }

        return lst*/
    }

    fun makesubmenus(indice: Int): Int {

        var valor = 0
        val inputStream: InputStream = ass.open("PathMenu")
        val inputString = inputStream.reader().use { it.readText() }

        val data: List<String> = inputString.split('\n')

        for (i in 1 until data.size) {
            val ID = data[i].split(";")[0].trim().toInt()

            if (ID == indice) {

                var categoria = data[i].split(";")[1]
                val document = data[i].split(";")[4]
                val SubMenu: String = data[i].split(";")[2]

                /*
                prefs.SaveIndex(ID)
                prefs.SaveCategoty(categoria.trim())
                prefs.SaveDocument(document.trim())
                prefs.SaveSubMenu(SubMenu.trim().toInt())*/

            }

        }

        return valor
    }

    fun SaveIndex(dato: DataVocabulary){
        prefs.SaveIndex(dato.id)  //Category ID
        prefs.SaveNameCategory(dato.name)
        prefs.SavePath(dato.path)  //Direccion para aceder a todos los datos
        prefs.SaveDocument(dato.document) //Guarda el tipo de documento
        prefs.IsSaveSubMenu(dato.sub) //Determina si tiene un sub menu
    }

    fun createlistword(): ArrayList<DataWorld>{

        var lst:ArrayList<DataWorld> = ArrayList()
        val category = prefs.GetCategory().trim()

        val i0 = 2 //ID CATEGORY
        val i1 = 3 //ID MAIN LENGUAJE
        val i2 = 4 //ID SEGUND LENGUAJE

        val inputStream: InputStream =  ass.open(category)
        val inputString = inputStream.reader().use { it.readText() }
        val data: List<String> = inputString.split('\n')

        for (i in 1 until data.size) {

            val elemento:Int = data[i].split(";")[i0].toInt()
            val opcion_1: String = data[i].split(";")[i1]
            val opcion_2: String = data[i].split(";")[i2]

            var url = "https://d1i3grysbjja6f.cloudfront.net/IMG/$category/$elemento.jpg"

            lst.add(DataWorld(elemento,opcion_1,opcion_2,url))
        }
        return lst
    }

    fun getkindocument(): Int {
        return prefs.GetDocument()
    }

    fun getsubmenu():Boolean{
        return prefs.IsSubMenu()
    }
    fun getindex(): Int {
        return prefs.GetIndex()
    }

    fun getCategory(): String {
        return prefs.GetCategory()
    }

    fun CreateJson(){

        var lst: ArrayList<DataVocabulary> = ArrayList()

        val idir = 1
        val iname = 5
        val inicio = 2
        val ifin =3
        val istars = 11

        var nombre = ""
        var dir = ""
        var Iinicio = ""
        var fin = ""
        var stars = ""




        //Name
        val inputStream: InputStream = ass.open("todos")
        val inputString = inputStream.reader().use { it.readText() }
        val data: List<String> = inputString.split('\n')

        //Document and sub
        /*val inputStreamDocument: InputStream = ass.open("PathMenu")
        val inputStringDocument = inputStreamDocument.reader().use { it.readText() }
        val dataDoc: List<String> = inputStringDocument.split('\n')
        */

        var total = ""

        for (i in 1 until data.size) {


            if(i>1){
                total+=","
                print(",")
            }

            //name
            nombre = data[i].split(";")[iname].trim()
            dir = data[i].split(";")[idir].trim()
            Iinicio = data[i].split(";")[inicio].trim()
            fin = data[i].split(";")[ifin].trim()
            stars = data[i].split(";")[istars].trim()

            val DIR = "\"dir\":\"${dir}\","
            val NAME = "\"name\":\"$nombre\","
            val INICIO = "\"inicio\":${Iinicio},"
            val FIN = "\"fin\":${fin},"
            val STARS = "\"stars\":${stars}"


            var texto = ""

            if(Iinicio != "null"){
                texto = ("{" +
                        DIR +
                        NAME +
                        INICIO +
                        FIN +
                        STARS
                        + "}")
            }else{
                texto = ("{" +
                        DIR +
                        NAME +
                        STARS
                        + "}")
            }



            total += texto

        }

        println(total)







    }

    fun CreateWord(categoria: String, idioma: String,subv :Boolean) {

        val iid = 2
        val iname = idioma.trim().toInt()
        val isub = 1

        var nombre = ""
        var dir = 0
        var sub = 0
        var doc = 0


        //Name
        val inputStream: InputStream = ass.open("List/${categoria}")
        val inputString = inputStream.reader().use { it.readText() }
        val data: List<String> = inputString.split('\n')

        var total = ""


        if(!subv){

            for (i in 1 until data.size) {

                if(i>1){
                    total+=","
                }

                //name
                dir = data[i].split(";")[iid].trim().toInt()
                nombre = data[i].split(";")[iname]

                val ID = "\"id\":${dir},"
                val NAME = "\"name\":\"$nombre\""


                val texto = ("{" +
                        ID +
                        NAME +
                        "}")

                total += texto


            }
            println(total)

        }else{
            for (i in 1 until data.size) {

                if(i>1){
                    total+=","
                }

                //name
                dir = data[i].split(";")[iid].trim().toInt()
                sub = data[i].split(";")[isub].trim().toInt()
                nombre = data[i].split(";")[iname]

                val ID = "\"id\":${dir},"
                val NAME = "\"name\":\"$nombre\""
                val SUB = "\"subcat\":$sub,"


                val texto = ("{" +
                        ID +
                        SUB +
                        NAME+
                        "}")

                total += texto


            }
            println(total)
        }


    }

    fun createSub(){

        var lst: ArrayList<DataVocabulary> = ArrayList()

        val iid = 1
        val iname = 3
        val ipath = 0
        val idoc = 4

        var nombre = ""
        var id = 0
        var dir = ""
        var img = ""


        //Name
        val inputStream: InputStream = ass.open("sub")
        val inputString = inputStream.reader().use { it.readText() }
        val data: List<String> = inputString.split('\n')


        var total = ""

        for (i in 0 until data.size) {

            id = data[i].split(";")[iid].trim().toInt()
            nombre = data[i].split(";")[iname].trim()
            dir = data[i].split(";")[ipath].trim()
            img = data[i].split(";")[idoc].trim()



                val DIR = "\"dir\":\"$dir\","
                val ID = "\"id\":${id},"
                val NAME = "\"name\":\"$nombre\","
                val IMG = "\"doc\":\"$img\""

                val texto = ("{" +
                        DIR+
                        ID +
                        NAME +
                        IMG +
                "}")


            total += texto
            total+=","

            }



        println(total)


    }

    fun createNivels(){

        val idir = 0
        val iname = 1
        val idoc = 2

        var nombre = ""
        var dir = ""
        var doc = ""

        //Name
        val inputStream: InputStream = ass.open("niveles")
        val inputString = inputStream.reader().use { it.readText() }
        val data: List<String> = inputString.split('\n')


        var total = ""

        for (i in 1 until data.size) {

            if(i>1){
                total+=","
                print(",")
            }

            //name
            nombre = data[i].split(";")[iname]
            dir = data[i].split(";")[idir]
            doc = data[i].split(";")[idoc]

            val DIR = "\"dir\":\"${dir}\","
            val NAME = "\"name\":\"$nombre\","
            val DOC = "\"doc\":${doc}"


            val texto = ("{" +
                    DIR +
                    NAME +
                    "$DOC}")


            total += texto


        }

        println(total)
    }

}