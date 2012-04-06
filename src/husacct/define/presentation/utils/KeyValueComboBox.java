package husacct.define.presentation.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class KeyValueComboBox extends JComboBox{
	private HashMap<String, String> keyValuePair = new HashMap<String,String>();
	/**
	 * 
	 */
	private static final long serialVersionUID = -2870095258058479405L;

	public KeyValueComboBox() {
		super();
		keyValuePair = new HashMap<String,String>();
	}
	
	public void setModel(String[] keys, String[] values){
		setHashMap(keys, values);
		ComboBoxModel model = new DefaultComboBoxModel(keys);
		super.setModel(model);
	}
	
	private void setHashMap(String[] keys, String[] values){
		HashMap<String, String> keyValuePair = new HashMap<String, String>();
		for (int i = 0; i<keys.length;i++){
			keyValuePair.put(keys[i], values[i]);
		}
		this.keyValuePair = keyValuePair;
	}
	
	public String getSelectedItemKey(){
		String selectedItemValue = super.getSelectedItem().toString();
		return getHaskMapKeyFromValue(selectedItemValue);
	}
	
	private String getHaskMapKeyFromValue(String value){
		String key = "";
		for (Iterator iterator = keyValuePair.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String tmpKey = (String)entry.getKey();
			if (keyValuePair.get(key).equals(value)){
				key = tmpKey;
				break;
			}
		}
		return key;
	}
}
