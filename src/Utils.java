import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Utils {
	public static String enumToNormalCase(Enum<?> input) {
		StringBuilder result = new StringBuilder();
		String[] words = input.toString().split("_"); // Split the input by underscore
		
		for (String word : words) {
			if (!word.isEmpty()) {
				result.append(Character.toUpperCase(word.charAt(0))) // Capitalize the first letter
						.append(word.substring(1).toLowerCase())       // Lowercase the rest
						.append(" ");                                 // Add a space after each word
			}
		}
		
		// Remove the trailing space and return the result
		return result.toString().trim();
	}
	
	public static <E extends Enum<E>> void populateComboBox(JComboBox<String> comboBox, Class<E> enumClass) {
		for (E enumConstant : enumClass.getEnumConstants()) {
			comboBox.addItem(Utils.enumToNormalCase(enumConstant));
		}
	}
	
	public static <E extends Enum<E>> Map<String, E> createEnumMap(Class<E> enumClass) {
		Map<String, E> enumMap = new HashMap<>();
		for (E enumConstant : enumClass.getEnumConstants()) {
			String displayName = Utils.enumToNormalCase(enumConstant);
			enumMap.put(displayName, enumConstant);
		}
		return enumMap;
	}
}
