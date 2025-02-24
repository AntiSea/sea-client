package net.seasonal.seaclient.modules;

public abstract class Module {
    private String name;
    private String description;
    private Category category;
    private boolean enabled;

    // Constructor with name, description, and category
    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false; // Default to disabled
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void toggle() {
        this.enabled = !this.enabled;  // Toggle the module's enabled state
        if (enabled) {
            onEnable();  // Call onEnable if the module is enabled
        } else {
            onDisable();  // Call onDisable if the module is disabled
        }
    }

    public void onEnable() {
        // Code to enable the module
    }

    public void onDisable() {
        // Code to disable the module
    }

    // Abstract tick method to be implemented by subclasses
    public abstract void tick();
}
