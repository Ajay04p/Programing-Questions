class Solution:
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        max_len = 0
        index = 0
        flag = False
        final_flag = False
        for i,w in enumerate(s):
            
            left = i-1
            right = i+1
            length = 1
            
            while(left >= 0 and right < len(s) and s[left] == s[right]):
                length+=2
                left-=1
                right+=1
                
                
            left = i
            right = i+1
            length2 = 0
            
            while(left >= 0 and right < len(s) and s[left] == s[right]):
                length2+=2
                left-=1
                right+=1

            if(length > length2):
                flag = False
            else:
                flag = True
                length = length2
        
            if(length > max_len):
                max_len = length
                index = i
                final_flag = flag
                
        #print(index, max_len, final_flag)
        if final_flag:
            start = (index-(max_len//2-1))
            end = index+(max_len//2+1)
            #print(start, end)
            return s[start: end]
        else:
            start = index-max_len//2
            end = index+(max_len//2+1)
            #print(start, end)
            return s[start: end]
            
            
