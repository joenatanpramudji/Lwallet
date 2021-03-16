package lsever;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FireBaseService2 {
	FirebaseDatabase db;

    public FireBaseService2() throws IOException {
        File file = new File(
                getClass().getClassLoader().getResource("l-wallet-d9b2e-firebase-adminsdk-eoj4d-b6d0100a2f.json").getFile()
        );

        FileInputStream fis = new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(fis)).setDatabaseUrl("https://l-wallet-d9b2e-default-rtdb.firebaseio.com").build();

        FirebaseApp.initializeApp(options);

        db = FirebaseDatabase.getInstance();
    }

    public FirebaseDatabase getDb() {
        return db;
    }

}
