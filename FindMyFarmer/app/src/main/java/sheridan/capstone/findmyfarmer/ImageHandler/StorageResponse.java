package sheridan.capstone.findmyfarmer.ImageHandler;

import android.graphics.Bitmap;

import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.Optional;

public interface StorageResponse {
    void processFinish(List<StorageReference> response, Optional<Bitmap> bitmap, Optional<String> Url);
    void OnErrorListener(String error);
}
