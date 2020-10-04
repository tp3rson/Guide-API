package amerifrance.guideapi.gui;

import amerifrance.guideapi.api.Renderer;
import amerifrance.guideapi.utils.MouseHelper;
import net.minecraft.client.util.math.MatrixStack;

public class RenderGuideObject {

    private final Object object;
    private final Renderer renderer;
    private final int x;
    private final int y;

    public RenderGuideObject(Object object, Renderer renderer, int x, int y) {
        this.object = object;
        this.renderer = renderer;
        this.x = x;
        this.y = y;
    }

    public Object getObject() {
        return object;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void render(GuideGui guideGui, MatrixStack matrixStack, float delta) {
        renderer.render(guideGui, matrixStack, x, y, delta);
    }

    public void hover(GuideGui guideGui, MatrixStack matrixStack, int mouseX, int mouseY) {
        renderer.hover(guideGui, matrixStack, x, y, mouseX, mouseY);
    }

    public boolean isHovering(GuideGui guideGui, int mouseX, int mouseY) {
        return MouseHelper.isInArea(x, y, renderer.getArea(guideGui), mouseX, mouseY);
    }
}