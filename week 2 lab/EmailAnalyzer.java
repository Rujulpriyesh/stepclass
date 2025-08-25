import java.util.*;
public class EmailAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> emails = new ArrayList<>();
        System.out.println("Enter email addresses (type 'end' to finish):");
        while (true) {
            String email = scanner.nextLine();
            if (email.equalsIgnoreCase("end")) break;
            emails.add(email);
        }
        List<EmailInfo> infoList = new ArrayList<>();
        Map<String, Integer> domainCount = new HashMap<>();
        int validCount = 0, invalidCount = 0, totalUsernameLen = 0;
        for (String email : emails) {
            EmailInfo info = extractEmailInfo(email);
            infoList.add(info);
            if (info.valid) {
                validCount++;
                totalUsernameLen += info.username.length();
                domainCount.put(info.domain, domainCount.getOrDefault(info.domain, 0) + 1);
            } else {
                invalidCount++;
            }
        }
        String mostCommonDomain = domainCount.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("");
        double avgUsernameLen = validCount > 0 ? (double)totalUsernameLen / validCount : 0;
        System.out.printf("%-30s %-15s %-20s %-15s %-10s %-10s\n", "Email", "Username", "Domain", "Domain Name", "Extension", "Valid");
        for (EmailInfo info : infoList) {
            System.out.printf("%-30s %-15s %-20s %-15s %-10s %-10s\n", info.email, info.username, info.domain, info.domainName, info.extension, info.valid ? "Valid" : "Invalid");
        }
        System.out.println("Total valid: " + validCount);
        System.out.println("Total invalid: " + invalidCount);
        System.out.println("Most common domain: " + mostCommonDomain);
        System.out.println("Average username length: " + avgUsernameLen);
        scanner.close();
    }

    static class EmailInfo {
        String email, username, domain, domainName, extension;
        boolean valid;
        EmailInfo(String email, String username, String domain, String domainName, String extension, boolean valid) {
            this.email = email;
            this.username = username;
            this.domain = domain;
            this.domainName = domainName;
            this.extension = extension;
            this.valid = valid;
        }
    }

    public static EmailInfo extractEmailInfo(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        int dot = email.indexOf('.', at + 1);
        boolean valid = at > 0 && at == lastAt && dot > at && dot < email.length() - 1;
        String username = valid ? email.substring(0, at) : "";
        String domain = valid ? email.substring(at + 1) : "";
        int dotInDomain = domain.indexOf('.');
        String domainName = valid && dotInDomain > 0 ? domain.substring(0, dotInDomain) : "";
        String extension = valid && dotInDomain > 0 ? domain.substring(dotInDomain + 1) : "";
        return new EmailInfo(email, username, domain, domainName, extension, valid);
    }
}
