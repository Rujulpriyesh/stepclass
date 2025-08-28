
    
public class StringBuiltInMethods {
			public static void main(String[] args) {
				String sampleText = " Java Programming is Fun and Challenging! ";
        
				System.out.println("Original string: '" + sampleText + "'");
				System.out.println("Length (with spaces): " + sampleText.length());
    
        
				String trimmed = sampleText.trim();
				System.out.println("Trimmed string: '" + trimmed + "'");
				System.out.println("Length (trimmed): " + trimmed.length());
    
        
				System.out.println("Character at index 5: " + sampleText.charAt(5));
    
        
				int startProg = sampleText.indexOf("Programming");
				int endProg = startProg + "Programming".length();
				String programming = sampleText.substring(startProg, endProg);
				System.out.println("Extracted substring: " + programming);
    
        
				int indexFun = sampleText.indexOf("Fun");
				System.out.println("Index of 'Fun': " + indexFun);
    
        
				System.out.println("Contains 'Java': " + sampleText.contains("Java"));
    
        
				System.out.println("Starts with 'Java' (trimmed): " + trimmed.startsWith("Java"));
    
        
				System.out.println("Ends with '!': " + sampleText.endsWith("! "));
				System.out.println("Ends with '!' (trimmed): " + trimmed.endsWith("!"));
    
        
				System.out.println("Uppercase: " + sampleText.toUpperCase());
    
        
				System.out.println("Lowercase: " + sampleText.toLowerCase());
    
        
				int vowelCount = countVowels(sampleText);
				System.out.println("Number of vowels: " + vowelCount);
    
        
				System.out.print("Positions of 'a': ");
				findAllOccurrences(sampleText, 'a');
			}
    
        
			public static int countVowels(String text) {
				int count = 0;
				String vowels = "aeiouAEIOU";
				for (int i = 0; i < text.length(); i++) {
					if (vowels.indexOf(text.charAt(i)) != -1) {
						count++;
					}
				}
				return count;
			}
    
        
			public static void findAllOccurrences(String text, char target) {
				boolean found = false;
				for (int i = 0; i < text.length(); i++) {
					if (text.charAt(i) == target) {
						System.out.print(i + " ");
						found = true;
					}
				}
				if (!found) {
					System.out.print("None");
				}
				System.out.println();
			}
		}

