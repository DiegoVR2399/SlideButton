package slidebutton.com.pe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.cardview.widget.CardView;

public class SlideButton extends RelativeLayout implements View.OnTouchListener {
    private int count = 200;
    private Context context;
    private static int id = 1234;
    private static int idImgDesplasing = 1334;
    private static int idCarView = 1334;
    private static int idCarViewEnd = 1234;
    private static int idCarViewStart = 12345;
    private final int DEFAULT_TEXT_COLOR = Color.BLACK;
    private final int DEFAULT_IMAGE_TINT_COLOR = Color.WHITE;
    private final int DEFAULT_IMAGE_TINT_COLOR_TRANPARENT = Color.TRANSPARENT;
    private final int DEFAULT_CARD_BACKGROUND_COLOR = Color.GREEN;
    private final int DEFAULT_TEXT_SIZE = -1;

    private int backgroundTintCardEnd = 0;
    private int backgroundTintCardDesplasing = 0;
    private int textColor = DEFAULT_TEXT_COLOR;
    private float textSize = DEFAULT_TEXT_SIZE;
    private String text = "";

    private boolean isChecked = false;
    private boolean isLockedScrolling = false;
    private @DrawableRes
    int imgResDesplasing = 0;
    private @DrawableRes
    int imgResEnd = 0;
    private @DrawableRes
    int background_custom_slide_on = 0;
    int background_custom_slide_off = 0;
    private float paddingIconDesplasing = 0;
    private float paddingIconEnd = 0;
    private int tint = 0;
    private int tintEnd = 0;
    //    private float margin = 0;
    private float width_height = 0;
    private float radius = 0;
//    private boolean tint_visible=true;

    //widgets
    private TextView textView;
    private ImageView imageViewCardview;
    private ImageView imageViewEnd;
    private ImageView imageViewStart;
    private CardView cardView;
    private CardView cardViewEnd;
    private CardView cardViewStart;
    private RelativeLayout relativeLayout;
//    private RelativeLayout relativeLayoutChild;

    private int BTN_AVAILABILITY_WIDTH;
    private int _xDelta;
    private setListenerSlideButton listener;


    public SlideButton(Context context) {
        super(context);
        if (!isInEditMode())
            init(context);

    }

    public SlideButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public SlideButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlideButton, defStyleAttr, 0);
            int margin_default = (int) Constants.convertDpiToPx(context, 3);
            int DEFAULT_WIDTH_HEIGHT = (int) Constants.convertDpiToPx(context, 34);
            int DEFAULT_BORDER = (int) Constants.convertDpiToPx(context, 17);

            text = a.getString(R.styleable.SlideButton_sb_text);
            textColor = a.getColor(R.styleable.SlideButton_sb_textColor, DEFAULT_TEXT_COLOR);
            textSize = a.getDimension(R.styleable.SlideButton_sb_textSize, DEFAULT_TEXT_SIZE);
            imgResDesplasing = a.getResourceId(R.styleable.SlideButton_sb_src_desplasing, 0);
            imgResEnd = a.getResourceId(R.styleable.SlideButton_sb_src_end, 0);
            background_custom_slide_on = a.getResourceId(R.styleable.SlideButton_sb_background_custom_slide_on, R.drawable.background_custom_slide_on);
            background_custom_slide_off = a.getResourceId(R.styleable.SlideButton_sb_background_custom_slide_off, R.drawable.background_custom_slide_off);
            paddingIconDesplasing = a.getDimension(R.styleable.SlideButton_sb_padding_desplasing, 0);
            paddingIconEnd = a.getDimension(R.styleable.SlideButton_sb_padding_end, 0);
            radius = a.getDimension(R.styleable.SlideButton_sb_radius_card, DEFAULT_BORDER);
            width_height = a.getDimension(R.styleable.SlideButton_sb_width_height_card, DEFAULT_WIDTH_HEIGHT);
            tint = a.getColor(R.styleable.SlideButton_sb_tint_icon_desplasing, DEFAULT_IMAGE_TINT_COLOR);
            tintEnd = a.getColor(R.styleable.SlideButton_sb_tint_icon_end, DEFAULT_IMAGE_TINT_COLOR);
            backgroundTintCardEnd = a.getColor(R.styleable.SlideButton_sb_background_tint_end, DEFAULT_IMAGE_TINT_COLOR_TRANPARENT);
            backgroundTintCardDesplasing = a.getColor(R.styleable.SlideButton_sb_background_tint_desplasing, DEFAULT_CARD_BACKGROUND_COLOR);
