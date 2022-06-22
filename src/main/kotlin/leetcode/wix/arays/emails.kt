package leetcode.wix.arays

class Solution {
    fun numUniqueEmails(emails: Array<String>): Int {
        val regexPlus = Regex("\\+.*@")
        return emails
            .map {
                it
                    .replace(regexPlus, "@")
                    .replace(".", "")
            }
            .distinct()
            .count()
    }
}