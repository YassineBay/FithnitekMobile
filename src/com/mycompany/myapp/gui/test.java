package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;


public class test extends Form  {
    public test(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Button gui_Button = new com.codename1.ui.Button();
    protected com.codename1.ui.RadioButton gui_Radio_Button = new com.codename1.ui.RadioButton();
    protected com.codename1.components.SpanButton gui_Span_Button = new com.codename1.components.SpanButton();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("test");
        setName("test");
        gui_Button.setText("Button");
                gui_Button.setInlineStylesTheme(resourceObjectInstance);
        gui_Button.setName("Button");
        gui_Radio_Button.setText("Radio");
                gui_Radio_Button.setInlineStylesTheme(resourceObjectInstance);
        gui_Radio_Button.setName("Radio_Button");
        gui_Span_Button.setText("Span Button");
                gui_Span_Button.setInlineStylesTheme(resourceObjectInstance);
        gui_Span_Button.setName("Span_Button");
        addComponent(gui_Button);
        addComponent(gui_Radio_Button);
        addComponent(gui_Span_Button);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Button.getParent().getLayout()).setInsets(gui_Button, "31.368822% auto auto 42.436974%").setReferenceComponents(gui_Button, "-1 -1 -1 -1").setReferencePositions(gui_Button, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Radio_Button.getParent().getLayout()).setInsets(gui_Radio_Button, "auto 20.308123% 43.726234% auto").setReferenceComponents(gui_Radio_Button, "-1 -1 -1 -1").setReferencePositions(gui_Radio_Button, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Span_Button.getParent().getLayout()).setInsets(gui_Span_Button, "20.152092% 10.92437% auto auto").setReferenceComponents(gui_Span_Button, "-1 -1 -1 -1").setReferencePositions(gui_Span_Button, "0.0 0.0 0.0 0.0");
    }// </editor-fold>
//-- DON'T EDIT ABOVE THIS LINE!!!
}
