/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.util;

import java.util.Collection;

public class ArrayList<E> extends java.util.ArrayList<E> {
	
	private static final long serialVersionUID = 1L;

	public ArrayList() {
	}

	public ArrayList(Collection<? extends E> c) {
		super(c);
	}
		
	public String toString() {
        StringBuilder sb = new StringBuilder();
       	sb.append("[");
        int i = 0;
        for (E item : this) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append((item instanceof String) ? "'" + item + "'" : item);
            i++;
        }        
       	sb.append("]");
        return sb.toString();
	}	
	
}
