package com.app.mundo.oggies;

import java.util.ArrayList;

/**
 * Created by PC on 12/05/2016.
 */
public class Girl extends Personaje {

    public Girl() {
        super();
        if(Jugador.gender==1){
            Jugador.creado=false;
            Jugador.marcosDesbloqueados=2;
            Jugador.gender=0;
            Jugador.head=0;
            Jugador.headRecurso=0;
            Jugador.body=0;
            Jugador.accessory=0;
            Jugador.estacion=0;
            Jugador.window=0;
            Jugador.closet=0;
            Jugador.trunk=0;
            Jugador.bed=0;
        }
        Jugador.gender=2;
        RoomEdit.bedroom= new BedRoomGril();
        base=R.drawable.nina_base_todo;

        //

        winter.getHead().add(R.drawable.nina_cabeza_todos1);
        winter.getHead().add(R.drawable.nina_cabeza_todos2);

        winter.getBody().add(R.drawable.nina_cuerpo_invierno1);
        winter.getBody().add(R.drawable.nina_cuerpo_invierno2);

        winter.getAccessory().add(R.drawable.objeto_ambos_1);
        winter.getAccessory().add(R.drawable.objeto_ambos_2);
        winter.getAccessory().add(R.drawable.objeto_ambos_3);

        //

        summer.getHead().add(R.drawable.nina_cabeza_verano1);
        summer.getHead().add(R.drawable.nina_cabeza_verano2);


        summer.getBody().add(R.drawable.nina_cuerpo_verano1);
        summer.getBody().add(R.drawable.nina_cuerpo_verano2);
        summer.getBody().add(R.drawable.nina_cuerpo_verano3);
        summer.getBody().add(R.drawable.nina_cuerpo_verano4);

        summer.getAccessory().add(R.drawable.objeto_ambos_verano1);

        spring.getHead().add(R.drawable.nina_cabeza_todos1);
        spring.getHead().add(R.drawable.nina_cabeza_todos2);


        spring.getBody().add(R.drawable.nina_cuerpo_primavera1);
        spring.getBody().add(R.drawable.nina_cuerpo_primavera2);

        spring.getAccessory().add(R.drawable.objeto_ambos_1);
        spring.getAccessory().add(R.drawable.objeto_ambos_2);
        spring.getAccessory().add(R.drawable.objeto_ambos_3);

        autumn.getHead().add(R.drawable.nina_cabeza_todos1);
        autumn.getHead().add(R.drawable.nina_cabeza_todos2);


        autumn.getBody().add(R.drawable.nina_cuerpo_otono1);
        autumn.getBody().add(R.drawable.nina_cuerpo_otono2);

        autumn.getAccessory().add(R.drawable.objeto_ambos_1);
        autumn.getAccessory().add(R.drawable.objeto_ambos_2);
        autumn.getAccessory().add(R.drawable.objeto_ambos_3);

    }
}
