package sharedObject;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {

    private static final RenderableHolder instance = new RenderableHolder();
    private List<IRenderable> entities;

    private final Comparator<IRenderable> comparator;

    //Images
    public static Image wallpaper;
    public static Image wallpaper2;
    public static Image bKing;
    public static Image wKing;
    public static Image bKnight;
    public static Image wKnight;
    public static Image bPawn;
    public static Image wPawn;
    public static Image bQueen;
    public static Image wQueen;
    public static Image bRook;
    public static Image wRook;
    public static Image wBishop;
    public static Image bBishop;

    //audio
    public static AudioClip captureChess;
    public static AudioClip captureFailed;
    public static AudioClip clickButton;
    public static AudioClip moveChess;

    static {
        loadResource();
    }

    public RenderableHolder() {
        entities = new ArrayList<IRenderable>();
        comparator = (IRenderable o1, IRenderable o2) -> {
            if (o1.getZ() > o2.getZ())
                return 1;
            return -1;
        };
    }

    public static RenderableHolder getInstance() {
        return instance;
    }

    public static void loadResource() {
        // Load Images
        wallpaper = new Image(ClassLoader.getSystemResource("wallpaper.jpg").toString());
        wallpaper2 = new Image(ClassLoader.getSystemResource("wallpaper2.jpg").toString());
        bKing = new Image(ClassLoader.getSystemResource("bKing.png").toString());
        wKing = new Image(ClassLoader.getSystemResource("wKing.png").toString());
        bKnight = new Image(ClassLoader.getSystemResource("bKnight.png").toString());
        wKnight = new Image(ClassLoader.getSystemResource("wKnight.png").toString());
        bPawn = new Image(ClassLoader.getSystemResource("bPawn.png").toString());
        wPawn = new Image(ClassLoader.getSystemResource("wPawn.png").toString());
        bQueen = new Image(ClassLoader.getSystemResource("bQueen.png").toString());
        wQueen = new Image(ClassLoader.getSystemResource("wQueen.png").toString());
        bRook = new Image(ClassLoader.getSystemResource("bRook.png").toString());
        wRook = new Image(ClassLoader.getSystemResource("wRook.png").toString());
        bBishop = new Image(ClassLoader.getSystemResource("bBishop.png").toString());
        wBishop = new Image(ClassLoader.getSystemResource("wBishop.png").toString());

        // Load Audio Clips
        captureChess = new AudioClip(ClassLoader.getSystemResource("captureChess.wav").toString());
        captureFailed = new AudioClip(ClassLoader.getSystemResource("captureFailed.wav").toString());
        clickButton = new AudioClip(ClassLoader.getSystemResource("clickButton.wav").toString());
        moveChess = new AudioClip(ClassLoader.getSystemResource("moveChess.wav").toString());
    }

    public void clear() {
        entities = new ArrayList<IRenderable>();
    }

    public void add(IRenderable entity) {
        entities.add(entity);
        Collections.sort(entities, comparator);
    }

    public void update() {
        Collections.sort(entities, comparator);
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).isDestroyed() || !entities.get(i).isVisible())
                entities.remove(i);
        }
    }

    public List<IRenderable> getEntities() {
        return entities;
    }

}
