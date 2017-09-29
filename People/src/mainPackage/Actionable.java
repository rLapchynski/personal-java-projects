package mainPackage;

import java.awt.event.*;

public interface Actionable {
	void addActionListener(ActionListener listener);
	void removeActionListener(ActionListener listener);
}
