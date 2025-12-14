package patterns.composite;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TextInput implements Component {

    public void draw() {
        System.out.println("draw text input");
    }
}
