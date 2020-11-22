package com.example.friendsr

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.friendsr.InfUsers.Companion.FRIEND_EXTRA
import com.example.friendsr.databinding.ActivityListaAmigosBinding
import kotlinx.android.synthetic.main.activity_lista_amigos.*

class ListaAmigos : AppCompatActivity(), Adapter.PeopleListener {
    private lateinit var binding: ActivityListaAmigosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaAmigosBinding.inflate(layoutInflater)
        //val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val friends = mutableListOf<ViewList>(
            ViewList(
                "Angelina Jolie",
                "Angelina Jolie Voight (Los Ángeles, California; 4 de junio de 1975) es una actriz, modelo, filántropa, actriz de voz, directora, guionista, productora y activista por los derechos de las mujeres, estadounidense. ",
                R.drawable.angelina,
                0F
            )
        )
        friends.add(
            ViewList(
                    "Brad Pitt",
                    "William Bradley Pitt (Shawnee, Oklahoma; 18 de diciembre de 1963), conocido como Brad Pitt, es un actor y productor de cine estadounidense. Además, por su trabajo interpretativo, ha sido nominado en cuatro ocasiones a los Premios Óscar (ganando dicho premio como productor en 2013 y como mejor actor de reparto en 2020) y en cuatro a los Premios Globo de Oro (ganó como mejor actor de reparto en 1995 y 2020).",
                    R.drawable.brad,
                    0F
                )
            )
        friends.add(
            ViewList(
                    "Cristiano Ronaldo",
                    "Cristiano Ronaldo dos Santos Aveiro, (Funchal, Madeira; 5 de febrero de 1985), más conocido como Cristiano Ronaldo, es un futbolista portugués que juega como delantero en la Juventus F. C. de la Serie A de Italia y en la selección de Portugal, de la cual es su capitán.",
                    R.drawable.cristiano,
                    0F
                )
            )
        friends.add(
            ViewList(
                    "Mr. Bean",
                    " es un personaje creado e interpretado por el actor británico Rowan Atkinson, que ha protagonizado una serie de televisión homónima, una serie animada y dos largometrajes: Bean (1997) y Las vacaciones de Mr. Bean (2007).",
                    R.drawable.mrbean,
                    0F
                )
            )
        friends.add(
            ViewList(
                    "Scarlett Johansson",
                    "Scarlett Ingrid Johansson (Nueva York; 22 de noviembre de 1984) es una actriz, cantante y modelo estadounidense. Comenzó a mostrar intereses por la música y la actuación desde temprana edad, y a lo largo de su infancia y adolescencia se formó en distintos institutos como actriz.",
                    R.drawable.scarlett,
                    0F
                )
            )
        friends.add(
            ViewList(
                    "Shakira Mebarak",
                    "Shakira Isabel Mebarak Ripoll (Barranquilla, Colombia; 2 de febrero de 1977), conocida artísticamente como Shakira, es una cantautora, productora discográfica, actriz, bailarina, empresaria, embajadora de buena voluntad de UNICEF y filántropa colombiana.",
                    R.drawable.shakira,
                    0F
                )
            )

        val adapter = Adapter(friends)
        adapter.setOnFriendListener(this)
        binding.lista.adapter = adapter
    }

    override fun onFriendClick(friend: ViewList) {
        super.onFriendClick(friend)
        val intent = Intent(this, InfUsers::class.java)
        intent.putExtra(FRIEND_EXTRA, friend)
        startActivityForResult(intent, InfUsers.DETAIL_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == InfUsers.DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                if (it.hasExtra(FRIEND_EXTRA)) {
                    val updateFriend = it.getParcelableExtra(FRIEND_EXTRA) as ViewList
                    updateFriendAtRecycler(updateFriend)
                    Toast.makeText(
                        applicationContext,
                        "Se actualizo a ${updateFriend.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun updateFriendAtRecycler(updateFriend: ViewList) {
        (lista.adapter as Adapter).updatePerson(friend = updateFriend)
    }
}

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }*/
//}
