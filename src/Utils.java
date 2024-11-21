import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for working with enums and Swing components.
 */
public class Utils {
	
	/**
	 * Converts an enum constant to a more readable format by splitting words
	 * at underscores, capitalizing the first letter of each word, and converting
	 * the rest to lowercase.
	 *
	 * @param input the enum constant to convert
	 * @return a human-readable string representation of the enum constant
	 */
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
	
	/**
	 * Populates a JComboBox with the human-readable names of the constants
	 * of a given enum class.
	 *
	 * @param comboBox  the JComboBox to populate
	 * @param enumClass the enum class whose constants will populate the JComboBox
	 * @param <E>       the type of the enum
	 */
	public static <E extends Enum<E>> void populateComboBox(JComboBox<String> comboBox, Class<E> enumClass) {
		for (E enumConstant : enumClass.getEnumConstants()) {
			comboBox.addItem(Utils.enumToNormalCase(enumConstant));
		}
	}
	
	/**
	 * Creates a map from human-readable strings to their corresponding enum constants
	 * for a given enum class. The map's keys are the human-readable names of the enum constants,
	 * and the values are the corresponding enum constants.
	 *
	 * @param enumClass the enum class to map
	 * @param <E>       the type of the enum
	 * @return a map from human-readable strings to enum constants
	 */
	public static <E extends Enum<E>> Map<String, E> createEnumMap(Class<E> enumClass) {
		Map<String, E> enumMap = new HashMap<>();
		for (E enumConstant : enumClass.getEnumConstants()) {
			String displayName = Utils.enumToNormalCase(enumConstant);
			enumMap.put(displayName, enumConstant);
		}
		return enumMap;
	}
}
