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

    public void render(SpriteBatch sb, float boardY) {
        sb.setColor(Color.WHITE);
        sb.draw(ImageMaster.REWARD_SCREEN_SHEET, this.x, this.y - 350.0F * Settings.scale + boardY, 512.0F * Settings.scale, 716.0F * Settings.scale);
        sb.draw(ImageMaster.VICTORY_BANNER, this.x - 50.0F * Settings.scale, this.y + 199.0F * Settings.scale + boardY, 612.0F * Settings.scale, 238.0F * Settings.scale);
        FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.quest.name, this.x + 260.0F * Settings.scale, this.y + 340.0F * Settings.scale + boardY, Color.WHITE, 1.2f);
        sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, this.x + 5.0F, this.y - 445.0F * Settings.scale + boardY, 512.0F * Settings.scale, 256.0F * Settings.scale);
        FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[1], this.x + 260.0F * Settings.scale, this.y - 315.0F * Settings.scale + boardY, Color.WHITE, 0.8F);
        FontHelper.renderFontLeft(sb, FontHelper.cardDescFont_N, quest.getRequirementsText(), this.x + 55.0F * Settings.scale, this.y + 165.0F * Settings.scale + boardY, Color.WHITE);
        FontHelper.renderFontLeft(sb, FontHelper.cardDescFont_N, quest.getRewardsText(), this.x + 55.0F * Settings.scale, this.y - 60.0F * Settings.scale + boardY, Color.WHITE);
    }

    public void update() {

    }
}
