package patterns.composite;

import java.util.List;

public class BadWindow {
    List<Button> buttons;

    List<TextInput> textInputs;

    List<Block> blocks;

    public void draw() {
        buttons.forEach(Button::draw);
        textInputs.forEach(TextInput::draw);
        blocks.forEach(Block::draw);
    }
}
