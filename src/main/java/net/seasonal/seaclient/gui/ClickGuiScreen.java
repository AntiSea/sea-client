package net.seasonal.seaclient.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;  // Import the DrawContext class
import net.minecraft.text.Text;
import net.seasonal.seaclient.modules.Module;
import net.seasonal.seaclient.modules.ModuleManager;

public class ClickGuiScreen extends Screen {

    public ClickGuiScreen() {
        super(Text.literal("SeaClient GUI"));
    }

    @Override
    protected void init() {
        // Initialize buttons for each module
        int yOffset = this.height / 2 - 80;  // Start button y-position
        for (Module module : ModuleManager.getModules()) {
            this.addDrawableChild(createModuleButton(module, yOffset));
            yOffset += 30;  // Space out the buttons vertically
        }

        // Add a button to close the GUI
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Close GUI"), 
            button -> MinecraftClient.getInstance().setScreen(null) // Close GUI
        ).dimensions(this.width / 2 - 50, this.height / 2 + 50, 100, 20).build());
    }

    // Helper method to create a button for each module
    private ButtonWidget createModuleButton(Module module, int yOffset) {
        return ButtonWidget.builder(
            Text.literal(module.getName() + " - " + (module.isEnabled() ? "Enabled" : "Disabled")),
            button -> {
                module.toggle(); // Toggle the module
                button.setMessage(Text.literal(module.getName() + " - " + (module.isEnabled() ? "Enabled" : "Disabled"))); // Update button text
            }
        ).dimensions(this.width / 2 - 50, yOffset, 100, 20).build();
    }

    // Corrected render method
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.renderBackground(context, mouseX, mouseY, delta);  // Corrected method call
    }
}
