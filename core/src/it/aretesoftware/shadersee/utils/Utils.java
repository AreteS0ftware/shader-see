package it.aretesoftware.shadersee.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.util.nfd.NativeFileDialog;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Utils {

    public static FileHandle browseFile(String... formats) {
        String filterList = getFilterList(formats);
        String defaultPath = Gdx.files.absolute(System.getProperty("user.home")).path();
        ByteBuffer buffer = ByteBuffer.allocateDirect(256);
        PointerBuffer outPath = PointerBuffer.create(buffer);

        NativeFileDialog.NFD_SaveDialog(filterList, defaultPath, outPath);
        long address = outPath.get();
        String filePath = address > 0 ? MemoryUtil.memUTF8(address) : null;
        NativeFileDialog.NFD_Free(buffer);

        if (filePath != null) {
            return Gdx.files.absolute(filePath);
        }
        else {
            return null;
        }
    }

    public static FileHandle saveFile(String fileContents, String... formats) {
        FileHandle fileHandle = browseFile(formats);
        if (fileHandle != null) {
            fileHandle.writeString(fileContents, false);
        }
        return fileHandle;
    }

    public static FileHandle openFile(String... formats) {
        String filterList = getFilterList(formats);
        String defaultPath = Gdx.files.absolute(System.getProperty("user.home")).path();
        ByteBuffer buffer = ByteBuffer.allocateDirect(256);
        PointerBuffer outPath = PointerBuffer.create(buffer);

        NativeFileDialog.NFD_OpenDialog(filterList, defaultPath, outPath);
        long address = outPath.get();
        String filePath = address > 0 ? MemoryUtil.memUTF8(address) : null;
        NativeFileDialog.NFD_Free(buffer);
        return filePath != null ? Gdx.files.absolute(filePath) : null;
    }

    public static void desktopOpenFile(FileHandle fileLocation) {
        new Thread(() -> {
            try {
                Desktop.getDesktop().open(fileLocation.file());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static String getFilterList(String... formats) {
        StringBuilder builder = new StringBuilder();
        for (String format : formats) {
            builder.append(format).append(";");
        }
        if (builder.length() > 0) builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    //

    public static Vector2 snap(Vector2 grid, Vector2 position) {
        float width = grid.x;
        float height = grid.y;
        int xRatio = Math.round(position.x / width);
        int yRatio = Math.round(position.y / height);
        position.set(width * xRatio, height * yRatio);
        return position;
    }

}
