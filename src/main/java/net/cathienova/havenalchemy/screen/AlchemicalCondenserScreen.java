package net.cathienova.havenalchemy.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AlchemicalCondenserScreen extends AbstractContainerScreen<AlchemicalCondenserMenu>
{
    private static final ResourceLocation BACKGROUND = new ResourceLocation(HavenAlchemy.MOD_ID, "textures/gui/alchemical_condenser.png");

    public AlchemicalCondenserScreen(AlchemicalCondenserMenu pMenu, Inventory pPlayerInventory, Component pTitle)
    {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init()
    {
        super.init();
        this.inventoryLabelY = 10000;
        this.inventoryLabelX = 10000;
        this.titleLabelY = 10000;
        this.titleLabelX = 10000;
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {
        super.renderLabels(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        int x = (width - 256) / 2;
        int y = (height - 234) / 2;

        guiGraphics.blit(BACKGROUND, x, y, 0, 0, 256, 234);

        long emc = menu.getStoredEMC();
        if (emc > menu.maxEMC) emc = menu.maxEMC;

        if (emc > 0) {
            int width = 107;

            if (emc > menu.getTargetEMC() || emc > menu.maxEMC) {
                width = 107;
            } else {
                double ratio = (double) emc / Math.min(menu.getTargetEMC(), menu.maxEMC);
                width = (int) Math.round(ratio * 107);

                if (!(menu.getTargetEMC() < menu.maxEMC && menu.slots.get(91).hasItem())) {
                    width = (int) Math.round((double) emc / menu.maxEMC * 107);
                }
            }

            guiGraphics.blit(BACKGROUND, x + 31, y + 7, 0, 240, width, 14);
        }
        guiGraphics.drawString(font, "" + String.format("%,d", emc), x + 140, y + 10, 65535);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}