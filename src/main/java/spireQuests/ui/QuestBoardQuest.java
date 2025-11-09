package spireQuests.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import spireQuests.quests.AbstractQuest;

public class QuestBoardQuest {
    public static final String ID = spireQuests.Anniv8Mod.makeID("QuestBoard");
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public AbstractQuest quest;
    private final float x;
    private final float y;

    public QuestBoardQuest(AbstractQuest quest, float x, float y) {
        this.quest = quest;
        this.x = x;
        this.y = y;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(ImageMaster.REWARD_SCREEN_SHEET, this.x, this.y - 350.0F, 306.0F, 358.0F, 512.0F, 716.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 612, 716, false, false);
        sb.draw(ImageMaster.VICTORY_BANNER, this.x + 25.0F, this.y + 299.0F, 556.0F, 119.0F, 612.0F, 238.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1112, 238, false, false);
        FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.quest.name, this.x + 185.0F * Settings.xScale, this.y + 330.0F * Settings.scale, Color.WHITE, 1.2f);
        sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, this.x + 5.0F, this.y - 550.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
        FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[1], this.x + 195.0F * Settings.xScale, this.y - 315.0F * Settings.yScale, Color.WHITE, 0.8F);
        FontHelper.renderFontLeft(sb, FontHelper.cardDescFont_N, quest.getRequirementsText(), this.x + 25.0F * Settings.xScale, this.y + 165.0F * Settings.yScale, Color.WHITE);
        FontHelper.renderFontLeft(sb, FontHelper.cardDescFont_N, quest.getRewardsText(), this.x + 25.0F * Settings.xScale, this.y - 60.0F * Settings.yScale, Color.WHITE);
    }

    public void update() {

    }
}
