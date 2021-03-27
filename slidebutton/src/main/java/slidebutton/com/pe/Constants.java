package slidebutton.com.pe;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Constants {

    public static float convertDpiToPx(Context context, float dpi) {
        /*return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpi,
                context.getResources().getDisplayMetrics()
        );*/
        return dpi * context.getResources().getDisplayMetrics().density;
    }

    public static float convertPxToDpi(Context context, float pixel) {
        return pixel / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
