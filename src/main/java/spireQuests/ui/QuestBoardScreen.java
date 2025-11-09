package spireQuests.ui;

import basemod.abstracts.CustomScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import spireQuests.util.TexLoader;

import static spireQuests.Anniv8Mod.makeUIPath;

public class QuestBoardScreen extends CustomScreen {
    public static final String ID = spireQuests.Anniv8Mod.makeID("QuestBoard");
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    //protected static NitoriStoreTools.SpinningCardItems cards;
    protected static final String questBoardImagePath = makeUIPath("quest_board.png");
    protected static Texture questBoardImg;

    public static class Enum {
        @SpireEnum
        public static AbstractDungeon.CurrentScreen QUEST_BOARD;
    }

    public QuestBoardScreen() {
        questBoardImg = TexLoader.getTexture(questBoardImagePath);
    }

    @Override
    public AbstractDungeon.CurrentScreen curScreen() {
        return Enum.QUEST_BOARD;
    }

    private void open() {
        reopen();
    }

    @Override
    public void reopen() {
        //cards = new NitoriStoreTools.SpinningCardItems();

        AbstractDungeon.player.releaseCard();
        AbstractDungeon.screen = Enum.QUEST_BOARD;
        AbstractDungeon.overlayMenu.showBlackScreen();
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.cancelButton.show(TEXT[0]);

        if (MathUtils.randomBoolean()) { CardCrawlGame.sound.play("MAP_OPEN", 0.1f);
        } else { CardCrawlGame.sound.play("MAP_OPEN_2", 0.1f); }
    }

    @Override
    public void openingSettings() {
    }

    @Override
    public void close() {
        genericScreenOverlayReset();
        AbstractDungeon.overlayMenu.cancelButton.hide();
        if (MathUtils.randomBoolean()) {
            CardCrawlGame.sound.play("MAP_OPEN", 0.1f);
        } else {
            CardCrawlGame.sound.play("MAP_OPEN_2", 0.1f);
        }
    }

    @Override
    public void update() {
        //cards.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(questBoardImg, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
        //cards.render(sb);
    }

    @Override
    public boolean allowOpenDeck() {
        return true;
    }

    @Override
    public boolean allowOpenMap() {
        return true;
    }

    public void init() {

    }
}