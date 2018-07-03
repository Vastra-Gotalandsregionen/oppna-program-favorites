package se.vgregion.portal.favorites.comporator;

import java.util.Comparator;
import java.util.Locale;

import com.liferay.portal.kernel.model.Layout;

public class LayoutNameCompator  implements Comparator<Layout> {
	
	private boolean _asc = false;
	private Locale locale;
	private String orderBy;	
	
	public LayoutNameCompator(Locale locale, String orderBy) {
		this.locale = locale;
		this.orderBy = orderBy;
		
		if (this.orderBy.equals("asc")) {
			_asc = true;
		} else {
			_asc = false;
		}

	}

    @Override
    public int compare(Layout layout1, Layout layout2) {
    	
    	String layout1Name = layout1.getName(locale);
    	String layout2Name = layout2.getName(locale);
    	
    	int value = layout1Name.compareToIgnoreCase(layout2Name);

    	if (_asc) {
    	    return value;
    	} else {
    	    return -value;
    	}
    }
	
	
}


