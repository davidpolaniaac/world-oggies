package com.app.mundo.oggies;

import java.util.ArrayList;

/**
 * Created by PC on 12/05/2016.
 */
public class BedRoomGril extends Room{

    public BedRoomGril() {

        super();

        window.add(R.drawable.ventana_nina1);
        window.add(R.drawable.ventana_nina2);
        window.add(R.drawable.ventana_nina3);
        window.add(R.drawable.ventana_nina4);

        closer.add(R.drawable.closet_nina_frontal1);
        closer.add(R.drawable.closet_nina_frontal2);
        closer.add(R.drawable.closet_nina_frontal3);
        closer.add(R.drawable.closet_nina_frontal4);


        trunk.add(R.drawable.baul_nina1);
        trunk.add(R.drawable.baul_nina2);
        trunk.add(R.drawable.baul_nina3);
        trunk.add(R.drawable.baul_nina4);

        bed.add(R.drawable.cama_nina1);
        bed.add(R.drawable.cama_nina2);
        bed.add(R.drawable.cama_nina3);
        bed.add(R.drawable.cama_nina4);

    }

}
