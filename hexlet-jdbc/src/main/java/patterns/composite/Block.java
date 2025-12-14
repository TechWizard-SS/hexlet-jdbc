package patterns.composite;

import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class Block implements Component{
    private List<Component> childComponents;

    public void draw () {
        System.out.println("Block draw");
        childComponents.forEach(Component::draw);
    }
}
