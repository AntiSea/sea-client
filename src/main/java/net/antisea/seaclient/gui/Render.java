package net.antisea.seaclient.gui;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Render {
    private static final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private static final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
    private static boolean initialized = false;
    private static boolean showGui = false;
    private static final Logger LOG = LoggerFactory.getLogger(Render.class);

    public static void init(long windowHandle) {
        if (initialized) return;

        // Initialize ImGui
        ImGui.createContext();
        ImGuiIO io = ImGui.getIO();
        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard); // Enable Keyboard Controls
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable); // Enable Docking
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable); // Enable Multi-Viewport / Platform Windows

        imGuiGlfw.init(windowHandle, true);
        imGuiGl3.init("#version 150");

        initialized = true;
    }

    public static void render() {
        if (!initialized || !showGui) return;

        // Start the ImGui frame
        imGuiGlfw.newFrame();
        ImGui.newFrame();

        // Render your GUI
        ImGui.setNextWindowSize(ImGui.getIO().getDisplaySizeX(), ImGui.getIO().getDisplaySizeY());
        ImGui.setNextWindowPos(0, 0);
        ImGui.setNextWindowBgAlpha(0.5f); // Set window background transparency
        ImGui.begin("Sea Client GUI", ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoBackground);

        ImGui.text("This is a simple example of ImGui integration.");
        if (ImGui.button("Log Info")) {
            LOG.info("Button clicked: Log Info");
        }
        if (ImGui.button("Log Warning")) {
            LOG.warn("Button clicked: Log Warning");
        }
        if (ImGui.button("Log Error")) {
            LOG.error("Button clicked: Log Error");
        }

        ImGui.end();

        // Render ImGui
        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());

        // Update and Render additional Platform Windows
        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = GLFW.glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            GLFW.glfwMakeContextCurrent(backupWindowPtr);
        }
    }

    public static void toggleGui() {
        showGui = !showGui;
        if (showGui) {
            GLFW.glfwSetInputMode(GLFW.glfwGetCurrentContext(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        } else {
            GLFW.glfwSetInputMode(GLFW.glfwGetCurrentContext(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
        }
    }

    public static void dispose() {
        if (!initialized) return;

        imGuiGl3.dispose();
        imGuiGlfw.dispose();
        ImGui.destroyContext();

        initialized = false;
    }
}