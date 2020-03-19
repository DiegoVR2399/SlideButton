# SlideButton

    build.gradel:
    repositories {maven { url 'https://jitpack.io' }}
    
    dependencies:
    implementation 'com.github.DiegoVR2399:SlideButton:1.0'
    
    
    
    archivo xml:
    <slidebutton.com.pe.SlideButton
        android:layout_marginTop="10dp"
        android:id="@+id/sb_id"
        app:sb_src_desplasing="@android:drawable/ic_dialog_alert"
        app:sb_tint_icon_desplasing="@android:color/background_light"
        app:sb_padding_desplasing="7dp"
        app:sb_src_end="@android:drawable/ic_delete"
        app:sb_tint_icon_end="@android:color/background_light"
        app:sb_padding_end="7dp"
        app:sb_background_tint_end="@android:color/background_dark"
        app:sb_background_tint_desplasing="@android:color/holo_orange_dark"
        app:sb_text="Desliza para continuar"
        app:sb_textColor="@android:color/background_light"
        app:sb_textSize="3dp"
        app:sb_radius_card="17dp"
        app:sb_width_height_card="34dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
    
    *sb_src_desplasing = Asigna imagen que se va desplazar.
    *sb_tint_icon_desplasing = Tinte de la imagen que se va a desplazar.
    *sb_padding_desplasing = Asigna el pading de la imagen a desplazar.
    *sb_background_tint_desplasing = Asigna el pading de la imagen a desplazar.
    *sb_src_end = Asigna imagen en la parte final derecho.
    *sb_tint_icon_end = Tinte de la imagen en la parte final derecho.
    *sb_padding_end = Asigna el pading de la imagen en la parte final derecho.
    *sb_background_tint_end = Asigna un color de fondo de la imagen en la parte final derecho.
    *sb_text = Asigna un texto en el centro.
    *sb_textColor = Asigna un color al texto.
    *sb_textSize = Asigna un tamaño de letra.
    
    *sb_radius_card = Asigna un radio (borde) a las imagenes. (por defecto 17dp)
    *sb_width_height_card = Asigna un ancho y alto de las imagenes. (por defecto 34dp)
    
    *sb_background_custom_slide_off = Asigna un archivo customisado para cuando este en off.
    *sb_background_custom_slide_on = Asigna un archivo customisado para cuando este en on.