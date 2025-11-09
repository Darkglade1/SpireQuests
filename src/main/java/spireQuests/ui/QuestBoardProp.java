package spireQuests.ui;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import spireQuests.util.TexLoader;

import java.util.ArrayList;

import static spireQuests.Anniv8Mod.makeUIPath;

public class QuestBoardProp {
    public static final float DRAW_X;
    public static final float DRAW_Y;
    public Hitbox hb;
    private ArrayList<AbstractCard> cards1;
    protected static final String questBoardPropImagePath = makeUIPath("bulletin_board.png");
    private Texture sprite;

    public QuestBoardProp() {
        this(0.0F, 0.0F, 1);
    }

    public QuestBoardProp(float x, float y, int newShopScreen) {
        this.cards1 = new ArrayList();
        this.sprite = TexLoader.getTexture(questBoardPropImagePath);
        this.hb = new Hitbox(sprite.getWidth() * Settings.xScale, sprite.getHeight() * Settings.yScale);
        this.hb.move(DRAW_X + ((float) sprite.getWidth() / 2) * Settings.scale, DRAW_Y + ((float) sprite.getHeight() / 2) * Settings.scale);
        //AbstractDungeon.shopScreen.init(this.cards1, this.cards2);
    }

    public void update() {
        this.hb.update();
        if ((this.hb.hovered && InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed()) && !AbstractDungeon.isScreenUp && !AbstractDungeon.isFadingOut && !AbstractDungeon.player.viewingRelics) {
            BaseMod.openCustomScreen(QuestBoardScreen.Enum.QUEST_BOARD);
            this.hb.hovered = false;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(sprite, DRAW_X, DRAW_Y, sprite.getWidth() * Settings.scale, sprite.getHeight() * Settings.scale);
        if (this.hb.hovered) {
            sb.setBlendFunction(770, 1);
            sb.setColor(Color.GOLD);
            sb.draw(sprite, DRAW_X, DRAW_Y, sprite.getWidth() * Settings.scale, sprite.getHeight() * Settings.scale);
            sb.setBlendFunction(770, 771);
        }
        this.hb.render(sb);
    }

    static {
        DRAW_X = (float)Settings.WIDTH * 0.5F - 300.0F * Settings.xScale;
        DRAW_Y = AbstractDungeon.floorY + 109.0F * Settings.scale;
    }
}
