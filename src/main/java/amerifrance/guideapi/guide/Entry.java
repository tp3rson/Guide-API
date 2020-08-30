package amerifrance.guideapi.guide;

import amerifrance.guideapi.api.*;
import amerifrance.guideapi.displays.Display;
import amerifrance.guideapi.renderers.Renderer;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.google.common.collect.Maps.newLinkedHashMap;

public class Entry implements IdProvider, TextProvider, ChildOf<Category>, ParentOf<Element>, DisplayProvider, RendererProvider<Entry> {

    private String id;
    private String name;
    private Category category;
    private Display display;
    private Renderer<Entry> renderer;
    private Map<String, Element> elements;

    public Entry(String id, String name, Category category, Renderer<Entry> renderer, Consumer<Entry> $) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.renderer = renderer;
        this.elements = newLinkedHashMap();

        $.accept(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return name;
    }

    @Override
    public Category getParent() {
        return category;
    }

    @Override
    public List<Element> getChildren() {
        return Lists.newArrayList(elements.values());
    }

    @Override
    public void add(Element child) {
        elements.put(child.getId(), child);
    }

    @Override
    public Element getChild(String id) {
        return elements.getOrDefault(id, null);
    }

    @Override
    public Renderer<Entry> getRenderer() {
        return renderer;
    }

    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    public void setDisplay(Display display) {
        this.display = display;
    }

    @Override
    public ViewingRequirement getViewingRequirement() {
        return () -> true;
    }
}