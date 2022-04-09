package com.asdvek.MinecraftASKimble.ui.popomatic;

import com.asdvek.MinecraftASKimble.logic.KimbleGame;
import com.asdvek.MinecraftASKimble.math.Vec3;
import com.asdvek.MinecraftASKimble.ui.Drawable;
import org.apache.commons.lang.NotImplementedException;

/** View responsible for rendering the whole popomatic
 *  Popomatic is radially symmetric so the origin point is at its center.
 */
public class PopomaticView implements Drawable {
    private final KimbleGame gameState;

    // child views
    private final DomeView domeView;
    private final DiceView diceView;

    public PopomaticView(Vec3 popomaticOrigin, KimbleGame gameState) {
        this.gameState = gameState;
        this.domeView = new DomeView(popomaticOrigin);
        this.diceView = new DiceView(popomaticOrigin, this.gameState);
    };

    @Override
    public void draw() {
        this.domeView.draw();
        this.diceView.draw();
    }

    @Override
    public void clean() {
        throw new NotImplementedException("TODO: Implement PopomaticView.clean()");
    }
}