//        tint_visible = a.getBoolean(R.styleable.SlideButton_sb_tint_visible,true);

            a.recycle();
            init(context);
        }
    }

    private void init(Context context) {
        this.context = context;
        int margin_default = (int) Constants.convertDpiToPx(context, 3);

        RelativeLayout.LayoutParams layoutParamsRelative = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsRelative.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);

        relativeLayout = new RelativeLayout(context);
        relativeLayout.setBackground(context.getResources().getDrawable(background_custom_slide_off));
        relativeLayout.setPadding(margin_default, margin_default, margin_default, margin_default);
        relativeLayout.setLayoutParams(layoutParamsRelative);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
        BTN_AVAILABILITY_WIDTH = layoutParams.width;
        relativeLayout.post(new Runnable() {
            @Override
            public void run() {
                BTN_AVAILABILITY_WIDTH = relativeLayout.getWidth();
            }
        });


        initTextView(context);
        initCardView(context);
        initImageViewEnd(context);
        initImageViewStart(context);

        relativeLayout.addView(textView);
        relativeLayout.addView(cardView);
        relativeLayout.addView(cardViewEnd);
        relativeLayout.addView(cardViewStart);
//        relativeLayout.addView(relativeLayoutChild);

        addView(relativeLayout);

        cardView.setOnTouchListener(this);

    }

    private void initTextView(Context context) {
        RelativeLayout.LayoutParams layoutParamsTextView = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsTextView.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView = new TextView(context);
        textView.setId(id++);
        textView.setText(text);
        textView.setTextColor(textColor);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setLayoutParams(layoutParamsTextView);
        if (textSize != DEFAULT_TEXT_SIZE)
            textView.setTextSize(textSize);
    }

    private void initImageViewStart(Context context) {

        RelativeLayout.LayoutParams layoutParamsCardView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsCardView.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        cardViewStart = new CardView(context);
        layoutParamsCardView.height = (int) width_height;
        layoutParamsCardView.width = (int) width_height;
//        layoutParamsCardView.setMargins((int)margin, (int)margin, (int)margin, (int)margin);
        cardViewStart.setId(idCarViewStart++);
        cardViewStart.setRadius(radius);
        cardViewStart.setCardBackgroundColor(DEFAULT_IMAGE_TINT_COLOR_TRANPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardViewStart.setElevation(0);
        }
        cardViewStart.setLayoutParams(layoutParamsCardView);

        ViewGroup.LayoutParams layoutParamsImageView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageViewStart = new ImageView(context);
        imageViewStart.setLayoutParams(layoutParamsImageView);
        imageViewStart.setImageResource(imgResDesplasing);
        imageViewStart.setColorFilter(DEFAULT_IMAGE_TINT_COLOR);
        imageViewStart.setPadding((int) paddingIconDesplasing, (int) paddingIconDesplasing, (int) paddingIconDesplasing, (int) paddingIconDesplasing);
        imageViewStart.setColorFilter(Color.WHITE);

        cardViewStart.addView(imageViewStart);

    }

    private void initImageViewEnd(Context context) {

        RelativeLayout.LayoutParams layoutParamsCardView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsCardView.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        cardViewEnd = new CardView(context);
        layoutParamsCardView.height = (int) width_height;
        layoutParamsCardView.width = (int) width_height;
//        layoutParamsCardView.setMargins((int)margin, (int)margin, (int)margin, (int)margin);
        cardViewEnd.setRadius(radius);
        cardViewEnd.setId(idCarViewEnd++);
        cardViewEnd.setCardBackgroundColor(backgroundTintCardEnd);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardViewEnd.setElevation(0);
        }
        cardViewEnd.setClickable(false);
        cardViewEnd.setLayoutParams(layoutParamsCardView);

        ViewGroup.LayoutParams layoutParamsImageView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageViewEnd = new ImageView(context);
        imageViewEnd.setLayoutParams(layoutParamsImageView);
        imageViewEnd.setImageResource(imgResEnd);
        imageViewEnd.setColorFilter(DEFAULT_IMAGE_TINT_COLOR);
        imageViewEnd.setPadding((int) paddingIconEnd, (int) paddingIconEnd, (int) paddingIconEnd, (int) paddingIconEnd);
        imageViewEnd.setColorFilter(tintEnd);

        cardViewEnd.addView(imageViewEnd);
    }

    private void initCardView(Context context) {
        //CardView
        RelativeLayout.LayoutParams layoutParamsCardView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cardView = new CardView(context);
        layoutParamsCardView.height = (int) width_height;
        layoutParamsCardView.width = (int) width_height;
//        layoutParamsCardView.setMargins((int)margin,(int) margin,(int) margin, (int)margin);
        cardView.setId(idCarView++);
        cardView.setRadius(radius);
        cardView.setCardBackgroundColor(backgroundTintCardDesplasing);
        cardView.setLayoutParams(layoutParamsCardView);

        //ImageView
        ViewGroup.LayoutParams layoutParamsImageView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageViewCardview = new ImageView(context);
        imageViewCardview.setId(idImgDesplasing++);
        imageViewCardview.setLayoutParams(layoutParamsImageView);
        imageViewCardview.setImageResource(imgResDesplasing);
//        if (tint_visible) {
        imageViewCardview.setColorFilter(tint);
//        }else {
//
//        }
        imageViewCardview.setPadding((int) paddingIconDesplasing, (int) paddingIconDesplasing, (int) paddingIconDesplasing, (int) paddingIconDesplasing);

        cardView.addView(imageViewCardview);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        final int x = (int) event.getRawX();
        final int y = (int) event.getRawY();

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                _xDelta = x - layoutParams.leftMargin;

                break;

            case MotionEvent.ACTION_MOVE:

                RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) v.getLayoutParams();
                layoutParams1.leftMargin = x - _xDelta;
                v.setLayoutParams(layoutParams1);
                break;

            case MotionEvent.ACTION_UP:

                RelativeLayout.LayoutParams layoutParamsUp = (RelativeLayout.LayoutParams) v.getLayoutParams();

                if (layoutParamsUp.leftMargin <= (BTN_AVAILABILITY_WIDTH / 2)) {
                    layoutParamsUp.leftMargin = 0;
                    relativeLayout.setBackground(getContext().getResources().getDrawable(background_custom_slide_off));

                    if (listener != null) {
                        if (count == 100) {
                            listener.onButtonOff();
                            count = 200;
                        }
                    }

                } else {
                    layoutParamsUp.leftMargin =
                            BTN_AVAILABILITY_WIDTH - (int) Constants.convertDpiToPx(getContext(), ((int) Constants.convertPxToDpi(getContext(), width_height) + 6));
                    relativeLayout.setBackground(getContext().getResources().getDrawable(background_custom_slide_on));

                    if (listener != null) {
                        if (count == 200) {
                            listener.onButtonOn();
                            count = 100;
                        }
                    }
                }

                v.setLayoutParams(layoutParamsUp);
                break;

        }

        relativeLayout.invalidate();

        return true;
    }

    public void OnSetListenerSlideButton(setListenerSlideButton listener) {
        this.listener = listener;
    }

    public interface setListenerSlideButton {
        void onButtonOff();
        void onButtonOn();
    }

    public void sb_setText(String text){
        textView.setText(text);
    }

    public void sb_setTextColor(int color){
        textView.setTextColor(color);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(final boolean checked) {
        final RelativeLayout.LayoutParams layoutParamsUp = (LayoutParams) cardView.getLayoutParams();
        isChecked = checked;

        relativeLayout.post(new Runnable() {
            @Override
            public void run() {
                BTN_AVAILABILITY_WIDTH = relativeLayout.getWidth();

                if (checked) {

                    layoutParamsUp.leftMargin = BTN_AVAILABILITY_WIDTH - (int) Constants.convertDpiToPx(getContext(), ((int) Constants.convertPxToDpi(getContext(), width_height) + 6));
                    relativeLayout.setBackground(getContext().getResources().getDrawable(background_custom_slide_on));

                    if (listener != null) {
                        if (count == 200) {

                            listener.onButtonOn();
                            count = 100;
                        }
                    }

                } else {

                    layoutParamsUp.leftMargin = 0;
                    relativeLayout.setBackground(getContext().getResources().getDrawable(background_custom_slide_off));

                    if (listener != null) {
                        if (count == 100) {
                            listener.onButtonOff();
                            count = 200;
                        }
                    }
                }

                cardView.setLayoutParams(layoutParamsUp);
                relativeLayout.invalidate();

            }
        });

    }

    public void setLockScrolling(boolean lock){
        isLockedScrolling = lock;
        if (lock){
            cardView.setOnTouchListener(null);
        }else{
            cardView.setOnTouchListener(this);
        }
    }

    public boolean isLockedScrolling(){
        return isLockedScrolling;
    }

}
