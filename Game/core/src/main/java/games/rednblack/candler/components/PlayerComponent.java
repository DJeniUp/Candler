package games.rednblack.candler.components;

import com.artemis.PooledComponent;

public class PlayerComponent extends PooledComponent {

    public int timesOfLight;
    @Override
    protected void reset() {
        timesOfLight=0;
    }
}
