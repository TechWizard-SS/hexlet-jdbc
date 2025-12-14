package patterns.facade;

public class Client {
    private HostingImageLoader loader;

    public void loadImage(String filePath) {
        loader.loadImage(filePath);
    }
}