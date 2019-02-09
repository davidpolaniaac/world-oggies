package com.app.mundo.oggies;

import java.util.ArrayList;

/**
 * Created by PC on 12/05/2016.
 */
public class BedRoomBoy extends Room {



    public BedRoomBoy() {

        super();

        window.add(R.drawable.ventana_nino1);
        window.add(R.drawable.ventana_nino2);
        window.add(R.drawable.ventana_nino3);
        window.add(R.drawable.ventana_nino4);



        closer.add(R.drawable.closet_nino_frontal1);
        closer.add(R.drawable.closet_nino_frontal2);
        closer.add(R.drawable.closet_nino_frontal1);
        closer.add(R.drawable.closet_nino_frontal2);
        ;




        trunk.add(R.drawable.baul_nino1);
        trunk.add(R.drawable.baul_nino2);
        trunk.add(R.drawable.baul_nino3);
        trunk.add(R.drawable.baul_nino4);

        bed.add(R.drawable.cama_nino1);
        bed.add(R.drawable.cama_nino2);
        bed.add(R.drawable.cama_nino3);
        bed.add(R.drawable.cama_nino4);

    }

}
