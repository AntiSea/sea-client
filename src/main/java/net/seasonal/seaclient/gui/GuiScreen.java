package net.seasonal.seaclient.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class GuiScreen extends Screen {

    private int boxX, boxY; // Position of the draggable box
    private int boxWidth = 200, boxHeight = 150; // Size of the box
    private int dragOffsetX, dragOffsetY; // Offset when dragging the box
    private boolean isDragging = false;

    public GuiScreen() {
        super(Text.literal("SeaClient GUI"));
        // Default position for the box
        this.boxX = this.width / 2 - boxWidth / 2;
        this.boxY = this.height / 2 - boxHeight / 2;
    }

    @Override
    protected void init() {
        // Add button inside the draggable box
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Close"),
            button -> MinecraftClient.getInstance().setScreen(null) // Close the GUI
        ).dimensions(this.boxX + 50, this.boxY + boxHeight - 40, 100, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Draw the background and the draggable box
        this.renderBackground(context, mouseX, mouseY, delta);
        drawDraggableBox(context, mouseX, mouseY, delta);

        super.render(context, mouseX, mouseY, delta);
    }

    private void drawDraggableBox(DrawContext context, int mouseX, int mouseY, float delta) {
        // Draw the box
        context.fill(boxX, boxY, boxX + boxWidth, boxY + boxHeight, 0xFFAAAAAA); // Light gray background

        // Draw the tile (simple text for now)
        // Use the correct method to draw MutableText
        context.drawText(MinecraftClient.getInstance().textRenderer, Text.literal("Tile Above Box"), boxX + 60, boxY - 20, 0xFFFFFF, false); // Tile above the box

        // Draw the draggable box
        context.fill(boxX, boxY, boxX + boxWidth, boxY + boxHeight, 0xFFCCCCCC); // Light box background
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Check if the mouse is within the bounds of the draggable box
        if (mouseX >= boxX && mouseX <= boxX + boxWidth && mouseY >= boxY && mouseY <= boxY + 20) {
            // Start dragging
            isDragging = true;
            dragOffsetX = (int) (mouseX - boxX);
            dragOffsetY = (int) (mouseY - boxY);
            return true;
        }
        return false; // Return false to propagate the mouse press to other elements
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        // Stop dragging when mouse is released
        isDragging = false;
        return true; // Return true to indicate that the event has been handled
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (isDragging) {
            // Update box position based on mouse movement
            this.boxX = (int) (mouseX - dragOffsetX);
            this.boxY = (int) (mouseY - dragOffsetY);
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}
