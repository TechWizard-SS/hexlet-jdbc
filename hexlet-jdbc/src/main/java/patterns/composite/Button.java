package patterns.composite;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Button implements Component {
    public void draw() {
        System.out.println("draw button");
    }
}