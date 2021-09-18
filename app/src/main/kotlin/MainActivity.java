import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dev.androidbroadcast.devto.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources resources = getResources();
        resources.getString(R.string.app_name);
        resources.getAnimation(androidx.appcompat.R.anim.abc_fade_in);
        resources.getColor(androidx.core.R.color.androidx_core_ripple_material_light);
    }
}
