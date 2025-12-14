package patterns.composite;


import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Window implements Component {
    List<Component> childComponents;

    public void draw() {
        System.out.println("drawWindow");
        childComponents.forEach(Component::draw);
    }
}
