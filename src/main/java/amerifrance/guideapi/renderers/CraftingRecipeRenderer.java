package amerifrance.guideapi.renderers;

import amerifrance.guideapi.gui.GuideGui;
import amerifrance.guideapi.utils.Area;
import amerifrance.guideapi.utils.RecipePair;
import amerifrance.guideapi.utils.RenderStack;
import com.google.common.collect.Lists;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

import java.util.List;

public class CraftingRecipeRenderer<T> extends RecipeRenderer<T> {

    private static final Identifier TEXTURE = new Identifier("textures/gui/container/crafting_table.png");

    private final Item output;
    private List<RecipePair> recipePairs;

    public CraftingRecipeRenderer(Item output) {
        this.output = output;
    }

    @Override
    public void init(T object, GuideGui guideGui, int x, int y) {
        recipePairs = Lists.newArrayList();

        for (Recipe recipe : getRecipes(RecipeType.CRAFTING, output)) {
            List<RenderStack> recipeIngredients = Lists.newArrayList();
            DefaultedList<Ingredient> previewInputs = recipe.getPreviewInputs();

            for (int i = 0; i < previewInputs.size(); i++) {
                int xPos = x + (i % 3) * RenderStack.DRAW_SIZE;
                int yPos = y + (i / 3) * RenderStack.DRAW_SIZE;

                if (recipe instanceof ShapedRecipe)
                    xPos = x + (i % ((ShapedRecipe) recipe).getWidth()) * RenderStack.DRAW_SIZE;

                if (recipe instanceof ShapedRecipe && previewInputs.size() > 3)
                    yPos = y + (i / ((ShapedRecipe) recipe).getHeight()) * RenderStack.DRAW_SIZE;

                ItemStack[] matchingStacks = previewInputs.get(i).getMatchingStacksClient();
                if (matchingStacks.length > 0) {
                    recipeIngredients.add(new RenderStack(matchingStacks, xPos, yPos));
                } else {
                    recipeIngredients.add(new RenderStack(ItemStack.EMPTY, xPos, yPos));
                }
            }

            int outputX = x + 4 * RenderStack.DRAW_SIZE;
            int outputY = y + 1 * RenderStack.DRAW_SIZE;
            RenderStack outputStack = new RenderStack(recipe.getOutput(), outputX, outputY);

            recipePairs.add(new RecipePair(recipeIngredients, outputStack));
        }
    }

    @Override
    public Area getArea(T object, GuideGui guideGui) {
        return new Area(RenderStack.DRAW_SIZE * 5, RenderStack.DRAW_SIZE * 3);
    }

    @Override
    public RecipePair getRecipePairToDraw() {
        if (recipePairs.size() > 1) {
            int time = (int) (System.currentTimeMillis() / 1000);
            return recipePairs.get(time % recipePairs.size());
        }

        return recipePairs.get(0);
    }
}
