package sample;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SelectAllCellEditor extends DefaultCellEditor
{
    public SelectAllCellEditor(final JTextField textField) {
        super( textField );
        textField.addFocusListener( new FocusAdapter()
        {
            public void focusGained( final FocusEvent e )
            {
                textField.selectAll();
            }
        } );
    }
}