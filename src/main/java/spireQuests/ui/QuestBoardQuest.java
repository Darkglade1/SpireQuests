package spireQuests.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import spireQuests.quests.AbstractQuest;
import spireQuests.quests.QuestManager;

public class QuestBoardQuest {
    public static final String ID = spireQuests.Anniv8Mod.makeID("QuestBoard");
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public AbstractQuest quest;
    private final float x;
    private final float y;
    private final Hitbox hb;
    public boolean taken;

    public QuestBoardQuest(AbstractQuest quest, float x, float y) {
        this.quest = quest;
        this.x = x;
        this.y = y;
        this.hb = new Hitbox(300.0F * Settings.scale, 64.0F * Settings.scale);
        this.hb.move(this.x + 5.0F * Settings.scale + (512.0F / 2) * Settings.scale, this.y - 445.0F * Settings.scale + (256.0F / 2) * Settings.scale);
    }

    public void render(SpriteBatch sb, float boardY) {
        if (!taken) {
            sb.setColor(Color.WHITE);
            sb.draw(ImageMaster.REWARD_SCREEN_SHEET, this.x, this.y - 350.0F * Settings.scale + boardY, 512.0F * Settings.scale, 716.0F * Settings.scale);
            sb.draw(ImageMaster.VICTORY_BANNER, this.x - 50.0F * Settings.scale, this.y + 199.0F * Settings.scale + boardY, 612.0F * Settings.scale, 238.0F * Settings.scale);
            FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.quest.name, this.x + 260.0F * Settings.scale, this.y + 340.0F * Settings.scale + boardY, Color.WHITE, 1.2f);
            sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, this.x + 5.0F * Settings.scale, this.y - 445.0F * Settings.scale + boardY, 512.0F * Settings.scale, 256.0F * Settings.scale);
            if (this.hb.hovered) {
                sb.setBlendFunction(770, 1);
                sb.setColor(Color.GOLD);
                sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, this.x + 5.0F * Settings.scale, this.y - 445.0F * Settings.scale + boardY, 512.0F * Settings.scale, 256.0F * Settings.scale);
                sb.setBlendFunction(770, 771);
            }
            FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[1], this.x + 260.0F * Settings.scale, this.y - 315.0F * Settings.scale + boardY, Color.WHITE, 0.8F);
            FontHelper.renderFontLeft(sb, FontHelper.cardDescFont_N, quest.getRequirementsText(), this.x + 55.0F * Settings.scale, this.y + 165.0F * Settings.scale + boardY, Color.WHITE);
            FontHelper.renderFontLeft(sb, FontHelper.cardDescFont_N, quest.getRewardsText(), this.x + 55.0F * Settings.scale, this.y - 60.0F * Settings.scale + boardY, Color.WHITE);
        }
    }

    public void update() {
        if (!taken) {
            this.hb.update();
            if (this.hb.justHovered) {
                CardCrawlGame.sound.playV("UI_HOVER", 0.75F);
            }
            if ((this.hb.hovered && InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed()) && !AbstractDungeon.isScreenUp && !AbstractDungeon.isFadingOut && !AbstractDungeon.player.viewingRelics) {
                CardCrawlGame.sound.play("SHOP_PURCHASE", 0.1F);
                QuestManager.startQuest(quest);
                QuestBoardScreen.parentProp.quests.remove(quest);
                taken = true;
                this.hb.hovered = false;
            }
        }
    }
}
