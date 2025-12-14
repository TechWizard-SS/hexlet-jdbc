package patterns.composite;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var window = new Window(
                List.of(
                        new Button(),
                        new Button(),
                        new TextInput(),
                        new Block(
                                List.of(
                                        new Button()
                                )
                        )
                )
        );

        window.draw();
    }
}